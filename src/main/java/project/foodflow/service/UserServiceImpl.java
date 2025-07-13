package project.foodflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.foodflow.entity.User;
import project.foodflow.repository.UserRepository;
import project.foodflow.repository.UserRoleRepository;
import project.foodflow.repository.PermissionRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmailForUserDetails(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        
        // Get user permissions
        List<String> permissions = permissionRepository.findPermissionsByUserId(user.getId())
                .stream()
                .map(permission -> permission.getName())
                .collect(Collectors.toList());
        
        // Convert permissions to authorities
        List<SimpleGrantedAuthority> authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority("ROLE_" + permission.toUpperCase()))
                .collect(Collectors.toList());
        
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }

    @Override
    public User save(User user) {
        if (user.getCreatedDate() == null) {
            user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        }
        user.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByEmailOrPhone(String username) {
        return userRepository.findByEmailOrPhone(username, username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findByEmailForUserDetails(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    @Override
    public List<User> findByStatus(Integer status) {
        return userRepository.findByStatus(status);
    }
    
    @Override
    public List<User> findByRoleName(String roleName) {
        return userRepository.findByRoleName(roleName);
    }
    
    @Override
    public List<User> findByGroupName(String groupName) {
        return userRepository.findByGroupName(groupName);
    }
    
    @Override
    public List<User> findUsersWithFilters(String name, String email, String phone, Integer status) {
        return userRepository.findUsersWithFilters(name, email, phone, status);
    }
    
    @Override
    public long countByStatus(Integer status) {
        return userRepository.countByStatus(status);
    }
    
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }
    
    @Override
    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }
    
    @Override
    public List<User> findByProvider(String provider) {
        return userRepository.findByProvider(provider);
    }
    
    @Override
    public Optional<User> findByProviderAndProviderUserId(String provider, String providerUserId) {
        return userRepository.findByProviderAndProviderUserId(provider, providerUserId);
    }

    @Override
    public boolean existsByProviderUserIdOrEmailOrPhone(String providerUserId, String email, String phone) {
        return userRepository.existsByProviderUserIdOrEmailOrPhone(providerUserId, email, phone);
    }

} 