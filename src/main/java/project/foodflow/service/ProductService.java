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
} 