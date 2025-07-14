-- =====================================================
-- FOODFLOW DEFAULT DATA INITIALIZATION
-- =====================================================

-- Clear existing data (optional - uncomment if needed)
-- DELETE FROM group_users;
-- DELETE FROM user_roles;
-- DELETE FROM role_permissions;
-- DELETE FROM user_groups;
-- DELETE FROM app_users;
-- DELETE FROM app_roles;
-- DELETE FROM app_permissions;

-- =====================================================
-- PERMISSIONS
-- =====================================================
INSERT INTO app_permissions (name, description, created_date, status) VALUES
-- User management
('CREATE_USER', 'Create new users', NOW(), 1),
('READ_USER', 'Read user information', NOW(), 1),
('UPDATE_USER', 'Update user information', NOW(), 1),
('DELETE_USER', 'Delete user accounts', NOW(), 1),
('LIST_USERS', 'List all users', NOW(), 1),

-- Role management
('CREATE_ROLE', 'Create new roles', NOW(), 1),
('READ_ROLE', 'Read role information', NOW(), 1),
('UPDATE_ROLE', 'Update role information', NOW(), 1),
('DELETE_ROLE', 'Delete roles', NOW(), 1),
('LIST_ROLES', 'List all roles', NOW(), 1),
('ASSIGN_ROLE', 'Assign roles to users', NOW(), 1),

-- Group management
('CREATE_GROUP', 'Create new groups', NOW(), 1),
('READ_GROUP', 'Read group information', NOW(), 1),
('UPDATE_GROUP', 'Update group information', NOW(), 1),
('DELETE_GROUP', 'Delete groups', NOW(), 1),
('LIST_GROUPS', 'List all groups', NOW(), 1),
('ADD_USER_TO_GROUP', 'Add users to groups', NOW(), 1),

-- Permission management
('CREATE_PERMISSION', 'Create new permissions', NOW(), 1),
('READ_PERMISSION', 'Read permission information', NOW(), 1),
('UPDATE_PERMISSION', 'Update permission information', NOW(), 1),
('DELETE_PERMISSION', 'Delete permissions', NOW(), 1),
('LIST_PERMISSIONS', 'List all permissions', NOW(), 1),
('ASSIGN_PERMISSION', 'Assign permissions to roles', NOW(), 1),

-- Restaurant management
('CREATE_RESTAURANT', 'Create new restaurants', NOW(), 1),
('READ_RESTAURANT', 'Read restaurant information', NOW(), 1),
('UPDATE_RESTAURANT', 'Update restaurant information', NOW(), 1),
('DELETE_RESTAURANT', 'Delete restaurants', NOW(), 1),
('LIST_RESTAURANTS', 'List all restaurants', NOW(), 1),
('MANAGE_RESTAURANT', 'Manage restaurant operations', NOW(), 1),

-- Menu management
('CREATE_MENU', 'Create menu items', NOW(), 1),
('READ_MENU', 'Read menu items', NOW(), 1),
('UPDATE_MENU', 'Update menu items', NOW(), 1),
('DELETE_MENU', 'Delete menu items', NOW(), 1),
('LIST_MENUS', 'List all menus', NOW(), 1),

-- Order management
('CREATE_ORDER', 'Create new orders', NOW(), 1),
('READ_ORDER', 'Read order information', NOW(), 1),
('UPDATE_ORDER', 'Update order information', NOW(), 1),
('DELETE_ORDER', 'Delete orders', NOW(), 1),
('LIST_ORDERS', 'List all orders', NOW(), 1),
('MANAGE_ORDER', 'Manage order operations', NOW(), 1),
('CANCEL_ORDER', 'Cancel orders', NOW(), 1),

-- Delivery management
('CREATE_DELIVERY', 'Create new deliveries', NOW(), 1),
('READ_DELIVERY', 'Read delivery information', NOW(), 1),
('UPDATE_DELIVERY', 'Update delivery information', NOW(), 1),
('DELETE_DELIVERY', 'Delete deliveries', NOW(), 1),
('LIST_DELIVERIES', 'List all deliveries', NOW(), 1),
('ASSIGN_DELIVERY', 'Assign deliveries to drivers', NOW(), 1),

