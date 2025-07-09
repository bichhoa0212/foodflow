# FoodFlow API Test Guide

## Cấu hình Swagger đã hoàn thành!

### 1. Khởi chạy ứng dụng

```bash
mvn spring-boot:run
```

### 2. Truy cập Swagger UI

Sau khi khởi chạy ứng dụng, truy cập Swagger UI tại:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

### 3. Các API Endpoints có sẵn

#### Test API (Để kiểm tra cơ bản)
- `GET /api/test/health` - Kiểm tra trạng thái API
- `GET /api/test/echo/{message}` - Echo message
- `POST /api/test/data` - Test POST với JSON data
- `GET /api/test/error-test` - Test error response

#### User Management API
- `GET /api/users` - Lấy danh sách tất cả users
- `GET /api/users/{id}` - Lấy user theo ID
- `POST /api/users` - Tạo user mới
- `PUT /api/users/{id}` - Cập nhật user
- `DELETE /api/users/{id}` - Xóa user

#### Restaurant Management API
- `GET /api/restaurants` - Lấy danh sách restaurants (có filter)
- `GET /api/restaurants/{id}` - Lấy restaurant theo ID
- `POST /api/restaurants` - Tạo restaurant mới
- `PUT /api/restaurants/{id}` - Cập nhật restaurant
- `DELETE /api/restaurants/{id}` - Xóa restaurant
- `GET /api/restaurants/search?query={term}` - Tìm kiếm restaurants

### 4. Cách test API

#### Sử dụng Swagger UI
1. Mở http://localhost:8080/swagger-ui.html
2. Chọn API endpoint muốn test
3. Click "Try it out"
4. Nhập parameters (nếu có)
5. Click "Execute"

#### Sử dụng cURL

**Test Health Check:**
```bash
curl -X GET "http://localhost:8080/api/test/health"
```

**Lấy danh sách users:**
```bash
curl -X GET "http://localhost:8080/api/users"
```

**Tạo user mới:**
```bash
curl -X POST "http://localhost:8080/api/users" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test User",
    "email": "test@example.com",
    "phone": "+1234567890",
    "role": "CUSTOMER"
  }'
```

**Lấy danh sách restaurants:**
```bash
curl -X GET "http://localhost:8080/api/restaurants"
```

**Tìm kiếm restaurants:**
```bash
curl -X GET "http://localhost:8080/api/restaurants/search?query=pizza"
```

**Filter restaurants theo cuisine:**
```bash
curl -X GET "http://localhost:8080/api/restaurants?cuisine=Italian"
```

**Filter restaurants theo rating:**
```bash
curl -X GET "http://localhost:8080/api/restaurants?minRating=4.5"
```

### 5. Dữ liệu mẫu

Ứng dụng đã được khởi tạo với một số dữ liệu mẫu:

**Users:**
- John Doe (john.doe@example.com) - CUSTOMER
- Jane Smith (jane.smith@example.com) - ADMIN

**Restaurants:**
- Pizza Palace (Italian, rating: 4.5)
- Sushi Master (Japanese, rating: 4.8)
- Burger House (American, rating: 4.2)

### 6. Lưu ý quan trọng

⚠️ **Spring Security đã được tắt tạm thời** để dễ dàng test API. Trong môi trường production, bạn cần:
1. Xóa dòng `spring.autoconfigure.exclude` trong `application.properties`
2. Cấu hình Spring Security phù hợp
3. Thêm authentication và authorization

### 7. Troubleshooting

**Nếu gặp lỗi:**
1. Kiểm tra ứng dụng đã chạy chưa: `http://localhost:8080/api/test/health`
2. Kiểm tra logs trong console
3. Đảm bảo port 8080 không bị sử dụng bởi ứng dụng khác

**Nếu Swagger UI không load:**
1. Kiểm tra dependency `springdoc-openapi-starter-webmvc-ui` trong `pom.xml`
2. Restart ứng dụng
3. Clear browser cache 