package project.foodflow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.foodflow.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Page<Category> getAllCategories(Pageable pageable);
} 