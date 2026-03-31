package com.retail.ordering.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CartResponse {
    private Long cartId;
    private List<CartItemResponse> items;
    private BigDecimal totalAmount;

    @Data
    @Builder
    public static class CartItemResponse {
        private Long itemId;
        private Long productId;
        private String productName;
        private String category;
        private BigDecimal price;
        private Integer quantity;
        private BigDecimal subtotal;
        private String imageUrl;
    }
}
