package com.retail.ordering.dto;

import com.retail.ordering.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private Product.Category category;
    private BigDecimal price;
    private Integer stock;
    private String description;
    private String imageUrl;
}
