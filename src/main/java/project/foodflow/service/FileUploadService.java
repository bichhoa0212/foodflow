package project.foodflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    @Value("${app.upload.path:uploads/}")
    private String uploadPath;

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    /**
     * Upload file và trả về URL
     */
    public String uploadFile(MultipartFile file) throws IOException {
        // Tạo thư mục upload nếu chưa tồn tại
        Path uploadDir = Paths.get(uploadPath);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // Tạo tên file unique
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String filename = UUID.randomUUID().toString() + fileExtension;

        // Lưu file
        Path filePath = uploadDir.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Trả về URL
        return baseUrl + "/api/files/" + filename;
    }

    /**
     * Upload avatar và trả về URL
     */
    public String uploadAvatar(MultipartFile file) throws IOException {
        // Kiểm tra file type
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("File phải là hình ảnh");
        }

        // Kiểm tra kích thước file (max 5MB)
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new IllegalArgumentException("File không được lớn hơn 5MB");
        }

        return uploadFile(file);
    }

    /**
     * Xóa file
     */
    public void deleteFile(String filename) throws IOException {
        Path filePath = Paths.get(uploadPath, filename);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
    }

    /**
     * Xóa file từ URL
     */
    public void deleteFileFromUrl(String fileUrl) throws IOException {
        if (fileUrl != null && fileUrl.startsWith(baseUrl + "/api/files/")) {
            String filename = fileUrl.substring((baseUrl + "/api/files/").length());
            deleteFile(filename);
        }
    }
} 