package project.foodflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.foodflow.entity.UserRole;
import project.foodflow.entity.Role;
import project.foodflow.entity.Permission;
import project.foodflow.repository.UserRoleRepository;
import project.foodflow.repository.RoleRepository;
import project.foodflow.repository.PermissionRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserRole save(UserRole userRole) {
        if (userRole.getCreatedDate() == null) {
            userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        }
        userRole.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        return userRoleRepository.save(userRole);
    }

    @Override
    public Optional<UserRole> findById(Long id) {
        return userRoleRepository.findById(id);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public List<UserRole> findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    @Override
    public List<UserRole> findByRoleId(Long roleId) {
        return userRoleRepository.findByRoleId(roleId);
    }

    @Override
    public Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId) {
        return userRoleRepository.findByUserIdAndRoleId(userId, roleId);
    }

    @Override
    public List<UserRole> findByUserIdAndStatus(Long userId, Integer status) {
        return userRoleRepository.findByUserIdAndStatus(userId, status);
    }

    @Override
    public List<UserRole> findByRoleIdAndStatus(Long roleId, Integer status) {
        return userRoleRepository.findByRoleIdAndStatus(roleId, status);
    }

    @Override
    public boolean existsByUserIdAndRoleIdAndStatus(Long userId, Long roleId, Integer status) {
        return userRoleRepository.existsByUserIdAndRoleIdAndStatus(userId, roleId, status);
    }

    @Override
    public long countByUserId(Long userId) {
        return userRoleRepository.countByUserId(userId);
    }

    @Override
    public long countByRoleId(Long roleId) {
        return userRoleRepository.countByRoleId(roleId);
    }

    @Override
    public long countByUserIdAndStatus(Long userId, Integer status) {
        return userRoleRepository.countByUserIdAndStatus(userId, status);
    }

    @Override
    public List<UserRole> findByUserIdAndRoleName(Long userId, String roleName) {
        return userRoleRepository.findByUserIdAndRoleName(userId, roleName);
    }

    @Override
    public List<UserRole> findByRoleName(String roleName) {
        return userRoleRepository.findByRoleName(roleName);
    }

    @Override
    public List<UserRole> findAllActiveWithUserAndRole() {
        return userRoleRepository.findAllActiveWithUserAndRole();
    }

    @Override
    public List<UserRole> findByUserIdWithRole(Long userId) {
        return userRoleRepository.findByUserIdWithRole(userId);
    }
    
    @Override
    public List<UserRole> findByUserIdWithRoleAndUser(Long userId) {
        return userRoleRepository.findByUserIdWithRoleAndUser(userId);
    }

    @Override
    public List<UserRole> findByRoleIdWithUser(Long roleId) {
        return userRoleRepository.findByRoleIdWithUser(roleId);
    }
    
    @Override
    public List<Object[]> findByUserIdWithRoleNative(Long userId) {
        return userRoleRepository.findByUserIdWithRoleNative(userId);
    }

    @Override
    public List<Role> getUserRoles(Long userId) {
        // Try the alternative query first for debugging
        List<UserRole> userRoles = findByUserIdWithRoleAndUser(userId);
        if (userRoles.isEmpty()) {
            // Fallback to original query
            userRoles = findByUserIdWithRole(userId);
        }
        
        return userRoles.stream()
                .map(UserRole::getRole)
                .filter(role -> role != null) // Filter out null roles
                .collect(Collectors.toList());
    }
    
    @Override
    public long countUserRolesByUserId(Long userId) {
        return userRoleRepository.countUserRolesByUserId(userId);
    }

    @Override
    public void deleteByUserId(Long userId) {
        userRoleRepository.deleteByUserId(userId);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        userRoleRepository.deleteByRoleId(roleId);
    }

    @Override
    public void deleteByUserIdAndRoleId(Long userId, Long roleId) {
        userRoleRepository.deleteByUserIdAndRoleId(userId, roleId);
    }

    @Override
    public List<UserRole> findByCreatedDateBetween(Timestamp startDate, Timestamp endDate) {
        return userRoleRepository.findByCreatedDateBetween(startDate, endDate);
    }

    @Override
    public void assignRoleToUser(Long userId, Long roleId) {
        // Check if role exists
        if (!roleRepository.existsById(roleId)) {
            throw new IllegalArgumentException("Role not found with ID: " + roleId);
        }
        
        // Check if user-role relationship already exists
        if (!existsByUserIdAndRoleIdAndStatus(userId, roleId, 1)) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRole.setStatus(1);
            userRole.setAssignedAt(new Timestamp(System.currentTimeMillis()));
            save(userRole);
        }
    }

    @Override
    public void removeRoleFromUser(Long userId, Long roleId) {
        Optional<UserRole> userRole = findByUserIdAndRoleId(userId, roleId);
        if (userRole.isPresent()) {
            UserRole ur = userRole.get();
            ur.setStatus(0);
            ur.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            save(ur);
        }
    }

    @Override
    public boolean userHasRole(Long userId, String roleName) {
        return !findByUserIdAndRoleName(userId, roleName).isEmpty();
    }

    @Override
    public List<String> getUserRoleNames(Long userId) {
        return findByUserIdWithRole(userId)
                .stream()
                .map(userRole -> userRole.getRole().getName())
                .collect(Collectors.toList());
    }

    @Override
    public List<Permission> getUserPermissions(Long userId) {
        // Get all roles for the user
        List<Role> userRoles = getUserRoles(userId);
        
        // Get all permissions for these roles through RolePermission
        return userRoles.stream()
                .flatMap(role -> role.getRolePermissions().stream())
                .map(rolePermission -> rolePermission.getPermission())
                .distinct()
                .collect(Collectors.toList());
    }
} 