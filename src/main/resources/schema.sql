-- Drop existing tables if they exist (in reverse order of dependencies)
DROP TABLE IF EXISTS user_favorite_restaurants;
DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS shipper_locations;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS shippers;
DROP TABLE IF EXISTS promotions;
DROP TABLE IF EXISTS group_users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS role_permissions;
DROP TABLE IF EXISTS user_groups;
DROP TABLE IF EXISTS app_users;
DROP TABLE IF EXISTS app_roles;
DROP TABLE IF EXISTS app_permissions;

-- Create tables in correct order
CREATE TABLE app_permissions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(500),
    created_date DATETIME(6) NOT NULL,
    created_user BIGINT,
    modified_date DATETIME(6),
    modified_user BIGINT,
    status INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE app_roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(500),
    created_date DATETIME(6) NOT NULL,
    created_user BIGINT,
    modified_date DATETIME(6),
    modified_user BIGINT,
    status INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE app_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20) UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    sex INTEGER,
    address VARCHAR(500),
    status INTEGER NOT NULL DEFAULT 1,
    date_of_birth VARCHAR(20),
    provider VARCHAR(20),
    provider_user_id VARCHAR(255),
    provider_meta_data TEXT,
    created_date DATETIME(6) NOT NULL,
    created_user BIGINT,
    modified_date DATETIME(6),
    modified_user BIGINT,
    active_code VARCHAR(10),
    active_date DATETIME(6)
);

CREATE TABLE user_groups (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    role_id BIGINT,
    created_date DATETIME(6) NOT NULL,
    created_user BIGINT,
    modified_date DATETIME(6),
    modified_user BIGINT,
    status INTEGER NOT NULL DEFAULT 1,
    FOREIGN KEY (role_id) REFERENCES app_roles(id)
);

CREATE TABLE role_permissions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    assigned_at DATETIME(6),
    status INTEGER NOT NULL DEFAULT 1,
    created_date DATETIME(6) NOT NULL,
    created_user BIGINT,
    modified_date DATETIME(6),
    modified_user BIGINT,
    FOREIGN KEY (role_id) REFERENCES app_roles(id),
    FOREIGN KEY (permission_id) REFERENCES app_permissions(id)
);

CREATE TABLE user_roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    assigned_at DATETIME(6),
    status INTEGER NOT NULL DEFAULT 1,
    created_date DATETIME(6) NOT NULL,
    created_user BIGINT,
    modified_date DATETIME(6),
    modified_user BIGINT,
    FOREIGN KEY (user_id) REFERENCES app_users(id),
    FOREIGN KEY (role_id) REFERENCES app_roles(id)
);

CREATE TABLE group_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    group_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    assigned_at DATETIME(6),
    status INTEGER NOT NULL DEFAULT 1,
    created_date DATETIME(6) NOT NULL,
    created_user BIGINT,
    modified_date DATETIME(6),
    modified_user BIGINT,
    FOREIGN KEY (group_id) REFERENCES user_groups(id),
    FOREIGN KEY (user_id) REFERENCES app_users(id)
); 

-- =============================
-- Bảng hệ thống đặt giao đồ ăn trực tuyến (FoodFlow)
-- =============================

-- Bảng khuyến mãi/voucher (phải tạo trước để orders tham chiếu)
CREATE TABLE promotions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID khuyến mãi
    code VARCHAR(50) NOT NULL UNIQUE, -- Mã code khuyến mãi
    description TEXT, -- Mô tả khuyến mãi
    discount_type VARCHAR(20) NOT NULL, -- Loại giảm giá: PERCENTAGE hoặc FIXED_AMOUNT
    discount_value DECIMAL(10, 2) NOT NULL, -- Giá trị giảm giá
    max_discount_amount DECIMAL(10, 2), -- Số tiền giảm tối đa (nếu giảm theo phần trăm)
    min_order_value DECIMAL(10, 2) DEFAULT 0.00, -- Giá trị đơn hàng tối thiểu để áp dụng
    start_date DATETIME(6) NOT NULL, -- Ngày bắt đầu áp dụng
    end_date DATETIME(6) NOT NULL, -- Ngày kết thúc áp dụng
    usage_limit INT, -- Số lượt sử dụng tối đa
    usage_count INT DEFAULT 0, -- Số lượt đã sử dụng
    created_date DATETIME(6) NOT NULL, -- Ngày tạo
    status INTEGER NOT NULL DEFAULT 1 -- 1: Đang hoạt động, 2: Hết hạn, 3: Vô hiệu hóa
);

