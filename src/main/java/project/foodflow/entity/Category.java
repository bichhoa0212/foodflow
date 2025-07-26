package project.foodflow.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Entity đại diện cho danh mục sản phẩm
 * 
 * Mỗi danh mục có thể chứa nhiều sản phẩm khác nhau.
 * Thông tin bao gồm tên danh mục, mô tả, hình ảnh đại diện.
 * 
 * @author FoodFlow Team
 * @version 1.0
 */
@Entity
@Table(name = "categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity đại diện cho danh mục sản phẩm")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID duy nhất của danh mục sản phẩm")
    private Long id;

    @Schema(description = "Tên danh mục sản phẩm")
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Schema(description = "Mô tả chi tiết về danh mục sản phẩm")
    @Column(name = "description", length = 500)
    private String description;

    @Schema(description = "URL hình ảnh đại diện cho danh mục")
    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Schema(description = "Trạng thái hoạt động của danh mục: 1-ACTIVE, 0-INACTIVE")
    @Column(name = "status", nullable = false)
    private Integer status;

    /**
     * Danh sách sản phẩm thuộc danh mục này
     * Mối quan hệ One-to-Many với Product
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "Danh sách sản phẩm thuộc danh mục")
    @JsonIgnore
    private List<Product> products;
} 