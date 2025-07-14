package project.foodflow.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import project.foodflow.entity.Product;
import project.foodflow.entity.ProductCategory;
import project.foodflow.service.ProductService;
import project.foodflow.service.ProductCategoryService;
import java.util.List;
import project.foodflow.dto.Response;
import org.springframework.http.ResponseEntity;
import project.foodflow.constant.ReturnCode;
import project.foodflow.dto.ProductDto;
import project.foodflow.dto.PageData;
import java.util.stream.Collectors;
import project.foodflow.repository.ProductRepository;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/public")
public class ApiCommonController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductRepository productRepository;

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
    public ResponseEntity<Response<Page<ProductCategory>>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductCategory> categories = productCategoryService.getAllCategories(pageable);
        return ResponseEntity.ok(new Response<>(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getStatus(), "Lấy danh sách loại sản phẩm thành công", categories));
    }
} 