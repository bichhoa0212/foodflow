package project.foodflow.entity;

import jakarta.persistence.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "product_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID danh mục sản phẩm")
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    @Schema(description = "Tên danh mục")
    private String name;

    @Column(length = 500)
    @Schema(description = "Mô tả danh mục")
    private String description;

    @Column(name = "image_url", length = 500)
    @Schema(description = "URL ảnh danh mục")
    private String imageUrl;

    @Column(nullable = false)
    @Schema(description = "Trạng thái danh mục: 1-Hiện, 2-Ẩn")
    private Integer status;
} 