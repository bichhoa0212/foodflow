package project.foodflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID giao dịch thanh toán")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @Schema(description = "Đơn hàng liên quan")
    private Order order;

    @Column(nullable = false, length = 50)
    @Schema(description = "Cổng thanh toán (MOMO, VNPAY, COD, ...)")
    private String gateway;

    @Column(name = "gateway_transaction_id", length = 255)
    @Schema(description = "Mã giao dịch từ nhà cung cấp thanh toán")
    private String gatewayTransactionId;

    @Column(nullable = false, precision = 10, scale = 2)
    @Schema(description = "Số tiền giao dịch")
    private BigDecimal amount;

    @Column(name = "transaction_date", nullable = false)
    @Schema(description = "Thời điểm giao dịch")
    private LocalDateTime transactionDate;

    @Column(nullable = false)
    @Schema(description = "Trạng thái giao dịch: 1-Thành công, 2-Thất bại, 3-Đang xử lý")
    private Integer status;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Ghi chú giao dịch")
    private String notes;
} 