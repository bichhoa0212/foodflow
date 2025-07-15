package project.foodflow.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private List<OrderItemRequest> items;
    private String deliveryAddress;
    private String contactPhone;
    private String notes;

    @Data
    public static class OrderItemRequest {
        private Long productId;
        private Integer quantity;
    }
} 