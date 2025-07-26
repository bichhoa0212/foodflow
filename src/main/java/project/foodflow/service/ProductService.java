package project.foodflow.service;

import project.foodflow.entity.Product;
import project.foodflow.dto.ProductDto;
import project.foodflow.dto.PageData;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    List<Product> getAllProducts();
    PageData<ProductDto> getAllProducts(Pageable pageable, Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, String sort, String name);
    
    /**
     * Lấy top N sản phẩm bán chạy nhất
     * @param limit số lượng sản phẩm cần lấy
     * @return List<ProductDto> danh sách sản phẩm bán chạy
     */
    List<ProductDto> getTopSellingProducts(int limit);
    
    /**
     * Lấy top N sản phẩm được đánh giá nhiều nhất
     * @param limit số lượng sản phẩm cần lấy
     * @return List<ProductDto> danh sách sản phẩm được đánh giá nhiều
     */
    List<ProductDto> getTopReviewedProducts(int limit);
    
    /**
     * Lấy top N sản phẩm có khuyến mãi
     * @param limit số lượng sản phẩm cần lấy
     * @return List<ProductDto> danh sách sản phẩm có khuyến mãi
     */
    List<ProductDto> getTopDiscountedProducts(int limit);
} 