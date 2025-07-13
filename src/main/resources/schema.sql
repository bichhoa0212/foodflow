-- Drop existing tables if they exist (in reverse order of dependencies)
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