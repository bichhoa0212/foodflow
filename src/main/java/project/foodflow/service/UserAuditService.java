package project.foodflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.foodflow.security.AuditListenerUser;

@Service
@RequiredArgsConstructor
public class UserAuditService {
    
    /**
     * Lấy thông tin user hiện tại mà không cần Authentication parameter
     */
    public String getCurrentUserInfo() {
        String userId = AuditListenerUser.getCurrentUserId();
        String email = AuditListenerUser.getCurrentEmail();
        String username = AuditListenerUser.getCurrentUsername();
        
        if (userId != null) {
            return String.format("User ID: %s, Email: %s, Username: %s", userId, email, username);
        }
        return "No user logged in";
    }
    
    /**
     * Tạo audit log với thông tin user hiện tại
     */
    public void createAuditLog(String action, String details) {
        String userId = AuditListenerUser.getCurrentUserId();
        String email = AuditListenerUser.getCurrentEmail();
        String username = AuditListenerUser.getCurrentUsername();
        
        // Tạo audit log entry
        System.out.println("=== AUDIT LOG ===");
        System.out.println("Action: " + action);
        System.out.println("Details: " + details);
        System.out.println("User ID: " + userId);
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);
        System.out.println("Timestamp: " + System.currentTimeMillis());
        System.out.println("=================");
    }
    
    /**
     * Kiểm tra user có quyền thực hiện action không
     */
    public boolean canPerformAction(String action) {
        if (!AuditListenerUser.hasCurrentUser()) {
            return false;
        }
        
        String userId = AuditListenerUser.getCurrentUserId();
        // Logic kiểm tra quyền dựa trên userId và action
        // Ví dụ: chỉ user có ID > 0 mới có thể thực hiện action
        return userId != null && !userId.isEmpty();
    }
    
    /**
     * Lấy userId hiện tại
     */
    public String getCurrentUserId() {
        return AuditListenerUser.getCurrentUserId();
    }
    
    /**
     * Lấy email hiện tại
     */
    public String getCurrentEmail() {
        return AuditListenerUser.getCurrentEmail();
    }
    
    /**
     * Lấy username hiện tại
     */
    public String getCurrentUsername() {
        return AuditListenerUser.getCurrentUsername();
    }
    
    /**
     * Kiểm tra user có đăng nhập không
     */
    public boolean isUserLoggedIn() {
        return AuditListenerUser.hasCurrentUser();
    }
} 