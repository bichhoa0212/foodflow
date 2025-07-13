package project.foodflow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.foodflow.entity.RolePermission;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    
    /**
     * Find role permissions by role ID
     */
    List<RolePermission> findByRoleId(Long roleId);
    
    /**
     * Find role permissions by permission ID
     */
    List<RolePermission> findByPermissionId(Long permissionId);
    
    /**
     * Find role permission by role ID and permission ID
     */
    Optional<RolePermission> findByRoleIdAndPermissionId(Long roleId, Long permissionId);
    
    /**
     * Find active role permissions by role ID
     */
    List<RolePermission> findByRoleIdAndStatus(Long roleId, Integer status);
    
    /**
     * Find active role permissions by permission ID
     */
    List<RolePermission> findByPermissionIdAndStatus(Long permissionId, Integer status);
    
    /**
     * Check if role has permission
     */
    boolean existsByRoleIdAndPermissionIdAndStatus(Long roleId, Long permissionId, Integer status);
    
    /**
     * Count role permissions by role ID
     */
    long countByRoleId(Long roleId);
    
    /**
     * Count role permissions by permission ID
     */
    long countByPermissionId(Long permissionId);
    
    /**
     * Count active role permissions by role ID
     */
    long countByRoleIdAndStatus(Long roleId, Integer status);
    
    /**
     * Find role permissions by role name
     */
    @Query("SELECT rp FROM RolePermission rp " +
           "JOIN rp.role r " +
           "WHERE r.name = :roleName AND rp.status = 1")
    List<RolePermission> findByRoleName(@Param("roleName") String roleName);
    
    /**
     * Find role permissions by permission name
     */
    @Query("SELECT rp FROM RolePermission rp " +
           "JOIN rp.permission p " +
           "WHERE p.name = :permissionName AND rp.status = 1")
    List<RolePermission> findByPermissionName(@Param("permissionName") String permissionName);
    
    /**
     * Find role permissions by role name and permission name
     */
    @Query("SELECT rp FROM RolePermission rp " +
           "JOIN rp.role r " +
           "JOIN rp.permission p " +
           "WHERE r.name = :roleName AND p.name = :permissionName")
    List<RolePermission> findByRoleNameAndPermissionName(
        @Param("roleName") String roleName,
        @Param("permissionName") String permissionName
    );
    
    /**
     * Find role permissions with role and permission details
     */
    @Query("SELECT rp FROM RolePermission rp " +
           "JOIN FETCH rp.role r " +
           "JOIN FETCH rp.permission p " +
           "WHERE rp.status = 1")
    List<RolePermission> findAllActiveWithRoleAndPermission();
    
    /**
     * Find role permissions by role ID with permission details
     */
    @Query("SELECT rp FROM RolePermission rp " +
           "JOIN FETCH rp.permission p " +
           "WHERE rp.roleId = :roleId AND rp.status = 1")
    List<RolePermission> findByRoleIdWithPermission(@Param("roleId") Long roleId);
    
    /**
     * Find role permissions by permission ID with role details
     */
    @Query("SELECT rp FROM RolePermission rp " +
           "JOIN FETCH rp.role r " +
           "WHERE rp.permissionId = :permissionId AND rp.status = 1")
    List<RolePermission> findByPermissionIdWithRole(@Param("permissionId") Long permissionId);
    
    /**
     * Find permissions by role ID (returns permission names)
     */
    @Query("SELECT p.name FROM RolePermission rp " +
           "JOIN rp.permission p " +
           "WHERE rp.roleId = :roleId AND rp.status = 1")
    List<String> findPermissionNamesByRoleId(@Param("roleId") Long roleId);
    
    /**
     * Find roles by permission ID (returns role names)
     */
    @Query("SELECT r.name FROM RolePermission rp " +
           "JOIN rp.role r " +
           "WHERE rp.permissionId = :permissionId AND rp.status = 1")
    List<String> findRoleNamesByPermissionId(@Param("permissionId") Long permissionId);
    
    /**
     * Delete role permissions by role ID
     */
    void deleteByRoleId(Long roleId);
    
    /**
     * Delete role permissions by permission ID
     */
    void deleteByPermissionId(Long permissionId);
    
    /**
     * Delete specific role permission
     */
    void deleteByRoleIdAndPermissionId(Long roleId, Long permissionId);
    
    /**
     * Find role permissions created in date range
     */
    @Query("SELECT rp FROM RolePermission rp WHERE rp.createdDate BETWEEN :startDate AND :endDate")
    List<RolePermission> findByCreatedDateBetween(
        @Param("startDate") java.sql.Timestamp startDate,
        @Param("endDate") java.sql.Timestamp endDate
    );
} 