package project.foodflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import project.foodflow.dto.PageData;
import project.foodflow.dto.ProductDto;
import project.foodflow.entity.Product;
import project.foodflow.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // trả về dto thông tin sản phẩm thôi, k cần trả về các thông tin khác  
    @Override
    public PageData<ProductDto> getAllProducts(Pageable pageable, Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, String sort, String name) {
        Specification<Product> spec = (root, query, cb) -> {
            var predicates = new java.util.ArrayList<jakarta.persistence.criteria.Predicate>();
            if (categoryId != null) predicates.add(cb.equal(root.get("category").get("id"), categoryId));
            if (minPrice != null) predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            if (maxPrice != null) predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            if (name != null && !name.isEmpty()) predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            return cb.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        };
        // Xử lý sort
        Pageable sortedPageable = pageable;
        if (sort != null) {
            org.springframework.data.domain.Sort sortObj = null;
            switch (sort) {
                case "price_asc": sortObj = org.springframework.data.domain.Sort.by("price").ascending(); break;
                case "price_desc": sortObj = org.springframework.data.domain.Sort.by("price").descending(); break;
                case "name_asc": sortObj = org.springframework.data.domain.Sort.by("name").ascending(); break;
                case "name_desc": sortObj = org.springframework.data.domain.Sort.by("name").descending(); break;
                default: break;
            }
            if (sortObj != null) {
                sortedPageable = org.springframework.data.domain.PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortObj);
            }
        }
        var page = productRepository.findAll(spec, sortedPageable);
        var dtoList = page.getContent().stream().map(product -> {
            var dto = new project.foodflow.dto.ProductDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setImageUrl(product.getImageUrl());
            dto.setPrice(product.getPrice());
            dto.setStatus(product.getStatus());
            return dto;
        }).collect(java.util.stream.Collectors.toList());
        return new project.foodflow.dto.PageData<>(
            dtoList,
            dtoList.size(),
            page.getTotalElements(),
            page.getNumber(),
            page.getSize()
        );
    }

    @Override
    public List<ProductDto> getTopSellingProducts(int limit) {
        // Lấy top N sản phẩm theo purchase_count giảm dần
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(0, limit);
        List<Product> topProducts = productRepository.findTopByOrderByPurchaseCountDesc(pageable);
        
        // Debug logging
        System.out.println("Limit requested: " + limit);
        System.out.println("Pageable: " + pageable);
        System.out.println("Products found: " + topProducts.size());
        topProducts.forEach(p -> System.out.println("Product: " + p.getName() + " - Purchase count: " + p.getPurchaseCount()));

        return topProducts.stream().map(product -> {
            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setImageUrl(product.getImageUrl());
            dto.setPrice(product.getPrice());
            dto.setStatus(product.getStatus());
            dto.setPurchaseCount(product.getPurchaseCount());
            dto.setReviewCount(product.getReviewCount());
            dto.setDiscountType(product.getDiscountType());
            dto.setDiscountValue(product.getDiscountValue());
            return dto;
        }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<ProductDto> getTopReviewedProducts(int limit) {
        // Lấy top N sản phẩm theo review_count giảm dần
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(0, limit);
        List<Product> topProducts = productRepository.findTopByOrderByReviewCountDesc(pageable);
        
        return topProducts.stream().map(product -> {
            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setImageUrl(product.getImageUrl());
            dto.setPrice(product.getPrice());
            dto.setStatus(product.getStatus());
            dto.setPurchaseCount(product.getPurchaseCount());
            dto.setReviewCount(product.getReviewCount());
            dto.setDiscountType(product.getDiscountType());
            dto.setDiscountValue(product.getDiscountValue());
            return dto;
        }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<ProductDto> getTopDiscountedProducts(int limit) {
        // Lấy top N sản phẩm có khuyến mãi theo discount_value giảm dần
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(0, limit);
        List<Product> topProducts = productRepository.findTopByDiscountValueDesc(pageable);
        
        return topProducts.stream().map(product -> {
            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setImageUrl(product.getImageUrl());
            dto.setPrice(product.getPrice());
            dto.setStatus(product.getStatus());
            dto.setPurchaseCount(product.getPurchaseCount());
            dto.setReviewCount(product.getReviewCount());
            dto.setDiscountType(product.getDiscountType());
            dto.setDiscountValue(product.getDiscountValue());
            return dto;
        }).collect(java.util.stream.Collectors.toList());
    }
} 