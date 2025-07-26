package project.foodflow.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.foodflow.constant.ReturnCode;
import project.foodflow.dto.PageData;
import project.foodflow.dto.ProductDto;
import project.foodflow.dto.Response;
import project.foodflow.entity.Category;
import project.foodflow.repository.ProductRepository;
import project.foodflow.service.CategoryService;
import project.foodflow.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/public")
public class ApiCommonController {
    @Autowired
    private ProductService productService;

    @Autowired
    CategoryService categoryService;
    @GetMapping("/products")
    public ResponseEntity<Response<PageData<ProductDto>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String name
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageData<ProductDto> products = productService.getAllProducts(pageable, categoryId, minPrice, maxPrice, sort, name);
        return ResponseEntity.ok(new Response<>(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getStatus(), "Lấy danh sách sản phẩm thành công", products));
    }


    @GetMapping("/categories")
    public ResponseEntity<Response<Page<Category>>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = categoryService.getAllCategories(pageable);
        return ResponseEntity.ok(new Response<>(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getStatus(), "Lấy danh sách loại sản phẩm thành công", categories));
    }

    /**
     * Lấy top 10 sản phẩm bán chạy nhất
     */
    @GetMapping("/products/top-selling")
    public ResponseEntity<Response<List<ProductDto>>> getTopSellingProducts() {
        try {
            List<ProductDto> topProducts = productService.getTopSellingProducts(10);
            return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(), 
                ReturnCode.SUCCESS.getStatus(), 
                "Lấy top 10 sản phẩm bán chạy thành công", 
                topProducts
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response<>(
                ReturnCode.ERROR.getCode(),
                ReturnCode.ERROR.getStatus(),
                "Lỗi khi lấy top sản phẩm bán chạy: " + e.getMessage(),
                null
            ));
        }
    }
    // api danh sách top 10 sản phẩm được đánh giá nhiều nhất
    @GetMapping("/products/top-reviewed")
    public ResponseEntity<Response<List<ProductDto>>> getTopReviewedProducts() {
        try {
            List<ProductDto> topReviewedProducts = productService.getTopReviewedProducts(10);
            return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(), 
                ReturnCode.SUCCESS.getStatus(), 
                "Lấy top 10 sản phẩm được đánh giá nhiều nhất thành công", 
                topReviewedProducts
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response<>(
                ReturnCode.ERROR.getCode(),
                ReturnCode.ERROR.getStatus(),
                "Lỗi khi lấy top sản phẩm được đánh giá nhiều nhất: " + e.getMessage(),
                null
            ));
        }
    }

    /**
     * Lấy top 10 sản phẩm có khuyến mãi
     */
    @GetMapping("/products/top-discounted")
    public ResponseEntity<Response<List<ProductDto>>> getTopDiscountedProducts() {
        try {
            List<ProductDto> topDiscountedProducts = productService.getTopDiscountedProducts(10);
            return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(), 
                ReturnCode.SUCCESS.getStatus(), 
                "Lấy top 10 sản phẩm có khuyến mãi thành công", 
                topDiscountedProducts
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response<>(
                ReturnCode.ERROR.getCode(),
                ReturnCode.ERROR.getStatus(),
                "Lỗi khi lấy top sản phẩm có khuyến mãi: " + e.getMessage(),
                null
            ));
        }
    }
} 