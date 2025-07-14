package project.foodflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "shipper_locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipperLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID vị trí shipper")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipper_id", nullable = false)
    @Schema(description = "Shipper")
    private Shipper shipper;

    @Column(nullable = false, precision = 10, scale = 8)
    @Schema(description = "Vĩ độ hiện tại của shipper")
    private BigDecimal latitude;

    @Column(nullable = false, precision = 11, scale = 8)
    @Schema(description = "Kinh độ hiện tại của shipper")
    private BigDecimal longitude;

    @Column(name = "last_updated", nullable = false)
    @Schema(description = "Thời điểm cập nhật vị trí cuối cùng")
    private LocalDateTime lastUpdated;
} 