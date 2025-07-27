package project.foodflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.foodflow.security.AuditListenerUser;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductAuditService {
    
    // Giả lập database
    private final Map<String, String> productDatabase = new HashMap<>();
    
    /**
     * Tạo sản phẩm mới với audit log tự động
     */
    public String createProduct(String productName, String description) {
        // Kiểm tra user có đăng nhập không
        if (!AuditListenerUser.hasCurrentUser()) {
            throw new RuntimeException("User not logged in");
        }
        
        String userId = AuditListenerUser.getCurrentUserId();
        String email = AuditListenerUser.getCurrentEmail();
        
        // Tạo audit log trước khi tạo sản phẩm
        createAuditLog("CREATE_PRODUCT_START", "Creating product: " + productName);
        
        // Tạo sản phẩm (giả lập)
        String productId = "PROD_" + System.currentTimeMillis();
        productDatabase.put(productId, productName);
        
        // Tạo audit log sau khi tạo sản phẩm
        createAuditLog("CREATE_PRODUCT_SUCCESS", 
            String.format("Product created - ID: %s, Name: %s, Created by: %s (%s)", 
                productId, productName, userId, email));
        
        return productId;
    }
    
    /**
     * Cập nhật sản phẩm với audit log tự động
     */
    public boolean updateProduct(String productId, String newName) {
        // Kiểm tra user có đăng nhập không
        if (!AuditListenerUser.hasCurrentUser()) {
            throw new RuntimeException("User not logged in");
        }
        
        String userId = AuditListenerUser.getCurrentUserId();
        String email = AuditListenerUser.getCurrentEmail();
        
        // Kiểm tra sản phẩm có tồn tại không
        if (!productDatabase.containsKey(productId)) {
            createAuditLog("UPDATE_PRODUCT_FAILED", "Product not found: " + productId);
            return false;
        }
        
        String oldName = productDatabase.get(productId);
        
        // Tạo audit log trước khi cập nhật
        createAuditLog("UPDATE_PRODUCT_START", 
            String.format("Updating product - ID: %s, Old name: %s, New name: %s", 
                productId, oldName, newName));
        
        // Cập nhật sản phẩm
        productDatabase.put(productId, newName);
        
        // Tạo audit log sau khi cập nhật
        createAuditLog("UPDATE_PRODUCT_SUCCESS", 
            String.format("Product updated - ID: %s, Updated by: %s (%s)", 
                productId, userId, email));
        
        return true;
    }
    
    /**
     * Xóa sản phẩm với audit log tự động
     */
    public boolean deleteProduct(String productId) {
        // Kiểm tra user có đăng nhập không
        if (!AuditListenerUser.hasCurrentUser()) {
            throw new RuntimeException("User not logged in");
        }
        
        String userId = AuditListenerUser.getCurrentUserId();
        String email = AuditListenerUser.getCurrentEmail();
        
        // Kiểm tra sản phẩm có tồn tại không
        if (!productDatabase.containsKey(productId)) {
            createAuditLog("DELETE_PRODUCT_FAILED", "Product not found: " + productId);
            return false;
        }
        
        String productName = productDatabase.get(productId);
        
        // Tạo audit log trước khi xóa
        createAuditLog("DELETE_PRODUCT_START", 
            String.format("Deleting product - ID: %s, Name: %s", productId, productName));
        
        // Xóa sản phẩm
        productDatabase.remove(productId);
        
        // Tạo audit log sau khi xóa
        createAuditLog("DELETE_PRODUCT_SUCCESS", 
            String.format("Product deleted - ID: %s, Deleted by: %s (%s)", 
                productId, userId, email));
        
        return true;
    }
    
    /**
     * Lấy thông tin sản phẩm
     */
    public String getProduct(String productId) {
        if (!productDatabase.containsKey(productId)) {
            return "Product not found";
        }
        
        // Tạo audit log cho việc xem sản phẩm
        createAuditLog("VIEW_PRODUCT", "Viewed product: " + productId);
        
        return productDatabase.get(productId);
    }
    
    /**
     * Lấy danh sách tất cả sản phẩm
     */
    public Map<String, String> getAllProducts() {
        // Tạo audit log cho việc xem danh sách sản phẩm
        createAuditLog("VIEW_ALL_PRODUCTS", "Viewed all products");
        
        return new HashMap<>(productDatabase);
    }
    
    /**
     * Tạo audit log với thông tin user hiện tại
     */
    private void createAuditLog(String action, String details) {
        String userId = AuditListenerUser.getCurrentUserId();
        String email = AuditListenerUser.getCurrentEmail();
        String username = AuditListenerUser.getCurrentUsername();
        
        System.out.println("=== PRODUCT AUDIT LOG ===");
        System.out.println("Action: " + action);
        System.out.println("Details: " + details);
        System.out.println("User ID: " + userId);
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);
        System.out.println("Timestamp: " + System.currentTimeMillis());
        System.out.println("=========================");
    }
} 