package project.foodflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.foodflow.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
} 