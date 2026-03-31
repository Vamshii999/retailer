package com.retail.ordering.config;

import com.retail.ordering.entity.Product;
import com.retail.ordering.entity.User;
import com.retail.ordering.repository.ProductRepository;
import com.retail.ordering.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedUsers();
        seedProducts();
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            log.info("Seeding default users...");

            userRepository.saveAll(List.of(
                User.builder()
                    .name("Admin User")
                    .email("admin@retail.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(User.Role.ADMIN)
                    .build(),
                User.builder()
                    .name("John Doe")
                    .email("john@retail.com")
                    .password(passwordEncoder.encode("user123"))
                    .role(User.Role.USER)
                    .build()
            ));

            log.info("Default users seeded. Admin: admin@retail.com / admin123");
        }
    }

    private void seedProducts() {
        if (productRepository.count() == 0) {
            log.info("Seeding products...");

            List<Product> products = List.of(
                // PIZZA
                Product.builder().name("Margherita Pizza").category(Product.Category.PIZZA)
                    .price(new BigDecimal("12.99")).stock(50)
                    .description("Classic tomato sauce, fresh mozzarella, and basil leaves.")
                    .imageUrl("https://images.unsplash.com/photo-1604068549290-dea0e4a305ca?w=400").build(),

                Product.builder().name("Pepperoni Pizza").category(Product.Category.PIZZA)
                    .price(new BigDecimal("14.99")).stock(40)
                    .description("Loaded with premium pepperoni slices and melted cheese.")
                    .imageUrl("https://images.unsplash.com/photo-1628840042765-356cda07504e?w=400").build(),

                Product.builder().name("BBQ Chicken Pizza").category(Product.Category.PIZZA)
                    .price(new BigDecimal("15.99")).stock(35)
                    .description("Smoky BBQ sauce, grilled chicken, red onion, and cheddar.")
                    .imageUrl("https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=400").build(),

                Product.builder().name("Veggie Supreme Pizza").category(Product.Category.PIZZA)
                    .price(new BigDecimal("13.99")).stock(45)
                    .description("Loaded with fresh bell peppers, mushrooms, olives, and onions.")
                    .imageUrl("https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=400").build(),

                Product.builder().name("Four Cheese Pizza").category(Product.Category.PIZZA)
                    .price(new BigDecimal("16.99")).stock(30)
                    .description("Mozzarella, cheddar, gouda, and parmesan blend.")
                    .imageUrl("https://images.unsplash.com/photo-1513104890138-7c749659a591?w=400").build(),

                // DRINKS
                Product.builder().name("Coca Cola (330ml)").category(Product.Category.DRINKS)
                    .price(new BigDecimal("2.49")).stock(200)
                    .description("Ice-cold classic Coca Cola can.")
                    .imageUrl("https://images.unsplash.com/photo-1554866585-cd94860890b7?w=400").build(),

                Product.builder().name("Fresh Orange Juice").category(Product.Category.DRINKS)
                    .price(new BigDecimal("3.99")).stock(100)
                    .description("100% freshly squeezed orange juice, no additives.")
                    .imageUrl("https://images.unsplash.com/photo-1621506289937-a8e4df240d0b?w=400").build(),

                Product.builder().name("Sparkling Water").category(Product.Category.DRINKS)
                    .price(new BigDecimal("1.99")).stock(150)
                    .description("Refreshing sparkling mineral water.")
                    .imageUrl("https://images.unsplash.com/photo-1560023907-5f339617ea30?w=400").build(),

                Product.builder().name("Lemonade").category(Product.Category.DRINKS)
                    .price(new BigDecimal("2.99")).stock(120)
                    .description("Homemade style lemonade with a hint of mint.")
                    .imageUrl("https://images.unsplash.com/photo-1582634483660-61a63f02a10b?w=400").build(),

                Product.builder().name("Iced Tea").category(Product.Category.DRINKS)
                    .price(new BigDecimal("2.79")).stock(130)
                    .description("Cold-brewed black tea with peach flavour.")
                    .imageUrl("https://images.unsplash.com/photo-1556679343-c7306c1976bc?w=400").build(),

                // BREAD
                Product.builder().name("Garlic Bread").category(Product.Category.BREAD)
                    .price(new BigDecimal("4.99")).stock(80)
                    .description("Crispy baguette with garlic butter and parsley.")
                    .imageUrl("https://images.unsplash.com/photo-1573140247632-f8fd74997d5c?w=400").build(),

                Product.builder().name("Sourdough Loaf").category(Product.Category.BREAD)
                    .price(new BigDecimal("6.99")).stock(60)
                    .description("Artisan sourdough with a crispy crust and chewy interior.")
                    .imageUrl("https://images.unsplash.com/photo-1585478259715-876acc5be8eb?w=400").build(),

                Product.builder().name("Focaccia Bread").category(Product.Category.BREAD)
                    .price(new BigDecimal("5.99")).stock(55)
                    .description("Italian flatbread with olive oil, rosemary, and sea salt.")
                    .imageUrl("https://images.unsplash.com/photo-1586444248902-2f64eddc13df?w=400").build(),

                Product.builder().name("Dinner Rolls (6pcs)").category(Product.Category.BREAD)
                    .price(new BigDecimal("3.99")).stock(90)
                    .description("Soft, fluffy dinner rolls perfect with any meal.")
                    .imageUrl("https://images.unsplash.com/photo-1606101273945-87b6a41b913c?w=400").build(),

                Product.builder().name("Ciabatta Bread").category(Product.Category.BREAD)
                    .price(new BigDecimal("5.49")).stock(70)
                    .description("Traditional Italian ciabatta with an open crumb.")
                    .imageUrl("https://images.unsplash.com/photo-1509440159596-0249088772ff?w=400").build()
            );

            productRepository.saveAll(products);
            log.info("Seeded {} products successfully.", products.size());
        }
    }
}
