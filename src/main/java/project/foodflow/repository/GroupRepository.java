package project.foodflow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.foodflow.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    
    /**
     * Find group by name
     */
    Optional<Group> findByName(String name);
    
    /**
     * Find groups by status
     */
    List<Group> findByStatus(Integer status);
    
    /**
     * Find groups by role ID
     */
    List<Group> findByRoleId(Long roleId);
    
    /**
     * Check if group name exists
     */
    boolean existsByName(String name);
    
    /**
     * Find groups with pagination and search
     */
    @Query("SELECT g FROM Group g " +
           "WHERE (:name IS NULL OR g.name LIKE %:name%) " +
           "AND (:roleId IS NULL OR g.roleId = :roleId) " +
           "AND (:status IS NULL OR g.status = :status)")
    List<Group> findGroupsWithFilters(
        @Param("name") String name,
        @Param("roleId") Long roleId,
        @Param("status") Integer status
    );
    
    /**
     * Find groups by user ID
     */
    @Query("SELECT DISTINCT g FROM Group g " +
           "JOIN g.groupUsers gu " +
           "WHERE gu.user.id = :userId AND gu.status = 1")
    List<Group> findGroupsByUserId(@Param("userId") Long userId);
    
    /**
     * Find groups by role name
     */
    @Query("SELECT g FROM Group g " +
           "JOIN g.role r " +
           "WHERE r.name = :roleName")
    List<Group> findGroupsByRoleName(@Param("roleName") String roleName);
    
    /**
     * Count groups by status
     */
    long countByStatus(Integer status);
    
    /**
     * Count groups by role ID
     */
    long countByRoleId(Long roleId);
    
    /**
     * Find groups created in date range
     */
    @Query("SELECT g FROM Group g WHERE g.createdDate BETWEEN :startDate AND :endDate")
    List<Group> findByCreatedDateBetween(
        @Param("startDate") java.sql.Timestamp startDate,
        @Param("endDate") java.sql.Timestamp endDate
    );
    
    /**
     * Find groups by multiple names
     */
    List<Group> findByNameIn(List<String> names);
    
    /**
     * Find groups by name containing text
     */
    List<Group> findByNameContainingIgnoreCase(String name);
} 