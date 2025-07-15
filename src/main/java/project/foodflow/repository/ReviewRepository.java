package project.foodflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.foodflow.entity.Review;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRestaurantId(Long restaurantId);
    Page<Review> findByRestaurantId(Long restaurantId, Pageable pageable);
    Page<Review> findByRestaurantIdAndRating(Long restaurantId, Integer rating, Pageable pageable);
    Page<Review> findByProductId(Long productId, Pageable pageable);
    Page<Review> findByProductIdAndRating(Long productId, Integer rating, Pageable pageable);
} 