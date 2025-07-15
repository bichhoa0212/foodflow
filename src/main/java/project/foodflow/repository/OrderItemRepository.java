package project.foodflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.foodflow.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
} 