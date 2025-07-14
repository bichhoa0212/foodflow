package project.foodflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.foodflow.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
} 