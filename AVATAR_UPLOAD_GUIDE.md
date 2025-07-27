# Hướng dẫn sử dụng tính năng Upload Avatar

## Tổng quan

Tính năng upload avatar cho phép người dùng tải ảnh lên, lưu vào server và cập nhật URL vào database. Hệ thống sẽ tự động tạo tên file unique và trả về URL để hiển thị avatar.

## Các thành phần chính

### 1. **Backend Components**

#### User Entity (Đã cập nhật)
- Thêm trường `avatar` (String) để lưu URL avatar

#### FileUploadService
- `uploadFile()`: Upload file và trả về URL
- `uploadAvatar()`: Upload avatar với validation
- `deleteFile()`: Xóa file từ server
- `deleteFileFromUrl()`: Xóa file từ URL

#### FileController
- `POST /api/files/avatar`: Upload avatar
- `DELETE /api/files/avatar`: Xóa avatar
- `GET /api/files/{filename}`: Serve file

#### UserProfileController (Đã cập nhật)
- `POST /api/user/profile/avatar`: Upload avatar cho user hiện tại
- `DELETE /api/user/profile/avatar`: Xóa avatar của user hiện tại

#### UserProfileService (Đã cập nhật)
- `updateAvatar()`: Cập nhật avatar URL vào database
- `getCurrentAvatarUrl()`: Lấy avatar URL hiện tại

### 2. **Frontend Components**

#### AvatarUpload Component
- Upload ảnh với drag & drop
- Validation file type và size
- Hiển thị loading state
- Xử lý lỗi upload

#### Profile Component (Đã cập nhật)
- Tích hợp AvatarUpload component
- Hiển thị avatar hiện tại
- Cập nhật avatar real-time

## API Endpoints

### Upload Avatar
```http
POST /api/user/profile/avatar
Content-Type: multipart/form-data
Authorization: Bearer <token>

Body:
- file: [image file]
```

**Response:**
```json
{
  "code": 200,
  "status": "success",
  "message": "Upload avatar thành công",
  "data": "http://localhost:8080/api/files/abc123.jpg"
}
```

### Delete Avatar
```http
DELETE /api/user/profile/avatar
Authorization: Bearer <token>
```

**Response:**
```json
{
  "code": 200,
  "status": "success",
  "message": "Xóa avatar thành công",
  "data": null
}
```

### Get Profile (Đã cập nhật)
```http
GET /api/user/profile
Authorization: Bearer <token>
```

**Response:**
```json
{
  "code": 200,
  "status": "success",
  "message": "Lấy thông tin profile thành công",
  "data": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "avatar": "http://localhost:8080/api/files/abc123.jpg",
    // ... other fields
  }
}
```

## Cấu hình

### application.properties
```properties
# File upload configuration
app.upload.path=uploads/
app.base-url=http://localhost:8080

# Multipart file configuration
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

### Thư mục upload
- Tạo thư mục `uploads/` trong project root
- Files sẽ được lưu với tên unique (UUID + extension)
- URL format: `{base-url}/api/files/{filename}`

## Validation

### File Type
- Chỉ chấp nhận file hình ảnh (`image/*`)
- Hỗ trợ: JPG, PNG, GIF, WebP

### File Size
- Tối đa: 5MB cho avatar
- Có thể cấu hình trong `application.properties`

### Security
- Kiểm tra JWT token
- Validate file content type
- Sanitize filename

## Sử dụng trong Frontend

### Import AvatarUpload Component
```tsx
import AvatarUpload from './AvatarUpload';
```

### Sử dụng trong Profile
```tsx
<AvatarUpload
  currentAvatar={formData.avatar}
  onAvatarChange={(avatarUrl) => {
    setFormData(prev => ({ ...prev, avatar: avatarUrl }));
    setMessage({ type: 'success', text: 'Cập nhật avatar thành công!' });
  }}
  onAvatarDelete={() => {
    setFormData(prev => ({ ...prev, avatar: '' }));
    setMessage({ type: 'success', text: 'Xóa avatar thành công!' });
  }}
  disabled={!isEditing}
/>
```

## Tính năng

### ✅ **Đã hoàn thành:**
- Upload avatar với validation
- Xóa avatar và file
- Hiển thị avatar trong profile
- Real-time update
- Error handling
- Loading states
- File type validation
- File size validation

### 🔄 **Quy trình hoạt động:**
1. User chọn file ảnh
2. Frontend validate file type và size
3. Upload file lên server
4. Server tạo tên file unique
5. Lưu file vào thư mục uploads/
6. Cập nhật avatar URL vào database
7. Trả về URL cho frontend
8. Frontend cập nhật UI

### 🛡️ **Bảo mật:**
- JWT authentication required
- File type validation
- File size limit
- Unique filename generation
- Path traversal protection

## Troubleshooting

### Lỗi thường gặp:
1. **File too large**: Kiểm tra cấu hình `max-file-size`
2. **Invalid file type**: Chỉ chấp nhận image files
3. **Upload failed**: Kiểm tra thư mục `uploads/` có quyền write
4. **Avatar not showing**: Kiểm tra URL và file tồn tại

### Debug:
- Kiểm tra logs Spring Boot
- Kiểm tra network tab trong browser
- Kiểm tra file system permissions 