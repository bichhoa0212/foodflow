package project.foodflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.foodflow.entity.UserAddress;

import java.util.List;
import java.util.Optional;

/**
 * Repository cho UserAddress entity
 */
@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    
    /**
     * Tìm tất cả địa chỉ của một user
     */
    List<UserAddress> findByUserIdAndStatusOrderByIsDefaultDescCreatedDateDesc(Long userId, Integer status);
    
    /**
     * Tìm địa chỉ mặc định của user
     */
    Optional<UserAddress> findByUserIdAndIsDefaultTrueAndStatus(Long userId, Integer status);
    
    /**
     * Đếm số địa chỉ của user
     */
    long countByUserIdAndStatus(Long userId, Integer status);
    
    /**
     * Cập nhật tất cả địa chỉ của user thành không mặc định
     */
    @Modifying
    @Query("UPDATE UserAddress ua SET ua.isDefault = false WHERE ua.user.id = :userId AND ua.status = :status")
    void clearDefaultAddress(@Param("userId") Long userId, @Param("status") Integer status);
    
    /**
     * Đặt một địa chỉ làm mặc định
     */
    @Modifying
    @Query("UPDATE UserAddress ua SET ua.isDefault = true WHERE ua.id = :addressId")
    void setDefaultAddress(@Param("addressId") Long addressId);
} 