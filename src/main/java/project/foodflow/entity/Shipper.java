package project.foodflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "shippers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID shipper")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @Schema(description = "Tài khoản người dùng shipper")
    private User user;

    @Column(name = "license_plate", nullable = false, length = 20)
    @Schema(description = "Biển số xe")
    private String licensePlate;

    @Column(name = "vehicle_type", length = 50)
    @Schema(description = "Loại phương tiện (Motorbike, Bicycle, ...)")
    private String vehicleType;

    @Column(precision = 3, scale = 2)
    @Schema(description = "Điểm đánh giá trung bình của shipper")
    private BigDecimal rating;

    @Column(name = "created_date", nullable = false)
    @Schema(description = "Ngày tạo tài khoản shipper")
    private LocalDateTime createdDate;

    @Column(nullable = false)
    @Schema(description = "Trạng thái shipper: 1-Offline, 2-Online, 3-Bận/Đang giao hàng")
    private Integer status;
} 