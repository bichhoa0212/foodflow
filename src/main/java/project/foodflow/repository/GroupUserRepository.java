package project.foodflow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.foodflow.entity.GroupUser;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {
    
    /**
     * Find group users by group ID
     */
    List<GroupUser> findByGroupId(Long groupId);
    
    /**
     * Find group users by user ID
     */
    List<GroupUser> findByUserId(Long userId);
    
    /**
     * Find group user by group ID and user ID
     */
    Optional<GroupUser> findByGroupIdAndUserId(Long groupId, Long userId);
    
    /**
     * Find active group users by group ID
     */
    List<GroupUser> findByGroupIdAndStatus(Long groupId, Integer status);
    
    /**
     * Find active group users by user ID
     */
    List<GroupUser> findByUserIdAndStatus(Long userId, Integer status);
    
    /**
     * Check if user is in group
     */
    boolean existsByGroupIdAndUserIdAndStatus(Long groupId, Long userId, Integer status);
    
    /**
     * Count group users by group ID
     */
    long countByGroupId(Long groupId);
    
    /**
     * Count group users by user ID
     */
    long countByUserId(Long userId);
    
    /**
     * Count active group users by group ID
     */
    long countByGroupIdAndStatus(Long groupId, Integer status);
    
    /**
     * Find group users by group name
     */
    @Query("SELECT gu FROM GroupUser gu " +
           "JOIN gu.group g " +
           "WHERE g.name = :groupName AND gu.status = 1")
    List<GroupUser> findByGroupName(@Param("groupName") String groupName);
    
    /**
     * Find group users by user email
     */
    @Query("SELECT gu FROM GroupUser gu " +
           "JOIN gu.user u " +
           "WHERE u.email = :email AND gu.status = 1")
    List<GroupUser> findByUserEmail(@Param("email") String email);
    
    /**
     * Find group users by group name and user email
     */
    @Query("SELECT gu FROM GroupUser gu " +
           "JOIN gu.group g " +
           "JOIN gu.user u " +
           "WHERE g.name = :groupName AND u.email = :email")
    List<GroupUser> findByGroupNameAndUserEmail(
        @Param("groupName") String groupName,
        @Param("email") String email
    );
    
    /**
     * Find group users with group and user details
     */
    @Query("SELECT gu FROM GroupUser gu " +
           "JOIN FETCH gu.group g " +
           "JOIN FETCH gu.user u " +
           "WHERE gu.status = 1")
    List<GroupUser> findAllActiveWithGroupAndUser();
    
    /**
     * Find group users by group ID with user details
     */
    @Query("SELECT gu FROM GroupUser gu " +
           "JOIN FETCH gu.user u " +
           "WHERE gu.groupId = :groupId AND gu.status = 1")
    List<GroupUser> findByGroupIdWithUser(@Param("groupId") Long groupId);
    
    /**
     * Find group users by user ID with group details
     */
    @Query("SELECT gu FROM GroupUser gu " +
           "JOIN FETCH gu.group g " +
           "WHERE gu.userId = :userId AND gu.status = 1")
    List<GroupUser> findByUserIdWithGroup(@Param("userId") Long userId);
    
    /**
     * Find users by group ID (returns user IDs)
     */
    @Query("SELECT gu.userId FROM GroupUser gu " +
           "WHERE gu.groupId = :groupId AND gu.status = 1")
    List<Long> findUserIdsByGroupId(@Param("groupId") Long groupId);
    
    /**
     * Find groups by user ID (returns group IDs)
     */
    @Query("SELECT gu.groupId FROM GroupUser gu " +
           "WHERE gu.userId = :userId AND gu.status = 1")
    List<Long> findGroupIdsByUserId(@Param("userId") Long userId);
    
    /**
     * Find group names by user ID
     */
    @Query("SELECT g.name FROM GroupUser gu " +
           "JOIN gu.group g " +
           "WHERE gu.userId = :userId AND gu.status = 1")
    List<String> findGroupNamesByUserId(@Param("userId") Long userId);
    
    /**
     * Find user emails by group ID
     */
    @Query("SELECT u.email FROM GroupUser gu " +
           "JOIN gu.user u " +
           "WHERE gu.groupId = :groupId AND gu.status = 1")
    List<String> findUserEmailsByGroupId(@Param("groupId") Long groupId);
    
    /**
     * Delete group users by group ID
     */
    void deleteByGroupId(Long groupId);
    
    /**
     * Delete group users by user ID
     */
    void deleteByUserId(Long userId);
    
    /**
     * Delete specific group user
     */
    void deleteByGroupIdAndUserId(Long groupId, Long userId);
    
    /**
     * Find group users created in date range
     */
    @Query("SELECT gu FROM GroupUser gu WHERE gu.createdDate BETWEEN :startDate AND :endDate")
    List<GroupUser> findByCreatedDateBetween(
        @Param("startDate") java.sql.Timestamp startDate,
        @Param("endDate") java.sql.Timestamp endDate
    );
    
    /**
     * Find group users by role name
     */
    @Query("SELECT gu FROM GroupUser gu " +
           "JOIN gu.group g " +
           "JOIN g.role r " +
           "WHERE r.name = :roleName AND gu.status = 1")
    List<GroupUser> findByRoleName(@Param("roleName") String roleName);
} 