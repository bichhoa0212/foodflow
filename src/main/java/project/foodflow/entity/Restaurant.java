package project.foodflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Schema(description = "ID nhà hàng")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Tên nhà hàng")
    @Column(nullable = false)
    private String name;

    @Schema(description = "Địa chỉ nhà hàng")
    @Column(nullable = false, length = 500)
    private String address;

    @Schema(description = "Số điện thoại nhà hàng")
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Schema(description = "URL logo nhà hàng")
    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @Schema(description = "URL ảnh bìa nhà hàng")
    @Column(name = "cover_image_url", length = 500)
    private String coverImageUrl;

    @Schema(description = "Mô tả nhà hàng")
    @Column(columnDefinition = "TEXT")
    private String description;

    @Schema(description = "Giờ mở cửa")
    @Column(name = "opening_time")
    private LocalTime openingTime;

    @Schema(description = "Giờ đóng cửa")
    @Column(name = "closing_time")
    private LocalTime closingTime;

    @Schema(description = "Chủ sở hữu nhà hàng (User)")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Schema(description = "Điểm đánh giá trung bình")
    @Column(precision = 3, scale = 2)
    private BigDecimal rating;

    @Schema(description = "Ngày tạo")
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Schema(description = "Ngày cập nhật")
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Schema(description = "Trạng thái nhà hàng: 1-Mở, 2-Đóng, 3-Tạm đóng")
    @Column(nullable = false)
    private Integer status;
} 