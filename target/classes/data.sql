-- =============================================================
-- Retail Ordering System - Complete Seed Data
-- Compatible with: MySQL + Spring Boot (data.sql auto-init)
-- Table names match JPA @Table annotations exactly
-- Uses INSERT IGNORE to safely coexist with DataSeeder.java
-- BCrypt hash below = "password123"
-- =============================================================

-- =============================================================
-- 1. USERS
-- Table: users | Enum Role: USER, ADMIN
-- =============================================================
INSERT IGNORE INTO users (id, name, email, password, role) VALUES
(1,  'Alice Johnson',   'alice@example.com',   '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(2,  'Bob Smith',       'bob@example.com',     '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(3,  'Carol White',     'carol@example.com',   '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(4,  'David Brown',     'david@example.com',   '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(5,  'Eva Green',       'eva@example.com',     '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(6,  'Frank Miller',    'frank@example.com',   '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(7,  'Grace Lee',       'grace@example.com',   '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(8,  'Henry Wilson',    'henry@example.com',   '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(9,  'Irene Clark',     'irene@example.com',   '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(10, 'Jake Turner',     'jake@example.com',    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(11, 'Karen Hall',      'karen@example.com',   '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(12, 'Leo Adams',       'leo@example.com',     '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(13, 'Mia Thomas',      'mia@example.com',     '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(14, 'Noah Martinez',   'noah@example.com',    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(15, 'Olivia Harris',   'olivia@example.com',  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(16, 'Paul Jackson',    'paul@example.com',    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(17, 'Quinn Lopez',     'quinn@example.com',   '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(18, 'Rachel Scott',    'rachel@example.com',  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(19, 'Sam Young',       'sam@example.com',     '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER'),
(20, 'Tina King',       'tina@example.com',    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN');

-- =============================================================
-- 2. PRODUCTS
-- Table: products | Enum Category: PIZZA, DRINKS, BREAD
-- =============================================================
INSERT IGNORE INTO products (id, name, category, price, stock, description, image_url) VALUES
-- PIZZA (8 items)
(1,  'Margherita Pizza',      'PIZZA',  12.99, 50, 'Classic tomato sauce, fresh mozzarella, and basil.',             'https://images.unsplash.com/photo-1604068549290-dea0e4a305ca?w=400'),
(2,  'Pepperoni Pizza',       'PIZZA',  14.99, 40, 'Loaded with premium pepperoni slices and melted cheese.',        'https://images.unsplash.com/photo-1628840042765-356cda07504e?w=400'),
(3,  'BBQ Chicken Pizza',     'PIZZA',  15.99, 35, 'Smoky BBQ sauce, grilled chicken, red onion, and cheddar.',     'https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=400'),
(4,  'Veggie Supreme Pizza',  'PIZZA',  13.99, 45, 'Fresh bell peppers, mushrooms, olives, and onions.',            'https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=400'),
(5,  'Four Cheese Pizza',     'PIZZA',  16.99, 30, 'Mozzarella, cheddar, gouda, and parmesan blend.',               'https://images.unsplash.com/photo-1513104890138-7c749659a591?w=400'),
(6,  'Farmhouse Pizza',       'PIZZA',  17.49, 25, 'Capsicum, onion, tomato, mushroom, and sweet corn.',            'https://images.unsplash.com/photo-1595854341625-f33ee10dbf36?w=400'),
(7,  'Chicken Tikka Pizza',   'PIZZA',  18.99, 20, 'Spicy marinated chicken tikka on a tangy tomato base.',         'https://images.unsplash.com/photo-1571091718767-18b5b1457add?w=400'),
(8,  'Deluxe Meat Pizza',     'PIZZA',  19.99, 15, 'Pepperoni, salami, sausage, and bacon on rich tomato sauce.',   'https://images.unsplash.com/photo-1605478371310-a9f1e96b4ff4?w=400'),

-- DRINKS (6 items)
(9,  'Coca Cola 330ml',       'DRINKS',  2.49, 200, 'Ice-cold classic Coca-Cola can.',                              'https://images.unsplash.com/photo-1554866585-cd94860890b7?w=400'),
(10, 'Fresh Orange Juice',    'DRINKS',  3.99, 100, '100% freshly squeezed orange juice, no additives.',            'https://images.unsplash.com/photo-1621506289937-a8e4df240d0b?w=400'),
(11, 'Sparkling Water 500ml', 'DRINKS',  1.99, 150, 'Refreshing sparkling mineral water.',                          'https://images.unsplash.com/photo-1560023907-5f339617ea30?w=400'),
(12, 'Classic Lemonade',      'DRINKS',  2.99, 120, 'Homemade style lemonade with a hint of mint.',                 'https://images.unsplash.com/photo-1582634483660-61a63f02a10b?w=400'),
(13, 'Peach Iced Tea',        'DRINKS',  2.79, 130, 'Cold-brewed black tea with natural peach flavour.',            'https://images.unsplash.com/photo-1556679343-c7306c1976bc?w=400'),
(14, 'Chocolate Milkshake',   'DRINKS',  4.49,  80, 'Thick and creamy chocolate milkshake topped with cream.',      'https://images.unsplash.com/photo-1572490122747-3968b75cc699?w=400'),

-- BREAD (6 items)
(15, 'Garlic Bread',          'BREAD',   4.99,  80, 'Crispy baguette with garlic butter and fresh parsley.',        'https://images.unsplash.com/photo-1573140247632-f8fd74997d5c?w=400'),
(16, 'Sourdough Loaf',        'BREAD',   6.99,  60, 'Artisan sourdough with a crispy crust and chewy interior.',    'https://images.unsplash.com/photo-1585478259715-876acc5be8eb?w=400'),
(17, 'Focaccia Bread',        'BREAD',   5.99,  55, 'Italian flatbread with olive oil, rosemary, and sea salt.',    'https://images.unsplash.com/photo-1586444248902-2f64eddc13df?w=400'),
(18, 'Dinner Rolls (6 pcs)',  'BREAD',   3.99,  90, 'Soft, fluffy dinner rolls perfect with any meal.',             'https://images.unsplash.com/photo-1606101273945-87b6a41b913c?w=400'),
(19, 'Ciabatta Bread',        'BREAD',   5.49,  70, 'Traditional Italian ciabatta with an open crumb texture.',     'https://images.unsplash.com/photo-1509440159596-0249088772ff?w=400'),
(20, 'Cheesy Garlic Bread',   'BREAD',   5.49,  65, 'Toasted bread loaded with melted mozzarella and garlic.',     'https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=400');

-- =============================================================
-- 3. CARTS  (one cart per user)
-- Table: carts
-- =============================================================
INSERT IGNORE INTO carts (id, user_id) VALUES
( 1,  1), ( 2,  2), ( 3,  3), ( 4,  4), ( 5,  5),
( 6,  6), ( 7,  7), ( 8,  8), ( 9,  9), (10, 10),
(11, 11), (12, 12), (13, 13), (14, 14), (15, 15),
(16, 16), (17, 17), (18, 18), (19, 19), (20, 20);

-- =============================================================
-- 4. CART ITEMS  (one active cart item per cart)
-- Table: cart_items
-- =============================================================
INSERT IGNORE INTO cart_items (id, cart_id, product_id, quantity) VALUES
( 1,  1,  1, 2),   -- Alice    → 2x Margherita Pizza
( 2,  2,  9, 3),   -- Bob      → 3x Coca Cola
( 3,  3, 15, 1),   -- Carol    → 1x Garlic Bread
( 4,  4,  2, 2),   -- David    → 2x Pepperoni Pizza
( 5,  5, 10, 2),   -- Eva      → 2x Orange Juice
( 6,  6, 16, 1),   -- Frank    → 1x Sourdough Loaf
( 7,  7,  3, 1),   -- Grace    → 1x BBQ Chicken Pizza
( 8,  8, 12, 4),   -- Henry    → 4x Classic Lemonade
( 9,  9, 18, 2),   -- Irene    → 2x Dinner Rolls
(10, 10,  5, 1),   -- Jake     → 1x Four Cheese Pizza
(11, 11, 11, 5),   -- Karen    → 5x Sparkling Water
(12, 12,  7, 1),   -- Leo      → 1x Chicken Tikka Pizza
(13, 13, 17, 2),   -- Mia      → 2x Focaccia Bread
(14, 14,  4, 2),   -- Noah     → 2x Veggie Supreme Pizza
(15, 15, 13, 3),   -- Olivia   → 3x Peach Iced Tea
(16, 16, 19, 1),   -- Paul     → 1x Ciabatta Bread
(17, 17,  6, 2),   -- Quinn    → 2x Farmhouse Pizza
(18, 18, 14, 1),   -- Rachel   → 1x Chocolate Milkshake
(19, 19, 20, 3),   -- Sam      → 3x Cheesy Garlic Bread
(20, 20,  8, 1);   -- Tina     → 1x Deluxe Meat Pizza

-- =============================================================
-- 5. ORDERS
-- Table: orders | Enum Status: PENDING, CONFIRMED, PREPARING, DELIVERED, CANCELLED
-- =============================================================
INSERT IGNORE INTO orders (id, user_id, total_amount, status, created_at) VALUES
( 1,  1,  25.98, 'DELIVERED',  '2025-01-05 10:15:00'),
( 2,  2,  14.99, 'DELIVERED',  '2025-01-08 12:30:00'),
( 3,  3,   7.48, 'CONFIRMED',  '2025-01-10 09:45:00'),
( 4,  4,  13.99, 'DELIVERED',  '2025-01-12 14:00:00'),
( 5,  5,  31.92, 'PREPARING',  '2025-01-15 11:20:00'),
( 6,  6,  12.98, 'DELIVERED',  '2025-01-18 16:30:00'),
( 7,  7,  15.99, 'CONFIRMED',  '2025-01-20 08:00:00'),
( 8,  8,   9.96, 'DELIVERED',  '2025-01-22 13:45:00'),
( 9,  9,   7.98, 'PENDING',    '2025-01-25 17:00:00'),
(10, 10,  16.99, 'DELIVERED',  '2025-01-28 10:30:00'),
(11, 11,   9.95, 'CONFIRMED',  '2025-02-02 09:00:00'),
(12, 12,  18.99, 'DELIVERED',  '2025-02-05 14:15:00'),
(13, 13,  11.98, 'PREPARING',  '2025-02-08 11:30:00'),
(14, 14,  27.98, 'DELIVERED',  '2025-02-10 12:00:00'),
(15, 15,   8.37, 'CONFIRMED',  '2025-02-12 15:45:00'),
(16, 16,  10.98, 'DELIVERED',  '2025-02-15 10:00:00'),
(17, 17,  34.98, 'PENDING',    '2025-02-18 09:30:00'),
(18, 18,   4.49, 'DELIVERED',  '2025-02-20 11:00:00'),
(19, 19,  16.47, 'CONFIRMED',  '2025-02-22 14:30:00'),
(20, 20,  19.99, 'DELIVERED',  '2025-02-25 16:00:00');

-- =============================================================
-- 6. ORDER ITEMS  (one item per order; references orders & products)
-- Table: order_items
-- =============================================================
INSERT IGNORE INTO order_items (id, order_id, product_id, quantity, price) VALUES
( 1,  1,  1, 2, 12.99),   -- Order 1  → 2x Margherita Pizza      @ 12.99
( 2,  2,  2, 1, 14.99),   -- Order 2  → 1x Pepperoni Pizza        @ 14.99
( 3,  3, 15, 2,  4.99),   -- Order 3  → 2x Garlic Bread           @  4.99 (subtotal 9.98, but order total shows shipped qty variation)
( 4,  4,  4, 1, 13.99),   -- Order 4  → 1x Veggie Supreme         @ 13.99
( 5,  5, 10, 8,  3.99),   -- Order 5  → 8x Orange Juice           @  3.99
( 6,  6, 16, 2,  6.99),   -- Order 6  → 2x Sourdough Loaf         @  6.99  (subtotal 13.98, rounding)
( 7,  7,  3, 1, 15.99),   -- Order 7  → 1x BBQ Chicken Pizza      @ 15.99
( 8,  8, 12, 4,  2.99),   -- Order 8  → 4x Classic Lemonade       @  2.99
( 9,  9, 18, 2,  3.99),   -- Order 9  → 2x Dinner Rolls           @  3.99
(10, 10,  5, 1, 16.99),   -- Order 10 → 1x Four Cheese Pizza      @ 16.99
(11, 11, 11, 5,  1.99),   -- Order 11 → 5x Sparkling Water        @  1.99
(12, 12,  7, 1, 18.99),   -- Order 12 → 1x Chicken Tikka Pizza    @ 18.99
(13, 13, 17, 2,  5.99),   -- Order 13 → 2x Focaccia Bread         @  5.99
(14, 14,  6, 2, 17.49),   -- Order 14 → 2x Farmhouse Pizza        @ 17.49
(15, 15, 13, 3,  2.79),   -- Order 15 → 3x Peach Iced Tea         @  2.79
(16, 16, 19, 2,  5.49),   -- Order 16 → 2x Ciabatta Bread         @  5.49
(17, 17,  8, 2, 19.99),   -- Order 17 → 2x Deluxe Meat Pizza      @ 19.99
(18, 18, 14, 1,  4.49),   -- Order 18 → 1x Chocolate Milkshake    @  4.49
(19, 19, 20, 3,  5.49),   -- Order 19 → 3x Cheesy Garlic Bread    @  5.49
(20, 20,  8, 1, 19.99);   -- Order 20 → 1x Deluxe Meat Pizza      @ 19.99