-- Payment management
('CREATE_PAYMENT', 'Create new payments', NOW(), 1),
('READ_PAYMENT', 'Read payment information', NOW(), 1),
('UPDATE_PAYMENT', 'Update payment information', NOW(), 1),
('DELETE_PAYMENT', 'Delete payments', NOW(), 1),
('LIST_PAYMENTS', 'List all payments', NOW(), 1),
('PROCESS_PAYMENT', 'Process payments', NOW(), 1),

-- System management
('SYSTEM_CONFIG', 'Configure system settings', NOW(), 1),
('VIEW_LOGS', 'View system logs', NOW(), 1),
('MANAGE_SYSTEM', 'Manage system operations', NOW(), 1),

-- Customer specific
('PLACE_ORDER', 'Place new orders', NOW(), 1),
('VIEW_OWN_ORDERS', 'View own orders', NOW(), 1),
('UPDATE_PROFILE', 'Update own profile', NOW(), 1),
('VIEW_MENUS', 'View restaurant menus', NOW(), 1),

-- Driver specific
('ACCEPT_DELIVERY', 'Accept delivery assignments', NOW(), 1),
('UPDATE_DELIVERY_STATUS', 'Update delivery status', NOW(), 1),
('VIEW_ASSIGNED_DELIVERIES', 'View assigned deliveries', NOW(), 1);

-- =====================================================
-- ROLES
-- =====================================================
INSERT INTO app_roles (name, description, created_date, status) VALUES
('ADMIN', 'System administrator with full access', NOW(), 1),
('MANAGER', 'Manager with administrative privileges', NOW(), 1),
('STAFF', 'Staff member with limited access', NOW(), 1),
('CUSTOMER', 'Regular customer with basic access', NOW(), 1),
('DRIVER', 'Delivery driver with delivery access', NOW(), 1),
('RESTAURANT_OWNER', 'Restaurant owner with restaurant management access', NOW(), 1),
('RESTAURANT_STAFF', 'Restaurant staff with order management access', NOW(), 1);

