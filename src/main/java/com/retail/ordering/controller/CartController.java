package com.retail.ordering.controller;

import com.retail.ordering.dto.CartItemRequest;
import com.retail.ordering.dto.CartResponse;
import com.retail.ordering.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Cart", description = "Cart management APIs")
@SecurityRequirement(name = "Bearer Authentication")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    @Operation(summary = "Add item to cart")
    public ResponseEntity<CartResponse> addToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody CartItemRequest request) {
        log.info("POST /cart/add - user: {}", userDetails.getUsername());
        return ResponseEntity.ok(cartService.addToCart(userDetails.getUsername(), request));
    }

    @GetMapping
    @Operation(summary = "Get current user's cart")
    public ResponseEntity<CartResponse> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("GET /cart - user: {}", userDetails.getUsername());
        return ResponseEntity.ok(cartService.getCart(userDetails.getUsername()));
    }

    @DeleteMapping("/remove/{itemId}")
    @Operation(summary = "Remove an item from cart")
    public ResponseEntity<CartResponse> removeCartItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long itemId) {
        log.info("DELETE /cart/remove/{} - user: {}", itemId, userDetails.getUsername());
        return ResponseEntity.ok(cartService.removeCartItem(userDetails.getUsername(), itemId));
    }
}
