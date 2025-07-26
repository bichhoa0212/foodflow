-- Quyền (Permissions)
INSERT IGNORE INTO app_permissions (name, description, status, created_date) VALUES
('PRODUCT_VIEW', 'Xem sản phẩm', 1, NOW()),
('PRODUCT_CREATE', 'Tạo sản phẩm', 1, NOW()),
('PRODUCT_UPDATE', 'Cập nhật sản phẩm', 1, NOW()),
('PRODUCT_DELETE', 'Xóa sản phẩm', 1, NOW()),
('ORDER_VIEW', 'Xem đơn hàng', 1, NOW()),
('ORDER_CREATE', 'Tạo đơn hàng', 1, NOW()),
('ORDER_UPDATE', 'Cập nhật đơn hàng', 1, NOW()),
('ORDER_DELETE', 'Xóa đơn hàng', 1, NOW()),
('INVENTORY_VIEW', 'Xem tồn kho', 1, NOW()),
('INVENTORY_UPDATE', 'Cập nhật tồn kho', 1, NOW());

-- Vai trò (Roles)
INSERT IGNORE INTO app_roles (name, description, status, created_date) VALUES
('ADMIN', 'Quản trị hệ thống', 1, NOW()),
('MANAGER', 'Quản lý siêu thị', 1, NOW()),
('STAFF', 'Nhân viên bán hàng', 1, NOW()),
('CUSTOMER', 'Khách hàng', 1, NOW());

-- Gán quyền cho vai trò (Role-Permission)
INSERT IGNORE INTO role_permissions (role_id, permission_id, status, created_date) VALUES
(1, 1, 1, NOW()), (1, 2, 1, NOW()), (1, 3, 1, NOW()), (1, 4, 1, NOW()), (1, 5, 1, NOW()), (1, 6, 1, NOW()), (1, 7, 1, NOW()), (1, 8, 1, NOW()), (1, 9, 1, NOW()), (1, 10, 1, NOW()),
(2, 1, 1, NOW()), (2, 2, 1, NOW()), (2, 3, 1, NOW()), (2, 5, 1, NOW()), (2, 6, 1, NOW()), (2, 9, 1, NOW()), (2, 10, 1, NOW()),
(3, 1, 1, NOW()), (3, 5, 1, NOW()), (3, 6, 1, NOW()), (3, 9, 1, NOW()),
(4, 1, 1, NOW()), (4, 5, 1, NOW());

-- Người dùng mẫu (Users)
INSERT IGNORE INTO app_users (name, phone, email, password, status, created_date) VALUES
('Admin', '0900000001', 'admin@flowmart.com', '$2a$10$7QJ8Qw1Qw1Qw1Qw1Qw1QwOeQw1Qw1Qw1Qw1Qw1Qw1Qw1Qw1Qw1Qw', 1, NOW()), -- password: admin123
('Manager', '0900000002', 'manager@flowmart.com', '$2a$10$7QJ8Qw1Qw1Qw1Qw1Qw1QwOeQw1Qw1Qw1Qw1Qw1Qw1Qw1Qw1Qw1Qw', 1, NOW()),
('Staff', '0900000003', 'staff@flowmart.com', '$2a$10$7QJ8Qw1Qw1Qw1Qw1Qw1QwOeQw1Qw1Qw1Qw1Qw1Qw1Qw1Qw1Qw1Qw', 1, NOW()),
('Customer', '0900000004', 'customer@flowmart.com', '$2a$10$7QJ8Qw1Qw1Qw1Qw1Qw1QwOeQw1Qw1Qw1Qw1Qw1Qw1Qw1Qw1Qw1Qw', 1, NOW());

-- Gán vai trò cho user (User-Role)
INSERT IGNORE INTO user_roles (user_id, role_id, status, created_date) VALUES
(1, 1, 1, NOW()),
(2, 2, 1, NOW()),
(3, 3, 1, NOW()),
(4, 4, 1, NOW());

-- Danh mục sản phẩm mẫu
INSERT IGNORE INTO categories (name, description, image_url, status) VALUES
('Thực phẩm tươi sống', 'Thịt, cá, rau củ quả...', NULL, 1),
('Đồ uống', 'Nước ngọt, nước khoáng, bia...', NULL, 1),
('Đồ gia dụng', 'Đồ dùng nhà bếp, vệ sinh...', NULL, 1);

-- Nhà cung cấp mẫu
INSERT IGNORE INTO suppliers (name, contact_info, address, phone, email, created_date, status) VALUES
('Công ty Thực phẩm ABC', 'Mr. A', '123 Lê Lợi, Q1, TP.HCM', '0909000001', 'abc@food.com', NOW(), 1),
('Công ty Đồ uống XYZ', 'Ms. B', '456 Hai Bà Trưng, Q3, TP.HCM', '0909000002', 'xyz@drink.com', NOW(), 1);

