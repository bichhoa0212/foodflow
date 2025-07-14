package project.foodflow.service;

import project.foodflow.entity.ProductCategory;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCategoryService {
    List<ProductCategory> getAllCategories();
    Page<ProductCategory> getAllCategories(Pageable pageable);
} 