-- Bảng shipper (phải tạo trước để orders tham chiếu)
CREATE TABLE shippers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID shipper
    user_id BIGINT NOT NULL UNIQUE, -- Tài khoản người dùng shipper
    license_plate VARCHAR(20) NOT NULL, -- Biển số xe
    vehicle_type VARCHAR(50), -- Loại phương tiện (Motorbike, Bicycle...)
    rating DECIMAL(3, 2) DEFAULT 5.00, -- Điểm đánh giá trung bình
    created_date DATETIME(6) NOT NULL, -- Ngày tạo tài khoản shipper
    status INTEGER NOT NULL DEFAULT 1, -- 1: Offline, 2: Online, 3: Bận/Đang giao hàng
    FOREIGN KEY (user_id) REFERENCES app_users(id)
);

-- Bảng nhà hàng/điểm bán
CREATE TABLE restaurants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID nhà hàng
    name VARCHAR(255) NOT NULL, -- Tên nhà hàng
    address VARCHAR(500) NOT NULL, -- Địa chỉ nhà hàng
    phone_number VARCHAR(20), -- Số điện thoại nhà hàng
    logo_url VARCHAR(500), -- URL logo nhà hàng
    cover_image_url VARCHAR(500), -- URL ảnh bìa nhà hàng
    description TEXT, -- Mô tả nhà hàng
    opening_time TIME, -- Giờ mở cửa
    closing_time TIME, -- Giờ đóng cửa
    owner_id BIGINT, -- Người sở hữu/quản lý nhà hàng (liên kết app_users)
    rating DECIMAL(3, 2) DEFAULT 0.00, -- Điểm đánh giá trung bình
    created_date DATETIME(6) NOT NULL, -- Ngày tạo
    modified_date DATETIME(6), -- Ngày cập nhật
    status INTEGER NOT NULL DEFAULT 1, -- 1: Mở, 2: Đóng, 3: Tạm đóng
    FOREIGN KEY (owner_id) REFERENCES app_users(id)
);

-- Bảng danh mục sản phẩm (Pizza, Burger, Nước uống...)
CREATE TABLE product_categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID danh mục
    name VARCHAR(100) NOT NULL UNIQUE, -- Tên danh mục
    description VARCHAR(500), -- Mô tả danh mục
    image_url VARCHAR(500), -- Ảnh đại diện danh mục
    status INTEGER NOT NULL DEFAULT 1 -- 1: Hiện, 2: Ẩn
);

-- Bảng sản phẩm/món ăn
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID sản phẩm
    name VARCHAR(255) NOT NULL, -- Tên sản phẩm
    description TEXT, -- Mô tả sản phẩm
    image_url VARCHAR(500), -- Ảnh sản phẩm
    price DECIMAL(10, 2) NOT NULL, -- Giá sản phẩm
    restaurant_id BIGINT NOT NULL, -- Nhà hàng cung cấp
    category_id BIGINT NOT NULL, -- Danh mục sản phẩm
    created_date DATETIME(6) NOT NULL, -- Ngày tạo
    modified_date DATETIME(6), -- Ngày cập nhật
    status INTEGER NOT NULL DEFAULT 1, -- 1: Còn hàng, 2: Hết hàng
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
    FOREIGN KEY (category_id) REFERENCES product_categories(id)
);

-- Bảng đơn hàng (tham chiếu promotions, shippers)
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID đơn hàng
    order_code VARCHAR(20) NOT NULL UNIQUE, -- Mã đơn hàng (ví dụ: HD-110725)
    user_id BIGINT NOT NULL, -- Người đặt hàng
    restaurant_id BIGINT NOT NULL, -- Nhà hàng
    delivery_address VARCHAR(500) NOT NULL, -- Địa chỉ giao hàng
    contact_phone VARCHAR(20) NOT NULL, -- Số điện thoại liên hệ
    notes TEXT, -- Ghi chú đơn hàng
    subtotal_amount DECIMAL(10, 2) NOT NULL, -- Tổng tiền trước giảm giá
    shipping_fee DECIMAL(10, 2) DEFAULT 0.00, -- Phí vận chuyển
    discount_amount DECIMAL(10, 2) DEFAULT 0.00, -- Số tiền giảm giá
    total_amount DECIMAL(10, 2) NOT NULL, -- Tổng tiền thanh toán cuối cùng
    payment_method VARCHAR(50), -- Phương thức thanh toán (COD, Credit Card, MoMo...)
    order_date DATETIME(6) NOT NULL, -- Ngày đặt hàng
    completed_date DATETIME(6), -- Ngày hoàn thành đơn hàng
    status INTEGER NOT NULL DEFAULT 1, -- 1: Chờ xác nhận, 2: Đã xác nhận, 3: Đang chuẩn bị, 4: Đang giao, 5: Hoàn thành, 6: Đã hủy
    promotion_id BIGINT, -- Khuyến mãi áp dụng
    shipper_id BIGINT, -- Shipper giao hàng
    FOREIGN KEY (user_id) REFERENCES app_users(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
    FOREIGN KEY (promotion_id) REFERENCES promotions(id),
    FOREIGN KEY (shipper_id) REFERENCES shippers(id)
);

