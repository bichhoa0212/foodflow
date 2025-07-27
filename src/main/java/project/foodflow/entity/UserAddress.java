package project.foodflow.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entity đại diện cho địa chỉ giao hàng của người dùng
 * 
 * @author FoodFlow Team
 * @version 1.0
 * @since 2024-01-01
 */
@Entity
@Table(name = "user_addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
    
    /**
     * ID duy nhất của địa chỉ
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * Người dùng sở hữu địa chỉ này
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
    
    /**
     * Tên địa chỉ (ví dụ: Nhà riêng, Công ty)
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    /**
     * Số điện thoại liên hệ tại địa chỉ này
     */
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;
    
    /**
     * Địa chỉ chi tiết
     */
    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;
    
    /**
     * Tỉnh/Thành phố
     */
    @Column(name = "province", nullable = false, length = 100)
    private String province;
    
    /**
     * Quận/Huyện
     */
    @Column(name = "district", nullable = false, length = 100)
    private String district;
    
    /**
     * Phường/Xã
     */
    @Column(name = "ward", nullable = false, length = 100)
    private String ward;
    
    /**
     * Có phải địa chỉ mặc định không
     */
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;
    
    /**
     * Trạng thái hoạt động (1: hoạt động, 0: không hoạt động)
     */
    @Column(name = "status", nullable = false)
    private Integer status = 1;
    
    /**
     * Thời gian tạo
     */
    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;
    
    /**
     * Thời gian cập nhật cuối
     */
    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
} 