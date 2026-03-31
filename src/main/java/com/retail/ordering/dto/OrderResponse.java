package com.retail.ordering.dto;

import com.retail.ordering.entity.Order;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private BigDecimal totalAmount;
    private Order.OrderStatus status;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;

    @Data
    @Builder
    public static class OrderItemResponse {
        private Long itemId;
        private Long productId;
        private String productName;
        private String category;
        private Integer quantity;
        private BigDecimal price;
        private BigDecimal subtotal;
    }
}
