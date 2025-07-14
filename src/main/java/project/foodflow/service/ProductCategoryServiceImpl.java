package project.foodflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.foodflow.entity.ProductCategory;
import project.foodflow.repository.ProductCategoryRepository;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public Page<ProductCategory> getAllCategories(Pageable pageable) {
        return productCategoryRepository.findAll(pageable);
    }
} 