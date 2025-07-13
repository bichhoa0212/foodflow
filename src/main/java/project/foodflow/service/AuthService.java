package project.foodflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.foodflow.constant.SystemConstants;
import project.foodflow.dto.AuthRequest;
import project.foodflow.dto.AuthResponse;
import project.foodflow.dto.RegisterRequest;
import project.foodflow.entity.User;
import project.foodflow.entity.Role;
import project.foodflow.entity.Group;
import project.foodflow.dto.RefreshTokenRequest;
import project.foodflow.security.JwtService;
import project.foodflow.util.ChecksumUtil;
import project.foodflow.exception.CustomExceptions;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final RoleService roleService;
    private final GroupService groupService;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ChecksumUtil checksumUtil;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // Validate checksum trả về lỗi cho response
        if (!checksumUtil.validateChecksum(request.getProviderUserId(), request.getPassword(), request.getChecksum())) {
            throw new CustomExceptions.InvalidChecksumException(SystemConstants.ErrorMessages.INVALID_CHECKSUM);
        }

        // Check if user already exists providerUserId, email, phone
        if (userService.existsByProviderUserIdOrEmailOrPhone(request.getProviderUserId(), request.getEmail(), request.getPhone())) {
            throw new CustomExceptions.UserAlreadyExistsException(SystemConstants.ErrorMessages.USER_ALREADY_EXISTS);
        }

        // Create new user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setSex(request.getSex());
        user.setAddress(request.getAddress());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setProvider(request.getProvider() != null ? request.getProvider() : SystemConstants.Defaults.DEFAULT_PROVIDER);
        user.setProviderUserId(request.getProviderUserId());
        user.setProviderMetaData(request.getProviderMetaData());
        user.setStatus(SystemConstants.Defaults.DEFAULT_STATUS);
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        user.setCreatedUser(SystemConstants.SystemUsers.SYSTEM_USER_ID);

        User savedUser = userService.save(user);

        // Assign default role (CUSTOMER)
        Role customerRole = roleService.findByName(SystemConstants.Defaults.DEFAULT_ROLE)
                .orElseThrow(() -> new CustomExceptions.RoleNotFoundException(SystemConstants.ErrorMessages.ROLE_NOT_FOUND));
        userRoleService.assignRoleToUser(savedUser.getId(), customerRole.getId());

        // Assign default group (CUSTOMERS)
        Group customerGroup = groupService.findByName(SystemConstants.Defaults.DEFAULT_GROUP)
                .orElseThrow(() -> new CustomExceptions.GroupNotFoundException(SystemConstants.ErrorMessages.GROUP_NOT_FOUND));
        groupService.addUserToGroup(savedUser.getId(), customerGroup.getId());

        // Generate tokens with actual user roles
        List<String> userRoles = userRoleService.getUserRoles(savedUser.getId())
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        List<SimpleGrantedAuthority> authorities = userRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(savedUser.getProviderUserId())
                .password(savedUser.getPassword())
                .authorities(authorities)
                .build();
        
        String jwtToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return new AuthResponse(
                jwtToken,
                refreshToken,
                SystemConstants.Jwt.TOKEN_PREFIX.trim(),
                SystemConstants.Jwt.ACCESS_TOKEN_EXPIRY,
                buildUserInfo(savedUser, userRoles)
        );
    }

    public AuthResponse authenticate(AuthRequest request) {
        // Validate checksum
        if (!checksumUtil.validateChecksum(request.getProviderUserId(), request.getPassword(), request.getChecksum())) {
            throw new CustomExceptions.InvalidChecksumException(SystemConstants.ErrorMessages.INVALID_CHECKSUM);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getProviderUserId(),
                        request.getPassword()
                )
        );

        User user = userService.findByEmailOrPhone(request.getProviderUserId())
                .orElseThrow(() -> new CustomExceptions.UserNotFoundException(SystemConstants.ErrorMessages.USER_NOT_FOUND));

        // Get actual user roles
        List<String> userRoles = userRoleService.getUserRoles(user.getId())
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        List<SimpleGrantedAuthority> authorities = userRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
        
        String jwtToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return new AuthResponse(
                jwtToken,
                refreshToken,
                SystemConstants.Jwt.TOKEN_PREFIX.trim(),
                SystemConstants.Jwt.ACCESS_TOKEN_EXPIRY,
                buildUserInfo(user, userRoles)
        );
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        final String username = jwtService.extractUsername(request.getRefreshToken());
        
        if (username != null) {
            User user = userService.findByEmail(username)
                    .orElseThrow(() -> new CustomExceptions.UserNotFoundException(SystemConstants.ErrorMessages.USER_NOT_FOUND));

            // Get actual user roles
            List<String> userRoles = userRoleService.getUserRoles(user.getId())
                    .stream()
                    .map(Role::getName)
                    .collect(Collectors.toList());

            List<SimpleGrantedAuthority> authorities = userRoles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList());

            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .authorities(authorities)
                    .build();

            if (jwtService.isTokenValid(request.getRefreshToken(), userDetails)) {
                String accessToken = jwtService.generateToken(userDetails);
                
                return new AuthResponse(
                        accessToken,
                        request.getRefreshToken(),
                        SystemConstants.Jwt.TOKEN_PREFIX.trim(),
                        SystemConstants.Jwt.ACCESS_TOKEN_EXPIRY,
                        buildUserInfo(user, userRoles)
                );
            }
        }
        throw new CustomExceptions.InvalidTokenException(SystemConstants.ErrorMessages.INVALID_TOKEN);
    }

    private AuthResponse.UserInfo buildUserInfo(User user, List<String> roles) {
        // Get user permissions based on roles
        List<String> permissions = userRoleService.getUserPermissions(user.getId())
                .stream()
                .map(permission -> permission.getName())
                .collect(Collectors.toList());

        return new AuthResponse.UserInfo(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                roles.toArray(new String[0]),
                permissions.toArray(new String[0])
        );
    }
} 