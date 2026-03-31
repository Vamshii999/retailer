package com.retail.ordering.controller;

import com.retail.ordering.dto.OrderResponse;
import com.retail.ordering.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Orders", description = "Order management APIs")
@SecurityRequirement(name = "Bearer Authentication")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Place a new order from cart")
    public ResponseEntity<OrderResponse> placeOrder(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("POST /orders - user: {}", userDetails.getUsername());
        OrderResponse response = orderService.placeOrder(userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Get order history for current user")
    public ResponseEntity<List<OrderResponse>> getOrderHistory(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("GET /orders - user: {}", userDetails.getUsername());
        return ResponseEntity.ok(orderService.getOrderHistory(userDetails.getUsername()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get specific order details")
    public ResponseEntity<OrderResponse> getOrderById(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id) {
        log.info("GET /orders/{} - user: {}", id, userDetails.getUsername());
        return ResponseEntity.ok(orderService.getOrderById(userDetails.getUsername(), id));
    }
}
