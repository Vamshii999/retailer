package com.retail.ordering.service;

import com.retail.ordering.dto.CartItemRequest;
import com.retail.ordering.dto.CartResponse;
import com.retail.ordering.entity.Cart;
import com.retail.ordering.entity.CartItem;
import com.retail.ordering.entity.Product;
import com.retail.ordering.entity.User;
import com.retail.ordering.exception.BadRequestException;
import com.retail.ordering.exception.ResourceNotFoundException;
import com.retail.ordering.repository.CartItemRepository;
import com.retail.ordering.repository.CartRepository;
import com.retail.ordering.repository.ProductRepository;
import com.retail.ordering.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public CartResponse addToCart(String userEmail, CartItemRequest request) {
        log.info("Adding product {} to cart for user: {}", request.getProductId(), userEmail);

        User user = getUserByEmail(userEmail);
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", request.getProductId()));

        if (product.getStock() < request.getQuantity()) {
            throw new BadRequestException("Insufficient stock. Available: " + product.getStock());
        }

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(Cart.builder().user(user).build()));

        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProductId(cart, product.getId());

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int newQuantity = item.getQuantity() + request.getQuantity();
            if (product.getStock() < newQuantity) {
                throw new BadRequestException("Insufficient stock. Available: " + product.getStock());
            }
            item.setQuantity(newQuantity);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();
            cartItemRepository.save(newItem);
        }

        return getCart(userEmail);
    }

    public CartResponse getCart(String userEmail) {
        log.info("Fetching cart for user: {}", userEmail);
        User user = getUserByEmail(userEmail);

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(Cart.builder().user(user).build()));

        List<CartItem> items = cartItemRepository.findByCart(cart);

        List<CartResponse.CartItemResponse> itemResponses = items.stream()
                .map(item -> CartResponse.CartItemResponse.builder()
                        .itemId(item.getId())
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .category(item.getProduct().getCategory().name())
                        .price(item.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .subtotal(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .imageUrl(item.getProduct().getImageUrl())
                        .build())
                .collect(Collectors.toList());

        BigDecimal total = itemResponses.stream()
                .map(CartResponse.CartItemResponse::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CartResponse.builder()
                .cartId(cart.getId())
                .items(itemResponses)
                .totalAmount(total)
                .build();
    }

    @Transactional
    public CartResponse removeCartItem(String userEmail, Long itemId) {
        log.info("Removing cart item {} for user: {}", itemId, userEmail);
        User user = getUserByEmail(userEmail);

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user"));

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", itemId));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new BadRequestException("Cart item does not belong to the current user");
        }

        cartItemRepository.delete(item);
        return getCart(userEmail);
    }

    @Transactional
    public void clearCart(Cart cart) {
        cartItemRepository.deleteByCart(cart);
    }

    public Cart getOrCreateCart(User user) {
        return cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(Cart.builder().user(user).build()));
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }
}