-- =====================================================
-- USERS (password: 123456)
-- =====================================================
INSERT INTO app_users (name, email, password, phone, status, created_date) VALUES
('Admin User', 'admin@foodflow.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '0123456789', 1, NOW()),
('Manager User', 'manager@foodflow.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '0987654321', 1, NOW()),
('Staff User', 'staff@foodflow.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '0555555555', 1, NOW()),
('Customer User', 'customer@foodflow.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '0111111111', 1, NOW()),
('Driver User', 'driver@foodflow.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '0222222222', 1, NOW()),
('Restaurant Owner', 'restaurant@foodflow.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '0333333333', 1, NOW()),
('Restaurant Staff', 'restaurant_staff@foodflow.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '0444444444', 1, NOW());

-- =====================================================
-- GROUPS
-- =====================================================
INSERT INTO user_groups (name, role_id, created_date, status) VALUES
('ADMINISTRATORS', 1, NOW(), 1),
('MANAGERS', 2, NOW(), 1),
('STAFF_MEMBERS', 3, NOW(), 1),
('CUSTOMERS', 4, NOW(), 1),
('DRIVERS', 5, NOW(), 1),
('RESTAURANT_OWNERS', 6, NOW(), 1),
('RESTAURANT_STAFF', 7, NOW(), 1);

-- =====================================================
-- ROLE-PERMISSION MAPPINGS
-- =====================================================

-- Admin gets all permissions
INSERT INTO role_permissions (role_id, permission_id, assigned_at, created_date, status) 
SELECT 1, id, NOW(), NOW(), 1 FROM app_permissions;

-- Manager gets most permissions except system management
INSERT INTO role_permissions (role_id, permission_id, assigned_at, created_date, status) VALUES
(2, 1, NOW(), NOW(), 1), (2, 2, NOW(), NOW(), 1), (2, 3, NOW(), NOW(), 1), (2, 4, NOW(), NOW(), 1), (2, 5, NOW(), NOW(), 1), -- User management
(2, 6, NOW(), NOW(), 1), (2, 7, NOW(), NOW(), 1), (2, 8, NOW(), NOW(), 1), (2, 9, NOW(), NOW(), 1), (2, 10, NOW(), NOW(), 1), (2, 11, NOW(), NOW(), 1), -- Role management
(2, 12, NOW(), NOW(), 1), (2, 13, NOW(), NOW(), 1), (2, 14, NOW(), NOW(), 1), (2, 15, NOW(), NOW(), 1), (2, 16, NOW(), NOW(), 1), (2, 17, NOW(), NOW(), 1), -- Group management
(2, 18, NOW(), NOW(), 1), (2, 19, NOW(), NOW(), 1), (2, 20, NOW(), NOW(), 1), (2, 21, NOW(), NOW(), 1), (2, 22, NOW(), NOW(), 1), (2, 23, NOW(), NOW(), 1), -- Permission management
(2, 24, NOW(), NOW(), 1), (2, 25, NOW(), NOW(), 1), (2, 26, NOW(), NOW(), 1), (2, 27, NOW(), NOW(), 1), (2, 28, NOW(), NOW(), 1), (2, 29, NOW(), NOW(), 1), -- Restaurant management
(2, 30, NOW(), NOW(), 1), (2, 31, NOW(), NOW(), 1), (2, 32, NOW(), NOW(), 1), (2, 33, NOW(), NOW(), 1), (2, 34, NOW(), NOW(), 1), -- Menu management
(2, 35, NOW(), NOW(), 1), (2, 36, NOW(), NOW(), 1), (2, 37, NOW(), NOW(), 1), (2, 38, NOW(), NOW(), 1), (2, 39, NOW(), NOW(), 1), (2, 40, NOW(), NOW(), 1), (2, 41, NOW(), NOW(), 1), -- Order management
(2, 42, NOW(), NOW(), 1), (2, 43, NOW(), NOW(), 1), (2, 44, NOW(), NOW(), 1), (2, 45, NOW(), NOW(), 1), (2, 46, NOW(), NOW(), 1), (2, 47, NOW(), NOW(), 1), -- Delivery management
(2, 48, NOW(), NOW(), 1), (2, 49, NOW(), NOW(), 1), (2, 50, NOW(), NOW(), 1), (2, 51, NOW(), NOW(), 1), (2, 52, NOW(), NOW(), 1), (2, 53, NOW(), NOW(), 1); -- Payment management

-- Staff gets limited permissions
INSERT INTO role_permissions (role_id, permission_id, assigned_at, created_date, status) VALUES
(3, 2, NOW(), NOW(), 1), (3, 3, NOW(), NOW(), 1), (3, 5, NOW(), NOW(), 1), -- User management (read, update, list)
(3, 7, NOW(), NOW(), 1), (3, 10, NOW(), NOW(), 1), -- Role management (read, list)
(3, 13, NOW(), NOW(), 1), (3, 16, NOW(), NOW(), 1), -- Group management (read, list)
(3, 25, NOW(), NOW(), 1), (3, 28, NOW(), NOW(), 1), -- Restaurant management (read, list)
(3, 31, NOW(), NOW(), 1), (3, 34, NOW(), NOW(), 1), -- Menu management (read, list)
(3, 36, NOW(), NOW(), 1), (3, 37, NOW(), NOW(), 1), (3, 39, NOW(), NOW(), 1), (3, 40, NOW(), NOW(), 1), -- Order management (read, update, list, manage)
(3, 43, NOW(), NOW(), 1), (3, 44, NOW(), NOW(), 1), (3, 46, NOW(), NOW(), 1), -- Delivery management (read, update, list)
(3, 49, NOW(), NOW(), 1), (3, 52, NOW(), NOW(), 1); -- Payment management (read, list)

-- Customer gets basic permissions
INSERT INTO role_permissions (role_id, permission_id, assigned_at, created_date, status) VALUES
(4, 3, NOW(), NOW(), 1), -- UPDATE_USER (own profile)
(4, 25, NOW(), NOW(), 1), (4, 28, NOW(), NOW(), 1), -- Restaurant management (read, list)
(4, 31, NOW(), NOW(), 1), (4, 34, NOW(), NOW(), 1), -- Menu management (read, list)
(4, 35, NOW(), NOW(), 1), (4, 36, NOW(), NOW(), 1), (4, 39, NOW(), NOW(), 1), (4, 41, NOW(), NOW(), 1), -- Order management (create, read, list, cancel)
(4, 43, NOW(), NOW(), 1), (4, 46, NOW(), NOW(), 1), -- Delivery management (read, list)
(4, 49, NOW(), NOW(), 1), (4, 53, NOW(), NOW(), 1), -- Payment management (read, process)
(4, 54, NOW(), NOW(), 1), (4, 55, NOW(), NOW(), 1), (4, 56, NOW(), NOW(), 1), (4, 57, NOW(), NOW(), 1); -- Customer specific

-- Driver gets delivery permissions
INSERT INTO role_permissions (role_id, permission_id, assigned_at, created_date, status) VALUES
(5, 36, NOW(), NOW(), 1), (5, 39, NOW(), NOW(), 1), -- Order management (read, list)
(5, 43, NOW(), NOW(), 1), (5, 44, NOW(), NOW(), 1), (5, 46, NOW(), NOW(), 1), -- Delivery management (read, update, list)
(5, 58, NOW(), NOW(), 1), (5, 59, NOW(), NOW(), 1), (5, 60, NOW(), NOW(), 1); -- Driver specific

-- Restaurant Owner gets restaurant management permissions
INSERT INTO role_permissions (role_id, permission_id, assigned_at, created_date, status) VALUES
(6, 3, NOW(), NOW(), 1), -- UPDATE_USER (own profile)
(6, 24, NOW(), NOW(), 1), (6, 25, NOW(), NOW(), 1), (6, 26, NOW(), NOW(), 1), (6, 28, NOW(), NOW(), 1), (6, 29, NOW(), NOW(), 1), -- Restaurant management
(6, 30, NOW(), NOW(), 1), (6, 31, NOW(), NOW(), 1), (6, 32, NOW(), NOW(), 1), (6, 33, NOW(), NOW(), 1), (6, 34, NOW(), NOW(), 1), -- Menu management
(6, 36, NOW(), NOW(), 1), (6, 37, NOW(), NOW(), 1), (6, 39, NOW(), NOW(), 1), (6, 40, NOW(), NOW(), 1), -- Order management
(6, 49, NOW(), NOW(), 1), (6, 53, NOW(), NOW(), 1); -- Payment management

-- Restaurant Staff gets order management permissions
INSERT INTO role_permissions (role_id, permission_id, assigned_at, created_date, status) VALUES
(7, 31, NOW(), NOW(), 1), (7, 34, NOW(), NOW(), 1), -- Menu management (read, list)
(7, 36, NOW(), NOW(), 1), (7, 37, NOW(), NOW(), 1), (7, 39, NOW(), NOW(), 1), (7, 40, NOW(), NOW(), 1), -- Order management
(7, 43, NOW(), NOW(), 1), (7, 46, NOW(), NOW(), 1); -- Delivery management (read, list)

-- =====================================================
-- USER-ROLE MAPPINGS
-- =====================================================
INSERT INTO user_roles (user_id, role_id, assigned_at, created_date, status) VALUES
(1, 1, NOW(), NOW(), 1), -- Admin user gets ADMIN role
(2, 2, NOW(), NOW(), 1), -- Manager user gets MANAGER role
(3, 3, NOW(), NOW(), 1), -- Staff user gets STAFF role
(4, 4, NOW(), NOW(), 1), -- Customer user gets CUSTOMER role
(5, 5, NOW(), NOW(), 1), -- Driver user gets DRIVER role
(6, 6, NOW(), NOW(), 1), -- Restaurant Owner gets RESTAURANT_OWNER role
(7, 7, NOW(), NOW(), 1); -- Restaurant Staff gets RESTAURANT_STAFF role

-- =====================================================
-- GROUP-USER MAPPINGS
-- =====================================================
INSERT INTO group_users (group_id, user_id, assigned_at, created_date, status) VALUES
(1, 1, NOW(), NOW(), 1), -- Admin user in ADMINISTRATORS group
(2, 2, NOW(), NOW(), 1), -- Manager user in MANAGERS group
(3, 3, NOW(), NOW(), 1), -- Staff user in STAFF_MEMBERS group
(4, 4, NOW(), NOW(), 1), -- Customer user in CUSTOMERS group
(5, 5, NOW(), NOW(), 1), -- Driver user in DRIVERS group
(6, 6, NOW(), NOW(), 1), -- Restaurant Owner in RESTAURANT_OWNERS group
(7, 7, NOW(), NOW(), 1); -- Restaurant Staff in RESTAURANT_STAFF group 

-- =====================================================
-- SAMPLE DATA FOR FOODFLOW TABLES
-- =====================================================

-- RESTAURANTS
INSERT INTO restaurants (name, address, phone_number, logo_url, cover_image_url, description, opening_time, closing_time, owner_id, rating, created_date, status)
VALUES
('Pizza Home', '123 Pizza Street', '0909123456', 'https://img.foodflow.com/pizza-logo.png', 'https://img.foodflow.com/pizza-cover.jpg', 'Nhà hàng pizza nổi tiếng', '09:00', '22:00', 6, 4.5, NOW(), 1),
('Burger King', '456 Burger Avenue', '0909234567', 'https://img.foodflow.com/burger-logo.png', 'https://img.foodflow.com/burger-cover.jpg', 'Burger ngon số 1', '08:00', '23:00', 6, 4.2, NOW(), 1);

-- PRODUCT CATEGORIES
INSERT INTO product_categories (name, description, image_url, status) VALUES
('Pizza', 'Các loại pizza Ý truyền thống', 'https://img.foodflow.com/pizza-category.jpg', 1),
('Burger', 'Các loại burger hấp dẫn', 'https://img.foodflow.com/burger-category.jpg', 1),
('Nước uống', 'Đồ uống giải khát', 'https://img.foodflow.com/drink-category.jpg', 1);

-- PRODUCTS
INSERT INTO products (name, description, image_url, price, restaurant_id, category_id, created_date, status) VALUES
('Pizza Margherita', 'Pizza truyền thống với phô mai mozzarella và sốt cà chua', 'https://img.foodflow.com/margherita.jpg', 120000, 1, 1, NOW(), 1),
('Pizza Pepperoni', 'Pizza với xúc xích pepperoni', 'https://img.foodflow.com/pepperoni.jpg', 135000, 1, 1, NOW(), 1),
('Burger Bò', 'Burger bò Mỹ, phô mai cheddar', 'https://img.foodflow.com/beef-burger.jpg', 90000, 2, 2, NOW(), 1),
('Coca Cola', 'Nước ngọt Coca Cola lon 330ml', 'https://img.foodflow.com/cocacola.jpg', 15000, 2, 3, NOW(), 1);

-- PROMOTIONS
INSERT INTO promotions (code, description, discount_type, discount_value, max_discount_amount, min_order_value, start_date, end_date, usage_limit, usage_count, created_date, status)
VALUES
('FOOD10', 'Giảm 10% cho đơn từ 200k', 'PERCENTAGE', 10, 50000, 200000, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 100, 0, NOW(), 1),
('FREESHIP', 'Miễn phí vận chuyển cho đơn từ 100k', 'FIXED_AMOUNT', 20000, NULL, 100000, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 200, 0, NOW(), 1);

-- SHIPPERS
INSERT INTO shippers (user_id, license_plate, vehicle_type, rating, created_date, status)
VALUES (5, '59A1-12345', 'Motorbike', 5.0, NOW(), 2);

-- SHIPPER LOCATIONS
INSERT INTO shipper_locations (shipper_id, latitude, longitude, last_updated)
VALUES (1, 10.762622, 106.660172, NOW());

-- ORDERS
INSERT INTO orders (order_code, user_id, restaurant_id, delivery_address, contact_phone, notes, subtotal_amount, shipping_fee, discount_amount, total_amount, payment_method, order_date, status, promotion_id, shipper_id)
VALUES
('HD-110725', 4, 1, '789 Đường ABC, Quận 1, TP.HCM', '0111111111', 'Giao nhanh giúp mình', 255000, 20000, 25500, 249500, 'COD', NOW(), 2, 1, 1);

-- ORDER ITEMS
INSERT INTO order_items (order_id, product_id, quantity, price_at_order, total_price)
VALUES
(1, 1, 1, 120000, 120000),
(1, 2, 1, 135000, 135000);

-- REVIEWS
INSERT INTO reviews (user_id, order_id, restaurant_id, rating, comment, created_date)
VALUES (4, 1, 1, 5, 'Pizza rất ngon, giao hàng nhanh!', NOW());

-- TRANSACTIONS
INSERT INTO transactions (order_id, gateway, gateway_transaction_id, amount, transaction_date, status, notes)
VALUES (1, 'COD', 'COD-20240601-0001', 249500, NOW(), 1, 'Thanh toán khi nhận hàng');

-- NOTIFICATIONS
INSERT INTO notifications (user_id, title, message, type, target_id, is_read, created_date)
VALUES (4, 'Đơn hàng đã xác nhận', 'Đơn hàng HD-110725 của bạn đã được xác nhận và đang chuẩn bị.', 'ORDER_STATUS', 1, FALSE, NOW());

-- USER FAVORITE RESTAURANTS
INSERT INTO user_favorite_restaurants (user_id, restaurant_id, created_date)
VALUES (4, 1, NOW()); 