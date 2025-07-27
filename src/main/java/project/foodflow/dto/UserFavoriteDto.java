package project.foodflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO cho sản phẩm yêu thích của người dùng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFavoriteDto {
    private Long id;
    private Long productId;
    private String productName;
    private String productDescription;
    private String productImageUrl;
    private BigDecimal productPrice;
    private String discountType;
    private BigDecimal discountValue;
    private Integer productStatus;
    private LocalDateTime createdDate;
} 