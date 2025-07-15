package project.foodflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.foodflow.entity.Restaurant;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findTop10ByOrderByPurchaseCountDesc();
    List<Restaurant> findTop10ByOrderByReviewCountDesc();
} 