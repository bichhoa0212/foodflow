package project.foodflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Schema(description = "ID đơn hàng")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Mã đơn hàng (ví dụ: HD-110725)")
    @Column(name = "order_code", nullable = false, unique = true, length = 20)
    private String orderCode;

    @Schema(description = "Người đặt hàng")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Schema(description = "Nhà hàng")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Schema(description = "Địa chỉ giao hàng")
    @Column(name = "delivery_address", nullable = false, length = 500)
    private String deliveryAddress;

    @Schema(description = "Số điện thoại liên hệ")
    @Column(name = "contact_phone", nullable = false, length = 20)
    private String contactPhone;

    @Schema(description = "Ghi chú đơn hàng")
    @Column(columnDefinition = "TEXT")
    private String notes;

    @Schema(description = "Tổng tiền trước giảm giá")
    @Column(name = "subtotal_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotalAmount;

    @Schema(description = "Phí vận chuyển")
    @Column(name = "shipping_fee", precision = 10, scale = 2)
    private BigDecimal shippingFee;

    @Schema(description = "Số tiền giảm giá")
    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @Schema(description = "Tổng tiền thanh toán cuối cùng")
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Schema(description = "Phương thức thanh toán (COD, Credit Card, MoMo, ...)")
    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @Schema(description = "Ngày đặt hàng")
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Schema(description = "Ngày hoàn thành đơn hàng")
    @Column(name = "completed_date")
    private LocalDateTime completedDate;

    @Schema(description = "Trạng thái đơn hàng: 1-Chờ xác nhận, 2-Đã xác nhận, 3-Đang chuẩn bị, 4-Đang giao, 5-Hoàn thành, 6-Đã hủy")
    @Column(nullable = false)
    private Integer status;

    @Schema(description = "Khuyến mãi áp dụng")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @Schema(description = "Shipper giao hàng")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;
} 