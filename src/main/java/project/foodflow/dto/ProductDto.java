package project.foodflow.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private Integer status;
    private Integer stock;
    private Integer purchaseCount;
    private Integer reviewCount;
    private String discountType;
    private Double discountValue;
    private Long categoryId;
    private String categoryName;
    private Double rating;
} 