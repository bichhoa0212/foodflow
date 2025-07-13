-- =====================================================
-- FOODFLOW DATABASE INITIALIZATION SCRIPT
-- =====================================================

-- Create database if not exists
CREATE DATABASE IF NOT EXISTS foodflow
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- Create user if not exists (for MySQL 8.0+)
CREATE USER IF NOT EXISTS 'foodflow'@'localhost' IDENTIFIED BY '123456';

-- Grant privileges to the user
GRANT ALL PRIVILEGES ON foodflow.* TO 'foodflow'@'localhost';

-- Grant additional privileges for database creation
GRANT CREATE, DROP, ALTER, INDEX, REFERENCES ON foodflow.* TO 'foodflow'@'localhost';

-- Flush privileges to apply changes
FLUSH PRIVILEGES;

-- Use the database
USE foodflow;

-- Show confirmation
SELECT 'FoodFlow database and user created successfully!' as message; 