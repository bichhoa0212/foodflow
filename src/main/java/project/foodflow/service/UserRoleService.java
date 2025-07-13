package project.foodflow.service;

import project.foodflow.entity.UserRole;
import project.foodflow.entity.Role;
import project.foodflow.entity.Permission;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface UserRoleService {
    
    UserRole save(UserRole userRole);
    
    Optional<UserRole> findById(Long id);
    
    List<UserRole> findAll();
    
    List<UserRole> findByUserId(Long userId);
    
    List<UserRole> findByRoleId(Long roleId);
    
    Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);
    
    List<UserRole> findByUserIdAndStatus(Long userId, Integer status);
    
    List<UserRole> findByRoleIdAndStatus(Long roleId, Integer status);
    
    boolean existsByUserIdAndRoleIdAndStatus(Long userId, Long roleId, Integer status);
    
    long countByUserId(Long userId);
    
    long countByRoleId(Long roleId);
    
    long countByUserIdAndStatus(Long userId, Integer status);
    
    List<UserRole> findByUserIdAndRoleName(Long userId, String roleName);
    
    List<UserRole> findByRoleName(String roleName);
    
    List<UserRole> findAllActiveWithUserAndRole();
    
    /**
     * Find user roles by user ID with role details
     */
    List<UserRole> findByUserIdWithRole(Long userId);
    
    /**
     * Alternative query to find user roles with role and user details (for debugging)
     */
    List<UserRole> findByUserIdWithRoleAndUser(Long userId);
    
    /**
     * Find user roles by role ID with user details
     */
    List<UserRole> findByRoleIdWithUser(Long roleId);
    
    /**
     * Native query to test if the issue is with JPQL
     */
    List<Object[]> findByUserIdWithRoleNative(Long userId);
    
    /**
     * Get user roles by user ID
     */
    List<Role> getUserRoles(Long userId);
    
    void deleteByUserId(Long userId);
    
    void deleteByRoleId(Long roleId);
    
    void deleteByUserIdAndRoleId(Long userId, Long roleId);
    
    List<UserRole> findByCreatedDateBetween(Timestamp startDate, Timestamp endDate);
    
    // Business methods
    void assignRoleToUser(Long userId, Long roleId);
    
    void removeRoleFromUser(Long userId, Long roleId);
    
    boolean userHasRole(Long userId, String roleName);
    
    List<String> getUserRoleNames(Long userId);
    
    /**
     * Debug method to check if user has any roles
     */
    long countUserRolesByUserId(Long userId);

    List<Permission> getUserPermissions(Long userId);
} 