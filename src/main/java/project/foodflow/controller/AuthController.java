package project.foodflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.foodflow.dto.AuthRequest;
import project.foodflow.dto.AuthResponse;
import project.foodflow.dto.RegisterRequest;
import project.foodflow.dto.RefreshTokenRequest;
import project.foodflow.service.AuthService;
import project.foodflow.util.ChecksumUtil;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {

    private final AuthService authService;
    private final ChecksumUtil checksumUtil;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Register a new user with email/password or social login")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        log.info("Register request: {}", request);
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user with email/phone and password")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        log.info("Login request: {}", request);
        AuthResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh access token", description = "Get new access token using refresh token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        log.info("Refresh token request: {}", request);
        AuthResponse response = authService.refreshToken(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    @Operation(summary = "Test endpoint", description = "Test endpoint to verify authentication is working")
    public ResponseEntity<String> test() {
        log.info("Test endpoint");
        return ResponseEntity.ok("Authentication is working!");
    }

    @GetMapping("/cors-test")
    @Operation(summary = "CORS test endpoint", description = "Test endpoint to verify CORS is working")
    public ResponseEntity<String> corsTest() {
        log.info("CORS test endpoint");
        return ResponseEntity.ok("CORS is working!");
    }

    @GetMapping("/default-users")
    @Operation(summary = "Get default users info", description = "Get information about default users created by the system")
    public ResponseEntity<String> getDefaultUsers() {
        log.info("Getting default users info");
        return ResponseEntity.ok("""
            Default Users Created:
            
            1. Admin User:
               - Email: admin@foodflow.com
               - Password: admin123
               - Role: SUPER_ADMIN
               
            2. Customer User:
               - Email: customer@foodflow.com
               - Password: customer123
               - Role: CUSTOMER
               
            3. Restaurant Owner:
               - Email: restaurant@foodflow.com
               - Password: restaurant123
               - Role: RESTAURANT_OWNER
               
            4. Delivery Driver:
               - Email: driver@foodflow.com
               - Password: driver123
               - Role: DELIVERY_DRIVER
               
            All users have been created with proper roles and permissions!
            """);
    }

    @PostMapping("/generate-checksum")
    @Operation(summary = "Generate checksum", description = "Generate SHA256 checksum for testing purposes")
    public ResponseEntity<String> generateChecksum(@RequestBody AuthRequest request) {
        log.info("Generate checksum request: {}", request);
        String checksum = checksumUtil.generateChecksum(request.getProviderUserId(), request.getPassword());
        return ResponseEntity.ok(checksum);
    }
} 