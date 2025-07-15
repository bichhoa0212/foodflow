package project.foodflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.foodflow.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
} 