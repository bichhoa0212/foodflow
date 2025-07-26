package project.foodflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Schema(description = "ID sản phẩm")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Tên sản phẩm")
    @Column(nullable = false)
    private String name;

    @Schema(description = "Mô tả sản phẩm")
    @Column(columnDefinition = "TEXT")
    private String description;

    @Schema(description = "URL ảnh sản phẩm")
    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Schema(description = "Giá sản phẩm")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Schema(description = "Lượt mua sản phẩm")
    @Column(name = "purchase_count", nullable = false)
    private Integer purchaseCount = 0;

    @Schema(description = "Lượt đánh giá sản phẩm")
    @Column(name = "review_count", nullable = false)
    private Integer reviewCount = 0;

    @Schema(description = "Số lượng tồn kho sản phẩm")
    @Column(name = "stock", nullable = false)
    private Integer stock = 0;

    @Schema(description = "Kiểu giảm giá sản phẩm: PERCENTAGE hoặc FIXED_AMOUNT")
    @Column(name = "discount_type", length = 20)
    private String discountType;

    @Schema(description = "Giá trị giảm giá sản phẩm")
    @Column(name = "discount_value", precision = 10, scale = 2)
    private BigDecimal discountValue;

    @Schema(description = "Ngày bắt đầu giảm giá sản phẩm")
    @Column(name = "discount_start_date")
    private LocalDateTime discountStartDate;

    @Schema(description = "Ngày kết thúc giảm giá sản phẩm")
    @Column(name = "discount_end_date")
    private LocalDateTime discountEndDate;

    @Schema(description = "Nhà cung cấp sản phẩm")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    @JsonIgnore
    private Supplier supplier;

    @Schema(description = "Danh mục sản phẩm")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    @Schema(description = "Danh sách khuyến mãi áp dụng cho sản phẩm")
    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private java.util.List<Promotion> promotions;

    @Schema(description = "Ngày tạo sản phẩm")
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Schema(description = "Ngày cập nhật sản phẩm")
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Schema(description = "Trạng thái sản phẩm: 1-Còn hàng, 2-Hết hàng")
    @Column(nullable = false)
    private Integer status;
} 