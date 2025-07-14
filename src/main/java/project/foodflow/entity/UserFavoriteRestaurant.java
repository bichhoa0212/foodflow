package project.foodflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "user_favorite_restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserFavoriteRestaurant.PK.class)
public class UserFavoriteRestaurant {
    @Schema(description = "Người dùng yêu thích nhà hàng")
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Schema(description = "Nhà hàng được yêu thích")
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Schema(description = "Ngày thêm vào danh sách yêu thích")
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PK implements Serializable {
        private Long user;
        private Long restaurant;
    }
} 