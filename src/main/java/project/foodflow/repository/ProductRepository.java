package project.foodflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.foodflow.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findTop10ByOrderByPurchaseCountDesc();
    List<Product> findTop10ByOrderByReviewCountDesc();
    java.util.List<Product> findByRestaurantId(Long restaurantId);
} 