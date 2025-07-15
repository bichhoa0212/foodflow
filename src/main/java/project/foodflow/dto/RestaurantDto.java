package project.foodflow.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RestaurantDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String logoUrl;
    private String coverImageUrl;
    private String description;
    private BigDecimal rating;
    private Integer status;
    private Integer purchaseCount;
    private Integer reviewCount;
} 