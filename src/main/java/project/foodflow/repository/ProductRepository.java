package project.foodflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.foodflow.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findTop10ByOrderByPurchaseCountDesc();
    List<Product> findTop10ByOrderByReviewCountDesc();
    
    /**
     * Lấy top N sản phẩm bán chạy nhất
     * @param pageable phân trang với limit
     * @return List<Product> danh sách sản phẩm bán chạy
     */
    @Query(value = "SELECT * FROM products ORDER BY purchase_count DESC", nativeQuery = true)
    List<Product> findTopByOrderByPurchaseCountDesc(Pageable pageable);
    
    /**
     * Lấy tất cả sản phẩm để debug
     */
    @Query(value = "SELECT * FROM products", nativeQuery = true)
    List<Product> findAllProducts();
    
    /**
     * Lấy top N sản phẩm được đánh giá nhiều nhất
     * @param pageable phân trang với limit
     * @return List<Product> danh sách sản phẩm được đánh giá nhiều
     */
    List<Product> findTopByOrderByReviewCountDesc(Pageable pageable);
    
    /**
     * Lấy top N sản phẩm có khuyến mãi (có discount_type và discount_value)
     * @param pageable phân trang với limit
     * @return List<Product> danh sách sản phẩm có khuyến mãi
     */
    @Query(value = "SELECT * FROM products WHERE discount_type IS NOT NULL AND discount_value IS NOT NULL ORDER BY discount_value DESC", nativeQuery = true)
    List<Product> findTopByDiscountValueDesc(Pageable pageable);
} 