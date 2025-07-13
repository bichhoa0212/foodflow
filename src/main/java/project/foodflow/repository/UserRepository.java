package project.foodflow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.foodflow.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find user by email
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Find user by phone number
     */
    Optional<User> findByPhone(String phone);
    
    /**
     * Find user by email or phone
     */
    Optional<User> findByEmailOrPhone(String email, String phone);
    
    /**
     * Find users by status
     */
    List<User> findByStatus(Integer status);
    
    /**
     * Find users by provider
     */
    List<User> findByProvider(String provider);
    
    /**
     * Find user by provider and provider user ID
     */
    Optional<User> findByProviderAndProviderUserId(String provider, String providerUserId);
    
    /**
     * Check if email exists
     */
    boolean existsByEmail(String email);
    
    /**
     * Check if phone exists
     */
    boolean existsByPhone(String phone);
    
    /**
     * Find users by role name
     */
    @Query("SELECT DISTINCT u FROM User u " +
           "JOIN u.userRoles ur " +
           "JOIN ur.role r " +
           "WHERE r.name = :roleName")
    List<User> findByRoleName(@Param("roleName") String roleName);
    
    /**
     * Find users by group name
     */
    @Query("SELECT DISTINCT u FROM User u " +
           "JOIN u.groupUsers gu " +
           "JOIN gu.group g " +
           "WHERE g.name = :groupName")
    List<User> findByGroupName(@Param("groupName") String groupName);
    
    /**
     * Find users with pagination and search
     */
    @Query("SELECT u FROM User u " +
           "WHERE (:name IS NULL OR u.name LIKE %:name%) " +
           "AND (:email IS NULL OR u.email LIKE %:email%) " +
           "AND (:phone IS NULL OR u.phone LIKE %:phone%) " +
           "AND (:status IS NULL OR u.status = :status)")
    List<User> findUsersWithFilters(
        @Param("name") String name,
        @Param("email") String email,
        @Param("phone") String phone,
        @Param("status") Integer status
    );
    
    /**
     * Count users by status
     */
    long countByStatus(Integer status);
    
    /**
     * Find users created in date range
     */
    @Query("SELECT u FROM User u WHERE u.createdDate BETWEEN :startDate AND :endDate")
    List<User> findByCreatedDateBetween(
        @Param("startDate") java.sql.Timestamp startDate,
        @Param("endDate") java.sql.Timestamp endDate
    );

    /**
     * Check if user exists by provider user ID, email, or phone
     */
    boolean existsByProviderUserIdOrEmailOrPhone(String providerUserId, String email, String phone);
    
    /**
     * Check if user exists by email or phone
     */
} 