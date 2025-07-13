package project.foodflow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.foodflow.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    
    /**
     * Find user roles by user ID
     */
    List<UserRole> findByUserId(Long userId);
    
    /**
     * Find user roles by role ID
     */
    List<UserRole> findByRoleId(Long roleId);
    
    /**
     * Find user role by user ID and role ID
     */
    Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);
    
    /**
     * Find active user roles by user ID
     */
    List<UserRole> findByUserIdAndStatus(Long userId, Integer status);
    
    /**
     * Find active user roles by role ID
     */
    List<UserRole> findByRoleIdAndStatus(Long roleId, Integer status);
    
    /**
     * Check if user has role
     */
    boolean existsByUserIdAndRoleIdAndStatus(Long userId, Long roleId, Integer status);
    
    /**
     * Count user roles by user ID
     */
    long countByUserId(Long userId);
    
    /**
     * Count user roles by role ID
     */
    long countByRoleId(Long roleId);
    
    /**
     * Count active user roles by user ID
     */
    long countByUserIdAndStatus(Long userId, Integer status);
    
    /**
     * Find user roles by user ID and role name
     */
    @Query("SELECT ur FROM UserRole ur " +
           "JOIN ur.role r " +
           "WHERE ur.userId = :userId AND r.name = :roleName")
    List<UserRole> findByUserIdAndRoleName(
        @Param("userId") Long userId,
        @Param("roleName") String roleName
    );
    
    /**
     * Find user roles by role name
     */
    @Query("SELECT ur FROM UserRole ur " +
           "JOIN ur.role r " +
           "WHERE r.name = :roleName AND ur.status = 1")
    List<UserRole> findByRoleName(@Param("roleName") String roleName);
    
    /**
     * Find user roles with user and role details
     */
    @Query("SELECT ur FROM UserRole ur " +
           "JOIN FETCH ur.user u " +
           "JOIN FETCH ur.role r " +
           "WHERE ur.status = 1")
    List<UserRole> findAllActiveWithUserAndRole();
    
    /**
     * Find user roles by user ID with role details
     */
    @Query("SELECT ur FROM UserRole ur " +
           "JOIN FETCH ur.role r " +
           "WHERE ur.userId = :userId AND ur.status = 1")
    List<UserRole> findByUserIdWithRole(@Param("userId") Long userId);
    
    /**
     * Alternative query to find user roles with role details (for debugging)
     */
    @Query("SELECT ur FROM UserRole ur " +
           "LEFT JOIN FETCH ur.role r " +
           "LEFT JOIN FETCH ur.user u " +
           "WHERE ur.userId = :userId AND ur.status = 1")
    List<UserRole> findByUserIdWithRoleAndUser(@Param("userId") Long userId);
    
    /**
     * Simple query to check if user has any roles (for debugging)
     */
    @Query("SELECT COUNT(ur) FROM UserRole ur WHERE ur.userId = :userId AND ur.status = 1")
    long countUserRolesByUserId(@Param("userId") Long userId);
    
    /**
     * Native query to test if the issue is with JPQL
     */
    @Query(value = "SELECT ur.*, r.name as role_name, r.description as role_description " +
                   "FROM user_roles ur " +
                   "LEFT JOIN app_roles r ON ur.role_id = r.id " +
                   "WHERE ur.user_id = :userId AND ur.status = 1", 
           nativeQuery = true)
    List<Object[]> findByUserIdWithRoleNative(@Param("userId") Long userId);
    
    /**
     * Find user roles by role ID with user details
     */
    @Query("SELECT ur FROM UserRole ur " +
           "JOIN FETCH ur.user u " +
           "WHERE ur.roleId = :roleId AND ur.status = 1")
    List<UserRole> findByRoleIdWithUser(@Param("roleId") Long roleId);
    
    /**
     * Delete user roles by user ID
     */
    void deleteByUserId(Long userId);
    
    /**
     * Delete user roles by role ID
     */
    void deleteByRoleId(Long roleId);
    
    /**
     * Delete specific user role
     */
    void deleteByUserIdAndRoleId(Long userId, Long roleId);
    
    /**
     * Find user roles created in date range
     */
    @Query("SELECT ur FROM UserRole ur WHERE ur.createdDate BETWEEN :startDate AND :endDate")
    List<UserRole> findByCreatedDateBetween(
        @Param("startDate") java.sql.Timestamp startDate,
        @Param("endDate") java.sql.Timestamp endDate
    );
} 