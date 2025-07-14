package project.foodflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID chi tiết đơn hàng")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @Schema(description = "Đơn hàng")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @Schema(description = "Sản phẩm")
    private Product product;

    @Column(nullable = false)
    @Schema(description = "Số lượng")
    private Integer quantity;

    @Column(name = "price_at_order", nullable = false, precision = 10, scale = 2)
    @Schema(description = "Giá sản phẩm tại thời điểm đặt hàng")
    private BigDecimal priceAtOrder;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    @Schema(description = "Tổng tiền cho sản phẩm này")
    private BigDecimal totalPrice;
} 