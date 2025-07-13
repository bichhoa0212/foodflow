package project.foodflow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.foodflow.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    
    /**
     * Find permission by name
     */
    Optional<Permission> findByName(String name);
    
    /**
     * Find permissions by status
     */
    List<Permission> findByStatus(Integer status);
    
    /**
     * Check if permission name exists
     */
    boolean existsByName(String name);
    
    /**
     * Find permissions by description containing text
     */
    List<Permission> findByDescriptionContainingIgnoreCase(String description);
    
    /**
     * Find permissions with pagination and search
     */
    @Query("SELECT p FROM Permission p " +
           "WHERE (:name IS NULL OR p.name LIKE %:name%) " +
           "AND (:description IS NULL OR p.description LIKE %:description%) " +
           "AND (:status IS NULL OR p.status = :status)")
    List<Permission> findPermissionsWithFilters(
        @Param("name") String name,
        @Param("description") String description,
        @Param("status") Integer status
    );
    
    /**
     * Find permissions by role ID
     */
    @Query("SELECT DISTINCT p FROM Permission p " +
           "JOIN p.rolePermissions rp " +
           "WHERE rp.role.id = :roleId AND rp.status = 1")
    List<Permission> findPermissionsByRoleId(@Param("roleId") Long roleId);
    
    /**
     * Find permissions by user ID
     */
    @Query("SELECT DISTINCT p FROM Permission p " +
           "JOIN p.rolePermissions rp " +
           "JOIN rp.role r " +
           "JOIN r.userRoles ur " +
           "WHERE ur.user.id = :userId AND ur.status = 1 AND rp.status = 1")
    List<Permission> findPermissionsByUserId(@Param("userId") Long userId);
    
    /**
     * Find permissions by multiple names
     */
    List<Permission> findByNameIn(List<String> names);
    
    /**
     * Count permissions by status
     */
    long countByStatus(Integer status);
    
    /**
     * Find permissions created in date range
     */
    @Query("SELECT p FROM Permission p WHERE p.createdDate BETWEEN :startDate AND :endDate")
    List<Permission> findByCreatedDateBetween(
        @Param("startDate") java.sql.Timestamp startDate,
        @Param("endDate") java.sql.Timestamp endDate
    );
    
    /**
     * Find permissions by name pattern
     */
    List<Permission> findByNameContainingIgnoreCase(String namePattern);
    
    /**
     * Find permissions by category (e.g., user:, role:, etc.)
     */
    @Query("SELECT p FROM Permission p WHERE p.name LIKE :category%")
    List<Permission> findByCategory(@Param("category") String category);
} 