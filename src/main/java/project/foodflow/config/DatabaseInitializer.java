package project.foodflow.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
@Order(1) // Run before DataInitializer
@Slf4j
public class DatabaseInitializer implements CommandLineRunner {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting database initialization...");
        
        try {
            // Extract database URL without database name
            String baseUrl = dataSourceUrl.substring(0, dataSourceUrl.lastIndexOf("/"));
            String databaseName = "foodflow";
            
            // Connect to MySQL server (without specifying database)
            String serverUrl = baseUrl + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            
            try (Connection connection = DriverManager.getConnection(serverUrl, username, password)) {
                log.info("Connected to MySQL server successfully");
                
                // Create database if not exists
                createDatabaseIfNotExists(connection, databaseName);
                
                // Create user if not exists
                createUserIfNotExists(connection);
                
                log.info("Database initialization completed successfully!");
                
            } catch (Exception e) {
                log.warn("Could not connect with configured user, trying with root...");
                // Try with root user if configured user doesn't have privileges
                tryWithRootUser();
            }
            
        } catch (Exception e) {
            log.error("Database initialization failed: {}", e.getMessage());
            log.info("Please run the init-database.sql script manually or ensure MySQL is running");
        }
    }

    private void createDatabaseIfNotExists(Connection connection, String databaseName) throws Exception {
        String createDbSql = "CREATE DATABASE IF NOT EXISTS " + databaseName + 
                           " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";
        
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createDbSql);
            log.info("Database '{}' created or already exists", databaseName);
        }
    }

    private void createUserIfNotExists(Connection connection) throws Exception {
        String createUserSql = "CREATE USER IF NOT EXISTS 'foodflow'@'localhost' IDENTIFIED BY '123456'";
        String grantPrivilegesSql = "GRANT ALL PRIVILEGES ON foodflow.* TO 'foodflow'@'localhost'";
        String flushPrivilegesSql = "FLUSH PRIVILEGES";
        
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createUserSql);
            statement.executeUpdate(grantPrivilegesSql);
            statement.executeUpdate(flushPrivilegesSql);
            log.info("User 'foodflow' created or already exists with proper privileges");
        }
    }

    private void tryWithRootUser() {
        try {
            // Try with root user (common default)
            String rootUrl = dataSourceUrl.replace("foodflow", "").replace("?createDatabaseIfNotExist=true", "");
            rootUrl = rootUrl.substring(0, rootUrl.lastIndexOf("/")) + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            
            try (Connection connection = DriverManager.getConnection(rootUrl, "root", "root")) {
                log.info("Connected with root user, creating database and user...");
                
                createDatabaseIfNotExists(connection, "foodflow");
                createUserIfNotExists(connection);
                
                log.info("Database and user created successfully with root privileges!");
                
            } catch (Exception e) {
                log.error("Failed to connect with root user: {}", e.getMessage());
                log.info("Please check your MySQL installation and run init-database.sql manually");
            }
            
        } catch (Exception e) {
            log.error("Error in root user connection attempt: {}", e.getMessage());
        }
    }
} 