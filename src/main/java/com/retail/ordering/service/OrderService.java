package com.retail.ordering.service;

import com.retail.ordering.dto.OrderResponse;
import com.retail.ordering.entity.*;
import com.retail.ordering.exception.BadRequestException;
import com.retail.ordering.exception.InsufficientStockException;
import com.retail.ordering.exception.ResourceNotFoundException;
import com.retail.ordering.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Transactional
    public OrderResponse placeOrder(String userEmail) {
        log.info("Placing order for user: {}", userEmail);

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userEmail));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new BadRequestException("Cart not found. Please add items before ordering."));

        List<CartItem> cartItems = cartItemRepository.findByCart(cart);

        if (cartItems.isEmpty()) {
            throw new BadRequestException("Cart is empty. Please add items before placing an order.");
        }

        // Validate stock availability and calculate total
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            if (product.getStock() < cartItem.getQuantity()) {
                throw new InsufficientStockException(
                        product.getName(),
                        product.getStock(),
                        cartItem.getQuantity()
                );
            }
            totalAmount = totalAmount.add(
                    product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()))
            );
        }

        // Create order
        Order order = Order.builder()
                .user(user)
                .totalAmount(totalAmount)
                .status(Order.OrderStatus.CONFIRMED)
                .build();

        Order savedOrder = orderRepository.save(order);

        // Create order items and reduce stock
        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);

            return OrderItem.builder()
                    .order(savedOrder)
                    .product(product)
                    .quantity(cartItem.getQuantity())
                    .price(product.getPrice())
                    .build();
        }).collect(Collectors.toList());

        savedOrder.setOrderItems(orderItems);
        orderRepository.save(savedOrder);

        // Clear cart
        cartItemRepository.deleteByCart(cart);

        log.info("Order placed successfully. Order ID: {}", savedOrder.getId());

        // Send confirmation email (async/mock)
        emailService.sendOrderConfirmation(user.getEmail(), user.getName(), savedOrder.getId(), totalAmount);

        return mapToResponse(savedOrder);
    }

    public List<OrderResponse> getOrderHistory(String userEmail) {
        log.info("Fetching order history for user: {}", userEmail);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userEmail));

        return orderRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrderById(String userEmail, Long orderId) {
        log.info("Fetching order {} for user: {}", orderId, userEmail);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userEmail));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        // Security check: ensure order belongs to user (or user is admin)
        if (!order.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("You don't have permission to view this order.");
        }

        return mapToResponse(order);
    }

    private OrderResponse mapToResponse(Order order) {
        List<OrderResponse.OrderItemResponse> itemResponses = order.getOrderItems().stream()
                .map(item -> OrderResponse.OrderItemResponse.builder()
                        .itemId(item.getId())
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .category(item.getProduct().getCategory().name())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .subtotal(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .build())
                .collect(Collectors.toList());

        return OrderResponse.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .items(itemResponses)
                .build();
    }
}
