-- RBAC
CREATE TABLE IF NOT EXISTS app_users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20) UNIQUE,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    status INT NOT NULL,
    created_date DATETIME(6) NOT NULL,
    modified_date DATETIME(6),
    provider VARCHAR(50),
    gender INT,
    avatar VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS app_roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255),
    status INT NOT NULL,
    created_date DATETIME(6) NOT NULL,
    modified_date DATETIME(6)
);

CREATE TABLE IF NOT EXISTS app_permissions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255),
    status INT NOT NULL,
    created_date DATETIME(6) NOT NULL,
    modified_date DATETIME(6)
);

CREATE TABLE IF NOT EXISTS user_roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    assigned_at DATETIME(6),
    status INT NOT NULL,
    created_date DATETIME(6) NOT NULL,
    modified_date DATETIME(6),
    FOREIGN KEY (user_id) REFERENCES app_users(id),
    FOREIGN KEY (role_id) REFERENCES app_roles(id)
);

CREATE TABLE IF NOT EXISTS role_permissions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    status INT NOT NULL,
    created_date DATETIME(6) NOT NULL,
    modified_date DATETIME(6),
    FOREIGN KEY (role_id) REFERENCES app_roles(id),
    FOREIGN KEY (permission_id) REFERENCES app_permissions(id)
);

CREATE TABLE IF NOT EXISTS user_groups (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status INT NOT NULL,
    created_date DATETIME(6) NOT NULL,
    modified_date DATETIME(6),
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES app_roles(id)
);

CREATE TABLE IF NOT EXISTS group_users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    group_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status INT NOT NULL,
    created_date DATETIME(6) NOT NULL,
    modified_date DATETIME(6),
    FOREIGN KEY (group_id) REFERENCES user_groups(id),
    FOREIGN KEY (user_id) REFERENCES app_users(id)
);

-- Danh mục sản phẩm
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(500),
    image_url VARCHAR(500),
    status INT NOT NULL
);

-- Nhà cung cấp
CREATE TABLE IF NOT EXISTS suppliers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    contact_info VARCHAR(500),
    address VARCHAR(255),
    phone VARCHAR(100),
    email VARCHAR(100),
    created_date DATETIME(6) NOT NULL,
    modified_date DATETIME(6),
    status INT NOT NULL
);

-- Sản phẩm
CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    price DECIMAL(10,2) NOT NULL,
    purchase_count INT NOT NULL DEFAULT 0,
    review_count INT NOT NULL DEFAULT 0,
    stock INT NOT NULL DEFAULT 0,
    discount_type VARCHAR(20),
    discount_value DECIMAL(10,2),
    discount_start_date DATETIME(6),
    discount_end_date DATETIME(6),
    supplier_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    created_date DATETIME(6) NOT NULL,
    modified_date DATETIME(6),
    status INT NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Khuyến mãi
CREATE TABLE IF NOT EXISTS promotions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    discount_type VARCHAR(20) NOT NULL,
    discount_value DECIMAL(10,2) NOT NULL,
    max_discount_amount DECIMAL(10,2),
    min_order_value DECIMAL(10,2),
    image_url VARCHAR(500),
    start_date DATETIME(6) NOT NULL,
    end_date DATETIME(6) NOT NULL,
    usage_limit INT,
    usage_count INT,
    created_date DATETIME(6) NOT NULL,
    status INT NOT NULL
);

CREATE TABLE IF NOT EXISTS promotion_products (
    promotion_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (promotion_id, product_id),
    FOREIGN KEY (promotion_id) REFERENCES promotions(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Đơn hàng
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_code VARCHAR(20) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    delivery_address VARCHAR(500) NOT NULL,
    contact_phone VARCHAR(20) NOT NULL,
    notes TEXT,
    subtotal_amount DECIMAL(10,2) NOT NULL,
    shipping_fee DECIMAL(10,2),
    discount_amount DECIMAL(10,2),
    total_amount DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(50),
    order_date DATETIME(6) NOT NULL,
    completed_date DATETIME(6),
    status INT NOT NULL,
    promotion_id BIGINT,
    shipper_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES app_users(id),
    FOREIGN KEY (promotion_id) REFERENCES promotions(id)
    -- shipper_id có thể tham chiếu đến bảng shipper nếu có
);

CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price_at_order DECIMAL(10,2) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Phiếu nhập kho
CREATE TABLE IF NOT EXISTS stock_receipts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    receipt_code VARCHAR(50) NOT NULL UNIQUE,
    supplier_id BIGINT NOT NULL,
    receipt_date DATETIME(6) NOT NULL,
    notes VARCHAR(500),
    status INT NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

CREATE TABLE IF NOT EXISTS stock_receipt_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    stock_receipt_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (stock_receipt_id) REFERENCES stock_receipts(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Phiếu xuất kho
CREATE TABLE IF NOT EXISTS stock_issues (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    issue_code VARCHAR(50) NOT NULL UNIQUE,
    issue_date DATETIME(6) NOT NULL,
    notes VARCHAR(500),
    status INT NOT NULL
);

CREATE TABLE IF NOT EXISTS stock_issue_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    stock_issue_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (stock_issue_id) REFERENCES stock_issues(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Lịch sử tồn kho
CREATE TABLE IF NOT EXISTS inventory_history (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    change_quantity INT NOT NULL,
    stock_after_change INT NOT NULL,
    change_type VARCHAR(20) NOT NULL,
    change_date DATETIME(6) NOT NULL,
    notes VARCHAR(500),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Shipper
CREATE TABLE IF NOT EXISTS shippers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    status INT NOT NULL,
    created_date DATETIME(6) NOT NULL
);

-- Lịch sử trạng thái đơn hàng
CREATE TABLE IF NOT EXISTS order_status_history (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    changed_at DATETIME(6) NOT NULL,
    note VARCHAR(255),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- Giao dịch thanh toán
CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    method VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    gateway_transaction_id VARCHAR(255),
    transaction_date DATETIME(6) NOT NULL,
    notes TEXT,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- Thông báo
CREATE TABLE IF NOT EXISTS notifications (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    type VARCHAR(50),
    is_read BOOLEAN DEFAULT FALSE,
    created_date DATETIME(6) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES app_users(id)
);

-- Coupon/Mã giảm giá
CREATE TABLE IF NOT EXISTS coupons (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    discount_type VARCHAR(20) NOT NULL, -- PERCENTAGE, FIXED_AMOUNT
    discount_value DECIMAL(10,2) NOT NULL,
    min_order_value DECIMAL(10,2),
    max_discount_amount DECIMAL(10,2),
    start_date DATETIME(6) NOT NULL,
    end_date DATETIME(6) NOT NULL,
    usage_limit INT,
    usage_count INT,
    is_active BOOLEAN DEFAULT TRUE,
    created_date DATETIME(6) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_coupons (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    coupon_id BIGINT NOT NULL,
    used BOOLEAN DEFAULT FALSE,
    used_date DATETIME(6),
    assigned_date DATETIME(6) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES app_users(id),
    FOREIGN KEY (coupon_id) REFERENCES coupons(id)
);

-- Slider động/Banner quảng cáo
CREATE TABLE IF NOT EXISTS sliders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    image_url VARCHAR(500) NOT NULL,
    link_url VARCHAR(500),
    description VARCHAR(255),
    position INT DEFAULT 0,
    is_active BOOLEAN DEFAULT TRUE,
    start_date DATETIME(6),
    end_date DATETIME(6),
    created_date DATETIME(6) NOT NULL
);

-- Đã xóa đoạn ALTER TABLE promotions để tránh lỗi duplicate column


