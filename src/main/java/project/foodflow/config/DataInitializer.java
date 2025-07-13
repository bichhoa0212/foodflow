package project.foodflow.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import project.foodflow.entity.*;
import project.foodflow.service.UserService;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Component
@Order(2) // Run after DatabaseInitializer
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting data initialization...");
        
        // Initialize default data
        initializeDefaultData();
        
        log.info("Data initialization completed!");
    }

    private void initializeDefaultData() {
        try {
            // Check if admin user already exists
            if (!userService.existsByEmail("admin@foodflow.com")) {
                createDefaultUsers();
                log.info("Default users created successfully");
            } else {
                log.info("Default users already exist, skipping creation");
            }
        } catch (Exception e) {
            log.error("Error during data initialization: {}", e.getMessage(), e);
        }
    }

    private void createDefaultUsers() {
        // Create Admin User
        User adminUser = new User();
        adminUser.setName("System Administrator");
        adminUser.setEmail("admin@foodflow.com");
        adminUser.setPhone("0123456789");
        adminUser.setPassword(passwordEncoder.encode("admin123"));
        adminUser.setSex(1);
        adminUser.setAddress("System Address");
        adminUser.setDateOfBirth("01/01/1990");
        adminUser.setProvider("EMAIL");
        adminUser.setProviderUserId("admin");
        adminUser.setStatus(1);
        adminUser.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        adminUser.setCreatedUser(1L);
        adminUser.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        adminUser.setModifiedUser(1L);

        userService.save(adminUser);
        log.info("Admin user created: {}", adminUser.getEmail());

        // Create Customer User
        User customerUser = new User();
        customerUser.setName("John Customer");
        customerUser.setEmail("customer@foodflow.com");
        customerUser.setPhone("0987654321");
        customerUser.setPassword(passwordEncoder.encode("customer123"));
        customerUser.setSex(1);
        customerUser.setAddress("Customer Address");
        customerUser.setDateOfBirth("01/01/1995");
        customerUser.setProvider("EMAIL");
        customerUser.setProviderUserId("customer");
        customerUser.setStatus(1);
        customerUser.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        customerUser.setCreatedUser(1L);
        customerUser.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        customerUser.setModifiedUser(1L);

        userService.save(customerUser);
        log.info("Customer user created: {}", customerUser.getEmail());

        // Create Restaurant Owner User
        User restaurantOwner = new User();
        restaurantOwner.setName("Restaurant Owner");
        restaurantOwner.setEmail("restaurant@foodflow.com");
        restaurantOwner.setPhone("0555555555");
        restaurantOwner.setPassword(passwordEncoder.encode("restaurant123"));
        restaurantOwner.setSex(1);
        restaurantOwner.setAddress("Restaurant Address");
        restaurantOwner.setDateOfBirth("01/01/1985");
        restaurantOwner.setProvider("EMAIL");
        restaurantOwner.setProviderUserId("restaurant");
        restaurantOwner.setStatus(1);
        restaurantOwner.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        restaurantOwner.setCreatedUser(1L);
        restaurantOwner.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        restaurantOwner.setModifiedUser(1L);

        userService.save(restaurantOwner);
        log.info("Restaurant owner created: {}", restaurantOwner.getEmail());

        // Create Delivery Driver User
        User deliveryDriver = new User();
        deliveryDriver.setName("Delivery Driver");
        deliveryDriver.setEmail("driver@foodflow.com");
        deliveryDriver.setPhone("0666666666");
        deliveryDriver.setPassword(passwordEncoder.encode("driver123"));
        deliveryDriver.setSex(1);
        deliveryDriver.setAddress("Driver Address");
        deliveryDriver.setDateOfBirth("01/01/1992");
        deliveryDriver.setProvider("EMAIL");
        deliveryDriver.setProviderUserId("driver");
        deliveryDriver.setStatus(1);
        deliveryDriver.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        deliveryDriver.setCreatedUser(1L);
        deliveryDriver.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        deliveryDriver.setModifiedUser(1L);

        userService.save(deliveryDriver);
        log.info("Delivery driver created: {}", deliveryDriver.getEmail());
    }
} 