package project.foodflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.foodflow.security.AuditListenerUser;

@Service
@RequiredArgsConstructor
public class ExampleUserService {
    
    public void performUserOperation() {
        // Lấy thông tin user hiện tại từ AuditListenerUser
        String currentUserId = AuditListenerUser.getCurrentUserId();
        String currentEmail = AuditListenerUser.getCurrentEmail();
        String currentUsername = AuditListenerUser.getCurrentUsername();
        
        System.out.println("Thực hiện operation với:");
        System.out.println("User ID: " + currentUserId);
        System.out.println("Email: " + currentEmail);
        System.out.println("Username: " + currentUsername);
        
        // Thực hiện các logic khác với thông tin user
        // ...
    }
    
    public void createUserAuditLog(String action) {
        String userId = AuditListenerUser.getCurrentUserId();
        String email = AuditListenerUser.getCurrentEmail();
        String username = AuditListenerUser.getCurrentUsername();
        
        // Tạo audit log với thông tin user
        System.out.println("Audit Log - Action: " + action);
        System.out.println("User ID: " + userId);
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);
    }
    
    public boolean isUserLoggedIn() {
        return AuditListenerUser.hasCurrentUser();
    }
    
    public String getCurrentUserInfo() {
        if (AuditListenerUser.hasCurrentUser()) {
            return String.format("User ID: %s, Email: %s, Username: %s",
                    AuditListenerUser.getCurrentUserId(),
                    AuditListenerUser.getCurrentEmail(),
                    AuditListenerUser.getCurrentUsername());
        }
        return "No user logged in";
    }
} 