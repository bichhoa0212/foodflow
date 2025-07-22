package project.foodflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "promotions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promotion {
    @Schema(description = "ID khuyến mãi")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Mã code khuyến mãi")
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Schema(description = "Mô tả khuyến mãi")
    @Column(columnDefinition = "TEXT")
    private String description;

    @Schema(description = "Loại giảm giá: PERCENTAGE hoặc FIXED_AMOUNT")
    @Column(name = "discount_type", nullable = false, length = 20)
    private String discountType;

    @Schema(description = "Giá trị giảm giá")
    @Column(name = "discount_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal discountValue;

    @Schema(description = "Số tiền giảm tối đa (nếu giảm theo phần trăm)")
    @Column(name = "max_discount_amount", precision = 10, scale = 2)
    private BigDecimal maxDiscountAmount;

    @Schema(description = "Giá trị đơn hàng tối thiểu để áp dụng")
    @Column(name = "min_order_value", precision = 10, scale = 2)
    private BigDecimal minOrderValue;

    @Schema(description = "Ngày bắt đầu áp dụng")
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Schema(description = "Ngày kết thúc áp dụng")
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Schema(description = "Số lượt sử dụng tối đa")
    @Column(name = "usage_limit")
    private Integer usageLimit;

    @Schema(description = "Số lượt đã sử dụng")
    @Column(name = "usage_count")
    private Integer usageCount;

    @Schema(description = "Ngày tạo khuyến mãi")
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Schema(description = "Trạng thái: 1-Đang hoạt động, 2-Hết hạn, 3-Vô hiệu hóa")
    @Column(nullable = false)
    private Integer status;

    @Column(name = "type", length = 20)
    private String type = "ORDER";

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "priority")
    private Integer priority = 0;

    @Column(name = "image_url", length = 500)
    private String imageUrl;
} 