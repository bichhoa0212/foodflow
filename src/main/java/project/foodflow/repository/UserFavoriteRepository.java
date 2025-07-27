package project.foodflow.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.foodflow.entity.UserFavorite;

import java.util.List;
import java.util.Optional;

/**
 * Repository cho UserFavorite entity
 */
@Repository
public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Long> {
    
    /**
     * Tìm tất cả sản phẩm yêu thích của user với phân trang
     */
    Page<UserFavorite> findByUserIdAndStatusOrderByCreatedDateDesc(Long userId, Integer status, Pageable pageable);
    
    /**
     * Tìm tất cả sản phẩm yêu thích của user
     */
    List<UserFavorite> findByUserIdAndStatusOrderByCreatedDateDesc(Long userId, Integer status);
    
    /**
     * Kiểm tra xem user đã yêu thích sản phẩm chưa
     */
    Optional<UserFavorite> findByUserIdAndProductIdAndStatus(Long userId, Long productId, Integer status);
    
    /**
     * Đếm số sản phẩm yêu thích của user
     */
    long countByUserIdAndStatus(Long userId, Integer status);
    
    /**
     * Tìm sản phẩm yêu thích với thông tin sản phẩm đầy đủ
     */
    @Query("SELECT uf FROM UserFavorite uf " +
           "JOIN FETCH uf.product p " +
           "WHERE uf.user.id = :userId AND uf.status = :status " +
           "ORDER BY uf.createdDate DESC")
    List<UserFavorite> findFavoritesWithProduct(@Param("userId") Long userId, @Param("status") Integer status);
} 