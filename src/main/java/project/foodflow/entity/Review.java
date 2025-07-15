package project.foodflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Schema(description = "ID đánh giá")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Người đánh giá")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Schema(description = "Đơn hàng được đánh giá")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Schema(description = "Nhà hàng được đánh giá")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Schema(description = "Sản phẩm được đánh giá")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Schema(description = "Số sao đánh giá (1-5)")
    @Column(nullable = false)
    private Integer rating;

    @Schema(description = "Nội dung bình luận")
    @Column(columnDefinition = "TEXT")
    private String comment;

    @Schema(description = "Ngày tạo đánh giá")
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;
} 