-- Bảng chi tiết đơn hàng
CREATE TABLE order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID chi tiết đơn hàng
    order_id BIGINT NOT NULL, -- Đơn hàng
    product_id BIGINT NOT NULL, -- Sản phẩm
    quantity INTEGER NOT NULL, -- Số lượng
    price_at_order DECIMAL(10, 2) NOT NULL, -- Giá tại thời điểm đặt
    total_price DECIMAL(10, 2) NOT NULL, -- Tổng tiền cho sản phẩm này
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Bảng đánh giá
CREATE TABLE reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID đánh giá
    user_id BIGINT NOT NULL, -- Người đánh giá
    order_id BIGINT NOT NULL, -- Đơn hàng được đánh giá
    restaurant_id BIGINT NOT NULL, -- Nhà hàng được đánh giá
    rating INT NOT NULL, -- Số sao đánh giá (1-5)
    comment TEXT, -- Nội dung bình luận
    created_date DATETIME(6) NOT NULL, -- Ngày tạo đánh giá
    FOREIGN KEY (user_id) REFERENCES app_users(id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

-- Bảng vị trí shipper (realtime)
CREATE TABLE shipper_locations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID vị trí
    shipper_id BIGINT NOT NULL, -- Shipper
    latitude DECIMAL(10, 8) NOT NULL, -- Vĩ độ
    longitude DECIMAL(11, 8) NOT NULL, -- Kinh độ
    last_updated DATETIME(6) NOT NULL, -- Thời điểm cập nhật vị trí cuối cùng
    FOREIGN KEY (shipper_id) REFERENCES shippers(id)
);

-- Bảng giao dịch thanh toán
CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID giao dịch
    order_id BIGINT NOT NULL, -- Đơn hàng liên quan
    gateway VARCHAR(50) NOT NULL, -- Cổng thanh toán (MOMO, VNPAY, COD...)
    gateway_transaction_id VARCHAR(255), -- Mã giao dịch từ nhà cung cấp thanh toán
    amount DECIMAL(10, 2) NOT NULL, -- Số tiền giao dịch
    transaction_date DATETIME(6) NOT NULL, -- Thời điểm giao dịch
    status INTEGER NOT NULL, -- 1: Thành công, 2: Thất bại, 3: Đang xử lý
    notes TEXT, -- Ghi chú giao dịch
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- Bảng thông báo cho người dùng
CREATE TABLE notifications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID thông báo
    user_id BIGINT NOT NULL, -- Người nhận thông báo
    title VARCHAR(255) NOT NULL, -- Tiêu đề thông báo
    message TEXT NOT NULL, -- Nội dung thông báo
    type VARCHAR(50), -- Loại thông báo (ORDER_STATUS, PROMOTION, SYSTEM...)
    target_id BIGINT, -- ID đối tượng liên quan (order_id, promotion_id...)
    is_read BOOLEAN DEFAULT FALSE, -- Đã đọc hay chưa
    created_date DATETIME(6) NOT NULL, -- Ngày tạo thông báo
    FOREIGN KEY (user_id) REFERENCES app_users(id)
);

-- Bảng nhà hàng yêu thích của người dùng
CREATE TABLE user_favorite_restaurants (
    user_id BIGINT NOT NULL, -- Người dùng
    restaurant_id BIGINT NOT NULL, -- Nhà hàng được yêu thích
    created_date DATETIME(6) NOT NULL, -- Ngày thêm vào danh sách yêu thích
    PRIMARY KEY (user_id, restaurant_id),
    FOREIGN KEY (user_id) REFERENCES app_users(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

-- =============================
-- Kết thúc bổ sung bảng FoodFlow
-- ============================= 