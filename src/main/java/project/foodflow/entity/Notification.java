package project.foodflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID thông báo")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "Người nhận thông báo")
    private User user;

    @Column(nullable = false, length = 255)
    @Schema(description = "Tiêu đề thông báo")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Schema(description = "Nội dung thông báo")
    private String message;

    @Column(length = 50)
    @Schema(description = "Loại thông báo (ORDER_STATUS, PROMOTION, SYSTEM, ...)")
    private String type;

    @Column(name = "target_id")
    @Schema(description = "ID đối tượng liên quan (order_id, promotion_id, ...)")
    private Long targetId;

    @Column(name = "is_read")
    @Schema(description = "Đã đọc hay chưa")
    private Boolean isRead;

    @Column(name = "created_date", nullable = false)
    @Schema(description = "Ngày tạo thông báo")
    private LocalDateTime createdDate;
} 