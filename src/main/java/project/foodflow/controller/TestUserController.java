package project.foodflow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.foodflow.security.AuditListenerUser;
import project.foodflow.service.ExampleUserService;

@RestController
@RequestMapping("/api/test-user")
@RequiredArgsConstructor
public class TestUserController {

    private final ExampleUserService exampleUserService;

    @GetMapping("/current")
    public ResponseEntity<String> getCurrentUser() {
        // Lấy thông tin user hiện tại từ AuditListenerUser
        String userId = AuditListenerUser.getCurrentUserId();
        String email = AuditListenerUser.getCurrentEmail();
        String username = AuditListenerUser.getCurrentUsername();
        
        if (userId != null) {
            return ResponseEntity.ok(String.format(
                "Current User - ID: %s, Email: %s, Username: %s", 
                userId, email, username
            ));
        } else {
            return ResponseEntity.ok("No user logged in");
        }
    }

    @GetMapping("/operation")
    public ResponseEntity<String> performOperation() {
        // Thực hiện operation với thông tin user
        exampleUserService.performUserOperation();
        return ResponseEntity.ok("Operation completed successfully");
    }

    @GetMapping("/audit-log")
    public ResponseEntity<String> createAuditLog() {
        // Tạo audit log
        exampleUserService.createUserAuditLog("TEST_ACTION");
        return ResponseEntity.ok("Audit log created");
    }

    @GetMapping("/user-info")
    public ResponseEntity<String> getUserInfo() {
        String userInfo = exampleUserService.getCurrentUserInfo();
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/is-logged-in")
    public ResponseEntity<String> checkLoginStatus() {
        boolean isLoggedIn = exampleUserService.isUserLoggedIn();
        return ResponseEntity.ok("User logged in: " + isLoggedIn);
    }
} 