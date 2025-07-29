package project.foodflow.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.foodflow.dto.Response;
import project.foodflow.constant.ReturnCode;
import project.foodflow.service.FileUploadService;
import project.foodflow.service.UserProfileService;
import project.foodflow.security.AuditListenerUser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileUploadService fileUploadService;
    private final UserProfileService userProfileService;

    @Value("${app.upload.path:uploads/}")
    private String uploadPath;

    /**
     * Serve file từ thư mục upload
     */
    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        log.info("Serving file: {}", filename);
        
        try {
            Path filePath = Paths.get(uploadPath).resolve(filename).normalize();
            log.info("File path: {}", filePath.toString());
            
            Resource resource = new UrlResource(filePath.toUri());
            log.info("Resource exists: {}, readable: {}", resource.exists(), resource.isReadable());

            if (resource.exists() && resource.isReadable()) {
                // Detect content type based on file extension
                MediaType mediaType = getMediaType(filename);
                log.info("Detected media type: {}", mediaType);
                
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .contentType(mediaType)
                        .body(resource);
            } else {
                log.warn("File not found or not readable: {}", filePath);
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            log.error("Malformed URL for file: {}", filename, e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Error serving file: {}", filename, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Detect MediaType based on file extension
     */
    private MediaType getMediaType(String filename) {
        String extension = "";
        if (filename.contains(".")) {
            extension = filename.substring(filename.lastIndexOf(".")).toLowerCase();
        }
        
        return switch (extension) {
            case ".jpg", ".jpeg" -> MediaType.IMAGE_JPEG;
            case ".png" -> MediaType.IMAGE_PNG;
            case ".gif" -> MediaType.IMAGE_GIF;
            case ".webp" -> MediaType.valueOf("image/webp");
            case ".svg" -> MediaType.valueOf("image/svg+xml");
            case ".pdf" -> MediaType.APPLICATION_PDF;
            case ".txt" -> MediaType.TEXT_PLAIN;
            case ".html", ".htm" -> MediaType.TEXT_HTML;
            case ".css" -> MediaType.valueOf("text/css");
            case ".js" -> MediaType.valueOf("application/javascript");
            case ".json" -> MediaType.APPLICATION_JSON;
            case ".xml" -> MediaType.APPLICATION_XML;
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
    }

    /**
     * Upload avatar cho user hiện tại
     */
    @PostMapping("/avatar")
    public ResponseEntity<Response<String>> uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            // Upload file và lấy URL
            String avatarUrl = fileUploadService.uploadAvatar(file);

            // Lấy userId hiện tại
            Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());

            // Cập nhật avatar URL vào database
            userProfileService.updateAvatar(userId, avatarUrl);

            return ResponseEntity.ok(new Response<>(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getStatus(),
                    "Upload avatar thành công",
                    avatarUrl
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Response<>(
                    ReturnCode.BAD_REQUEST.getCode(),
                    ReturnCode.BAD_REQUEST.getStatus(),
                    e.getMessage(),
                    null
            ));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(new Response<>(
                    ReturnCode.ERROR.getCode(),
                    ReturnCode.ERROR.getStatus(),
                    "Lỗi khi upload file: " + e.getMessage(),
                    null
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new Response<>(
                    ReturnCode.ERROR.getCode(),
                    ReturnCode.ERROR.getStatus(),
                    "Lỗi không xác định: " + e.getMessage(),
                    null
            ));
        }
    }

    /**
     * Xóa avatar hiện tại
     */
    @DeleteMapping("/avatar")
    public ResponseEntity<Response<Void>> deleteAvatar() {
        try {
            Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());
            
            // Lấy avatar URL hiện tại
            String currentAvatarUrl = userProfileService.getCurrentAvatarUrl(userId);
            
            // Xóa file cũ nếu có
            if (currentAvatarUrl != null) {
                fileUploadService.deleteFileFromUrl(currentAvatarUrl);
            }
            
            // Cập nhật avatar thành null trong database
            userProfileService.updateAvatar(userId, null);

            return ResponseEntity.ok(new Response<>(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getStatus(),
                    "Xóa avatar thành công",
                    null
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new Response<>(
                    ReturnCode.ERROR.getCode(),
                    ReturnCode.ERROR.getStatus(),
                    "Lỗi khi xóa avatar: " + e.getMessage(),
                    null
            ));
        }
    }

    /**
     * Test endpoint để kiểm tra danh sách file trong thư mục uploads
     */
    @GetMapping("/test/list")
    public ResponseEntity<Response<String>> listFiles() {
        try {
            Path uploadDir = Paths.get(uploadPath);
            if (!java.nio.file.Files.exists(uploadDir)) {
                return ResponseEntity.ok(new Response<>(
                        ReturnCode.SUCCESS.getCode(),
                        ReturnCode.SUCCESS.getStatus(),
                        "Thư mục uploads không tồn tại",
                        "Upload directory: " + uploadDir.toAbsolutePath()
                ));
            }
            
            StringBuilder fileList = new StringBuilder();
            fileList.append("Upload directory: ").append(uploadDir.toAbsolutePath()).append("\n");
            fileList.append("Files:\n");
            
            java.nio.file.Files.list(uploadDir)
                    .filter(path -> java.nio.file.Files.isRegularFile(path))
                    .forEach(path -> {
                        try {
                            long size = java.nio.file.Files.size(path);
                            fileList.append("- ").append(path.getFileName())
                                    .append(" (").append(size).append(" bytes)\n");
                        } catch (IOException e) {
                            fileList.append("- ").append(path.getFileName()).append(" (error reading size)\n");
                        }
                    });
            
            return ResponseEntity.ok(new Response<>(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getStatus(),
                    "Danh sách file trong thư mục uploads",
                    fileList.toString()
            ));
        } catch (Exception e) {
            log.error("Error listing files", e);
            return ResponseEntity.internalServerError().body(new Response<>(
                    ReturnCode.ERROR.getCode(),
                    ReturnCode.ERROR.getStatus(),
                    "Lỗi khi liệt kê file: " + e.getMessage(),
                    null
            ));
        }
    }

    /**
     * Test endpoint public để kiểm tra file có tồn tại không
     */
    @GetMapping("/test/check/{filename:.+}")
    public ResponseEntity<Response<String>> checkFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadPath).resolve(filename).normalize();
            log.info("Checking file: {}", filePath.toString());
            
            if (java.nio.file.Files.exists(filePath)) {
                long size = java.nio.file.Files.size(filePath);
                String fileUrl = "http://localhost:8080/api/files/" + filename;
                return ResponseEntity.ok(new Response<>(
                        ReturnCode.SUCCESS.getCode(),
                        ReturnCode.SUCCESS.getStatus(),
                        "File tồn tại",
                        "File: " + filename + ", Size: " + size + " bytes, URL: " + fileUrl
                ));
            } else {
                return ResponseEntity.ok(new Response<>(
                        ReturnCode.SUCCESS.getCode(),
                        ReturnCode.SUCCESS.getStatus(),
                        "File không tồn tại",
                        "File path: " + filePath.toString()
                ));
            }
        } catch (Exception e) {
            log.error("Error checking file: {}", filename, e);
            return ResponseEntity.internalServerError().body(new Response<>(
                    ReturnCode.ERROR.getCode(),
                    ReturnCode.ERROR.getStatus(),
                    "Lỗi khi kiểm tra file: " + e.getMessage(),
                    null
            ));
        }
    }

    /**
     * Test endpoint để serve file
     */
    @GetMapping("/test/serve/{filename:.+}")
    public ResponseEntity<Resource> serveFileTest(@PathVariable String filename) {
        log.info("Test serving file: {}", filename);
        
        try {
            Path filePath = Paths.get(uploadPath).resolve(filename).normalize();
            log.info("File path: {}", filePath.toString());
            
            Resource resource = new UrlResource(filePath.toUri());
            log.info("Resource exists: {}, readable: {}", resource.exists(), resource.isReadable());

            if (resource.exists() && resource.isReadable()) {
                // Detect content type based on file extension
                MediaType mediaType = getMediaType(filename);
                log.info("Detected media type: {}", mediaType);
                
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .contentType(mediaType)
                        .body(resource);
            } else {
                log.warn("File not found or not readable: {}", filePath);
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            log.error("Malformed URL for file: {}", filename, e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Error serving file: {}", filename, e);
            return ResponseEntity.internalServerError().build();
        }
    }
} 