-- Sản phẩm mẫu
INSERT IGNORE INTO products (name, description, image_url, price, purchase_count, review_count, stock, discount_type, discount_value, discount_start_date, discount_end_date, supplier_id, category_id, created_date, status) VALUES
('Thịt heo sạch', 'Thịt heo tươi ngon, đảm bảo vệ sinh.', 'https://cdn.flowmart.com/products/thit-heo.jpg', 120000, 50, 12, 100, 'PERCENTAGE', 10, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 1, 1, NOW(), 1),
('Nước khoáng Lavie', 'Nước khoáng thiên nhiên đóng chai 500ml.', 'https://cdn.flowmart.com/products/lavie.jpg', 6000, 200, 45, 500, NULL, NULL, NULL, NULL, 2, 2, NOW(), 1),
('Nước rửa chén Sunlight', 'Nước rửa chén hương chanh 750ml.', 'https://cdn.flowmart.com/products/sunlight.jpg', 25000, 80, 23, 200, 'FIXED_AMOUNT', 5000, NOW(), DATE_ADD(NOW(), INTERVAL 15 DAY), 2, 3, NOW(), 1),
('Gạo ST25', 'Gạo thơm ST25 chất lượng cao 5kg.', 'https://cdn.flowmart.com/products/gao-st25.jpg', 150000, 120, 67, 80, NULL, NULL, NULL, NULL, 1, 1, NOW(), 1),
('Dầu ăn Neptune', 'Dầu ăn tinh luyện Neptune 1L.', 'https://cdn.flowmart.com/products/dau-an.jpg', 45000, 95, 34, 150, 'PERCENTAGE', 15, NOW(), DATE_ADD(NOW(), INTERVAL 10 DAY), 1, 1, NOW(), 1);

-- Khuyến mãi mẫu
INSERT IGNORE INTO promotions (code, description, discount_type, discount_value, max_discount_amount, min_order_value, image_url, start_date, end_date, usage_limit, usage_count, created_date, status) VALUES
('SALE10', 'Giảm 10% cho đơn hàng từ 200k', 'PERCENTAGE', 10, 50000, 200000, 'https://cdn.flowmart.com/banner/sale10.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 100, 0, NOW(), 1),
('SUMMER20', 'Giảm 20% mùa hè', 'PERCENTAGE', 20, 100000, 300000, 'https://cdn.flowmart.com/banner/summer20.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 60 DAY), 50, 0, NOW(), 1);

INSERT IGNORE INTO promotion_products (promotion_id, product_id) VALUES (1, 1), (1, 2);

-- Phiếu nhập kho mẫu
INSERT IGNORE INTO stock_receipts (receipt_code, supplier_id, receipt_date, notes, status) VALUES
('PNK001', 1, NOW(), 'Nhập hàng đầu tháng', 1);

INSERT IGNORE INTO stock_receipt_items (stock_receipt_id, product_id, quantity, unit_price, total_price) VALUES
(1, 1, 50, 115000, 5750000),
(1, 2, 100, 5800, 580000);

-- Phiếu xuất kho mẫu
INSERT IGNORE INTO stock_issues (issue_code, issue_date, notes, status) VALUES
('PXK001', NOW(), 'Xuất bán lẻ', 1);

INSERT IGNORE INTO stock_issue_items (stock_issue_id, product_id, quantity, unit_price, total_price) VALUES
(1, 1, 10, 120000, 1200000),
(1, 3, 5, 24000, 120000);

-- Lịch sử tồn kho mẫu
INSERT IGNORE INTO inventory_history (product_id, change_quantity, stock_after_change, change_type, change_date, notes) VALUES
(1, 50, 150, 'IMPORT', NOW(), 'Nhập kho đầu tháng'),
(1, -10, 140, 'EXPORT', NOW(), 'Xuất bán lẻ'),
(2, 100, 600, 'IMPORT', NOW(), 'Nhập kho đầu tháng'),
(3, -5, 195, 'EXPORT', NOW(), 'Xuất bán lẻ');

-- Coupon/Mã giảm giá mẫu
INSERT IGNORE INTO coupons (code, description, discount_type, discount_value, min_order_value, max_discount_amount, start_date, end_date, usage_limit, usage_count, is_active, created_date) VALUES
('WELCOME10', 'Giảm 10% cho đơn đầu tiên', 'PERCENTAGE', 10, 100000, 50000, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 100, 0, TRUE, NOW()),
('FREESHIP', 'Miễn phí vận chuyển cho đơn từ 200k', 'FIXED_AMOUNT', 30000, 200000, 30000, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 200, 0, TRUE, NOW());

-- Gán mã giảm giá cho user mẫu
INSERT IGNORE INTO user_coupons (user_id, coupon_id, used, assigned_date) VALUES
(1, 1, FALSE, NOW()),
(2, 2, FALSE, NOW());

-- Slider/Banner động mẫu
INSERT IGNORE INTO sliders (title, image_url, link_url, description, position, is_active, start_date, end_date, created_date) VALUES
('Khuyến mãi hè 2024', 'https://cdn.flowmart.com/banner/summer2024.jpg', 'https://flowmart.com/khuyen-mai', 'Giảm giá cực sốc mùa hè!', 1, TRUE, NOW(), DATE_ADD(NOW(), INTERVAL 15 DAY), NOW()),
('Mã giảm giá FREESHIP', 'https://cdn.flowmart.com/banner/freeship.jpg', 'https://flowmart.com/freeship', 'Nhập mã FREESHIP cho đơn từ 200k', 2, TRUE, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), NOW());

-- Mở rộng dữ liệu khuyến mãi
INSERT IGNORE INTO promotions (code, description, discount_type, discount_value, type, is_active, priority, image_url, start_date, end_date, created_date, status) VALUES
('ORDER20', 'Giảm 20k cho đơn hàng từ 300k', 'FIXED_AMOUNT', 20000, 'ORDER', TRUE, 1, 'https://cdn.flowmart.com/banner/order20.jpg', NOW(), DATE_ADD(NOW(), INTERVAL 20 DAY), NOW(), 1); 