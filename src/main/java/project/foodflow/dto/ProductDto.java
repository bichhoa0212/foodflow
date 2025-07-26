package project.foodflow.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private java.math.BigDecimal price;
    private Integer status;
    private Integer purchaseCount;
    private Integer reviewCount;
    private String discountType;
    private java.math.BigDecimal discountValue;
} 