# Hướng dẫn sử dụng AuditListenerUser (Không cần Authentication)

## Tổng quan

`AuditListenerUser` là một biến toàn cục sử dụng `ThreadLocal` để lưu trữ thông tin user hiện tại (`userId`, `email`, `username`) mà không cần sử dụng `Authentication` parameter hoặc `SecurityContextHolder`.

## Ưu điểm

1. **Đơn giản**: Không cần inject `Authentication` parameter
2. **Toàn cục**: Có thể truy cập từ bất kỳ đâu trong ứng dụng
3. **Thread-safe**: Sử dụng `ThreadLocal`
4. **Tự động cleanup**: Thông tin tự động được clear sau mỗi request

## Cách sử dụng

### 1. Trong Service (Không cần Authentication parameter)

```java
@Service
public class YourService {
    
    public void yourMethod() {
        // Lấy thông tin user hiện tại
        String userId = AuditListenerUser.getCurrentUserId();
        String email = AuditListenerUser.getCurrentEmail();
        String username = AuditListenerUser.getCurrentUsername();
        
        // Sử dụng thông tin này cho business logic
        System.out.println("User ID: " + userId);
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);
    }
    
    public boolean canPerformAction() {
        // Kiểm tra user có đăng nhập không
        return AuditListenerUser.hasCurrentUser();
    }
}
```

### 2. Trong Controller (Không cần Authentication parameter)

```java
@RestController
public class YourController {
    
    @GetMapping("/user-info")
    public ResponseEntity<String> getUserInfo() {
        // Không cần Authentication parameter
        String userId = AuditListenerUser.getCurrentUserId();
        String email = AuditListenerUser.getCurrentEmail();
        String username = AuditListenerUser.getCurrentUsername();
        
        return ResponseEntity.ok("User ID: " + userId + ", Email: " + email);
    }
}
```

### 3. Tạo Audit Log

```java
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
```

## API Endpoints để test

### User Audit API
- `GET /api/user-audit/current-user` - Lấy thông tin user hiện tại
- `POST /api/user-audit/audit-log` - Tạo audit log
- `GET /api/user-audit/can-perform?action=test` - Kiểm tra quyền
- `GET /api/user-audit/user-id` - Lấy userId
- `GET /api/user-audit/email` - Lấy email
- `GET /api/user-audit/username` - Lấy username
- `GET /api/user-audit/login-status` - Kiểm tra trạng thái đăng nhập
- `POST /api/user-audit/perform-action` - Thực hiện action với audit log

### Product Audit API
- `POST /api/product-audit/create` - Tạo sản phẩm mới
- `PUT /api/product-audit/update` - Cập nhật sản phẩm
- `DELETE /api/product-audit/delete` - Xóa sản phẩm
- `GET /api/product-audit/get` - Lấy thông tin sản phẩm
- `GET /api/product-audit/all` - Lấy danh sách tất cả sản phẩm

## Ví dụ thực tế

### Tạo sản phẩm với audit log tự động:

```bash
# Đăng nhập trước (có JWT token)
curl -H "Authorization: Bearer YOUR_JWT_TOKEN" \
     -X POST "http://localhost:8080/api/product-audit/create" \
     -d "productName=Test Product&description=Test Description"
```

Kết quả sẽ tạo audit log:
```
=== PRODUCT AUDIT LOG ===
Action: CREATE_PRODUCT_START
Details: Creating product: Test Product
User ID: 123
Email: user@example.com
Username: user123
Timestamp: 1703123456789
=========================

=== PRODUCT AUDIT LOG ===
Action: CREATE_PRODUCT_SUCCESS
Details: Product created - ID: PROD_1703123456789, Name: Test Product, Created by: 123 (user@example.com)
User ID: 123
Email: user@example.com
Username: user123
Timestamp: 1703123456790
=========================
```

## Lưu ý quan trọng

1. **JWT Token bắt buộc**: Phải có JWT token hợp lệ trong header `Authorization`
2. **Thread-safe**: Sử dụng `ThreadLocal` nên an toàn cho multi-thread
3. **Auto cleanup**: Thông tin tự động được clear sau mỗi request
4. **Null safety**: Luôn kiểm tra null trước khi sử dụng
5. **Scope**: Chỉ hoạt động trong context của HTTP request có JWT token hợp lệ

## So sánh với Authentication

| Aspect | Authentication Parameter | AuditListenerUser |
|--------|-------------------------|-------------------|
| Cần inject parameter | ✅ Có | ❌ Không |
| Cần cast/check type | ✅ Có | ❌ Không |
| Truy cập toàn cục | ❌ Không | ✅ Có |
| Đơn giản sử dụng | ❌ Phức tạp | ✅ Đơn giản |
| Thread-safe | ✅ Có | ✅ Có |

## Troubleshooting

- **Null values**: Kiểm tra JWT token có hợp lệ không
- **No user info**: Đảm bảo request có header `Authorization: Bearer <token>`
- **Thread issues**: AuditListenerUser đã thread-safe, không cần lo lắng 