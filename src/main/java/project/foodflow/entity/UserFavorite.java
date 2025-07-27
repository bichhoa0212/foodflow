package project.foodflow.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entity đại diện cho sản phẩm yêu thích của người dùng
 * 
 * @author FoodFlow Team
 * @version 1.0
 * @since 2024-01-01
 */
@Entity
@Table(name = "user_favorites")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFavorite {
    
    /**
     * ID duy nhất của bản ghi yêu thích
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * Người dùng yêu thích sản phẩm
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
    
    /**
     * Sản phẩm được yêu thích
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;
    
    /**
     * Trạng thái hoạt động (1: hoạt động, 0: không hoạt động)
     */
    @Column(name = "status", nullable = false)
    private Integer status = 1;
    
    /**
     * Thời gian thêm vào yêu thích
     */
    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;
} 