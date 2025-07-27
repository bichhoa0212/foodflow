package project.foodflow.controller;

import lombok.RequiredArgsConstructor;
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
        try {
            Path filePath = Paths.get(uploadPath).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .contentType(MediaType.IMAGE_JPEG) // Có thể cải thiện để detect type
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
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
} 