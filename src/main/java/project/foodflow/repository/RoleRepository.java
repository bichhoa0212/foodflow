package project.foodflow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.foodflow.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    /**
     * Find role by name
     */
    Optional<Role> findByName(String name);
    
    /**
     * Find roles by status
     */
    List<Role> findByStatus(Integer status);
    
    /**
     * Check if role name exists
     */
    boolean existsByName(String name);
    
    /**
     * Find roles by description containing text
     */
    List<Role> findByDescriptionContainingIgnoreCase(String description);
    
    /**
     * Find roles with pagination and search
     */
    @Query("SELECT r FROM Role r " +
           "WHERE (:name IS NULL OR r.name LIKE %:name%) " +
           "AND (:description IS NULL OR r.description LIKE %:description%) " +
           "AND (:status IS NULL OR r.status = :status)")
    List<Role> findRolesWithFilters(
        @Param("name") String name,
        @Param("description") String description,
        @Param("status") Integer status
    );
    
    /**
     * Find roles by user ID
     */
    @Query("SELECT DISTINCT r FROM Role r " +
           "JOIN r.userRoles ur " +
           "WHERE ur.user.id = :userId AND ur.status = 1")
    List<Role> findRolesByUserId(@Param("userId") Long userId);
    
    /**
     * Find roles by permission ID
     */
    @Query("SELECT DISTINCT r FROM Role r " +
           "JOIN r.rolePermissions rp " +
           "WHERE rp.permission.id = :permissionId AND rp.status = 1")
    List<Role> findRolesByPermissionId(@Param("permissionId") Long permissionId);
    
    /**
     * Count roles by status
     */
    long countByStatus(Integer status);
    
    /**
     * Find roles created in date range
     */
    @Query("SELECT r FROM Role r WHERE r.createdDate BETWEEN :startDate AND :endDate")
    List<Role> findByCreatedDateBetween(
        @Param("startDate") java.sql.Timestamp startDate,
        @Param("endDate") java.sql.Timestamp endDate
    );
    
    /**
     * Find roles by multiple names
     */
    List<Role> findByNameIn(List<String> names);
} 