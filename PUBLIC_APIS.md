# Danh sách API Public - Không cần Authentication

## 1. Authentication APIs
```
/api/auth/**
```
- Đăng ký, đăng nhập, refresh token
- Không cần JWT token

## 2. Public APIs - Dữ liệu công khai
```
/api/public/**
```

### 2.1 Categories (Loại sản phẩm)
- `GET /api/public/categories/list` - Lấy danh sách loại sản phẩm cho navigation (ID, name)

### 2.2 Products (Sản phẩm)
- `GET /api/public/products` - Lấy danh sách sản phẩm với filter
- Parameters: page, size, categoryId, minPrice, maxPrice, sort, name

### 2.3 Restaurants (Nhà hàng)
- `GET /api/public/restaurants` - Lấy danh sách nhà hàng
- `GET /api/public/restaurants/{id}` - Chi tiết nhà hàng

### 2.4 Common Data
- `GET /api/public/sliders` - Banner/Slider trang chủ
- `GET /api/public/promotions` - Khuyến mãi đang diễn ra

## 3. Health Check & Monitoring
```
/actuator/**
/health/**
```
- Kiểm tra trạng thái hệ thống
- Metrics, health check

## 4. Documentation APIs
```
/swagger-ui/**
/v3/api-docs/**
/api-docs/**
/swagger-resources/**
```
- Swagger UI
- API documentation

## 5. Test APIs
```
/test/**
```
- APIs để test hệ thống

## 6. Static Resources
```
/images/**
/uploads/**
/favicon.ico
```
- Hình ảnh, file upload
- Favicon

## 7. CORS Preflight
```
OPTIONS /**
```
- Bắt buộc cho CORS preflight requests

---

## Lưu ý:
- Tất cả API public đều có thể truy cập mà không cần JWT token
- Dữ liệu trả về chỉ là thông tin công khai, không chứa thông tin nhạy cảm
- Các API khác ngoài danh sách trên đều yêu cầu authentication 