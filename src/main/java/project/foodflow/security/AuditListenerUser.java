package project.foodflow.security;

import org.springframework.stereotype.Component;

@Component
public class AuditListenerUser {
    
    private static final ThreadLocal<String> currentUserId = new ThreadLocal<>();
    private static final ThreadLocal<String> currentEmail = new ThreadLocal<>();
    private static final ThreadLocal<String> currentUsername = new ThreadLocal<>();
    
    public static void setCurrentUser(String userId, String email, String username) {
        currentUserId.set(userId);
        currentEmail.set(email);
        currentUsername.set(username);
    }
    
    public static String getCurrentUserId() {
        return currentUserId.get();
    }
    
    public static String getCurrentEmail() {
        return currentEmail.get();
    }
    
    public static String getCurrentUsername() {
        return currentUsername.get();
    }
    
    public static void clear() {
        currentUserId.remove();
        currentEmail.remove();
        currentUsername.remove();
    }
    
    public static boolean hasCurrentUser() {
        return currentUserId.get() != null;
    }
} 