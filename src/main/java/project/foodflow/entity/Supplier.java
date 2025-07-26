package project.foodflow.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity đại diện cho nhà cung cấp sản phẩm
 * 
 * Mỗi supplier có thể cung cấp nhiều sản phẩm khác nhau.
 * Thông tin bao gồm tên, thông tin liên hệ, địa chỉ, số điện thoại, email.
 * 
 * @author FoodFlow Team
 * @version 1.0
 */
@Entity
@Table(name = "suppliers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity đại diện cho nhà cung cấp sản phẩm")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID duy nhất của nhà cung cấp")
    private Long id;

    @Schema(description = "Tên nhà cung cấp")
    @Column(name = "name", nullable = false, unique = true, length = 255)
    private String name;

    @Schema(description = "Thông tin liên hệ chi tiết của nhà cung cấp")
    @Column(name = "contact_info", length = 500)
    private String contactInfo;

    @Schema(description = "Địa chỉ của nhà cung cấp")
    @Column(name = "address", length = 255)
    private String address;

    @Schema(description = "Số điện thoại liên hệ của nhà cung cấp")
    @Column(name = "phone", length = 100)
    private String phone;

    @Schema(description = "Email liên hệ của nhà cung cấp")
    @Column(name = "email", length = 100)
    private String email;

    @Schema(description = "Ngày tạo nhà cung cấp")
    @CreationTimestamp
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Schema(description = "Ngày cập nhật thông tin nhà cung cấp")
    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Schema(description = "Trạng thái hoạt động của nhà cung cấp: 1-ACTIVE, 0-INACTIVE")
    @Column(name = "status", nullable = false)
    private Integer status;

    /**
     * Danh sách sản phẩm được cung cấp bởi nhà cung cấp này
     * Mối quan hệ One-to-Many với Product
     */
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "Danh sách sản phẩm được cung cấp bởi nhà cung cấp")
    @JsonIgnore
    private List<Product> products;
} 