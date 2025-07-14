package project.foodflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

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

    @Schema(description = "Nhà hàng cung cấp sản phẩm")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Schema(description = "Danh mục sản phẩm")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

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