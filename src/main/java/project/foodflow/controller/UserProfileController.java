package project.foodflow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.foodflow.dto.UserProfileDto;
import project.foodflow.dto.UserAddressDto;
import project.foodflow.dto.UserFavoriteDto;
import project.foodflow.dto.PageData;
import project.foodflow.dto.Response;
import project.foodflow.service.UserProfileService;
import project.foodflow.service.FileUploadService;
import project.foodflow.constant.ReturnCode;
import project.foodflow.security.AuditListenerUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controller cho quản lý profile người dùng
 */
@RestController
@RequestMapping("/api/user/profile")
@RequiredArgsConstructor
public class UserProfileController {
    
    private final UserProfileService userProfileService;
    private final FileUploadService fileUploadService;
    
    /**
     * Lấy thông tin profile của user hiện tại
     */
    @GetMapping
    public ResponseEntity<Response<UserProfileDto>> getCurrentUserProfile() {
        Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());
        UserProfileDto profile = userProfileService.getUserProfile(userId);
        return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getStatus(),
                "Lấy thông tin profile thành công",
                profile
        ));
    }
    
    /**
     * Cập nhật thông tin profile của user hiện tại
     */
    @PutMapping
    public ResponseEntity<Response<UserProfileDto>> updateCurrentUserProfile(
            @RequestBody UserProfileDto profileDto) {
        Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());
        UserProfileDto updatedProfile = userProfileService.updateUserProfile(userId, profileDto);
        return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getStatus(),
                "Cập nhật thông tin profile thành công",
                updatedProfile
        ));
    }
    
    /**
     * Lấy danh sách địa chỉ của user hiện tại
     */
    @GetMapping("/addresses")
    public ResponseEntity<Response<List<UserAddressDto>>> getCurrentUserAddresses() {
        Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());
        List<UserAddressDto> addresses = userProfileService.getUserAddresses(userId);
        return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getStatus(),
                "Lấy danh sách địa chỉ thành công",
                addresses
        ));
    }
    
    /**
     * Thêm địa chỉ mới cho user hiện tại
     */
    @PostMapping("/addresses")
    public ResponseEntity<Response<UserAddressDto>> addCurrentUserAddress(
            @RequestBody UserAddressDto addressDto) {
        Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());
        UserAddressDto newAddress = userProfileService.addUserAddress(userId, addressDto);
        return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getStatus(),
                "Thêm địa chỉ thành công",
                newAddress
        ));
    }
    
    /**
     * Cập nhật địa chỉ của user hiện tại
     */
    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<Response<UserAddressDto>> updateCurrentUserAddress(
            @PathVariable Long addressId,
            @RequestBody UserAddressDto addressDto) {
        Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());
        UserAddressDto updatedAddress = userProfileService.updateUserAddress(userId, addressId, addressDto);
        return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getStatus(),
                "Cập nhật địa chỉ thành công",
                updatedAddress
        ));
    }
    
    /**
     * Xóa địa chỉ của user hiện tại
     */
    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<Response<Void>> deleteCurrentUserAddress(
            @PathVariable Long addressId) {
        Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());
        userProfileService.deleteUserAddress(userId, addressId);
        return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getStatus(),
                "Xóa địa chỉ thành công",
                null
        ));
    }
    
    /**
     * Đặt địa chỉ làm mặc định cho user hiện tại
     */
    @PutMapping("/addresses/{addressId}/default")
    public ResponseEntity<Response<Void>> setDefaultAddress(
            @PathVariable Long addressId) {
        Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());
        userProfileService.setDefaultAddress(userId, addressId);
        return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getStatus(),
                "Đặt địa chỉ mặc định thành công",
                null
        ));
    }
    
    /**
     * Lấy danh sách sản phẩm yêu thích của user hiện tại
     */
    @GetMapping("/favorites")
    public ResponseEntity<Response<PageData<UserFavoriteDto>>> getCurrentUserFavorites(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());
        PageData<UserFavoriteDto> favorites = userProfileService.getUserFavorites(userId, page, size);
        return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getStatus(),
                "Lấy danh sách sản phẩm yêu thích thành công",
                favorites
        ));
    }
    
    /**
     * Thêm sản phẩm vào yêu thích cho user hiện tại
     */
    @PostMapping("/favorites/{productId}")
    public ResponseEntity<Response<Void>> addToFavorites(
            @PathVariable Long productId) {
        Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());
        userProfileService.addToFavorites(userId, productId);
        return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getStatus(),
                "Thêm vào yêu thích thành công",
                null
        ));
    }
    
    /**
     * Xóa sản phẩm khỏi yêu thích của user hiện tại
     */
    @DeleteMapping("/favorites/{productId}")
    public ResponseEntity<Response<Void>> removeFromFavorites(
            @PathVariable Long productId) {
        Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());
        userProfileService.removeFromFavorites(userId, productId);
        return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getStatus(),
                "Xóa khỏi yêu thích thành công",
                null
        ));
    }
    
    /**
     * Kiểm tra xem sản phẩm có trong yêu thích không
     */
    @GetMapping("/favorites/{productId}/check")
    public ResponseEntity<Response<Boolean>> checkFavorite(
            @PathVariable Long productId) {
        Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());
        boolean isFavorited = userProfileService.isProductFavorited(userId, productId);
        return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getStatus(),
                "Kiểm tra yêu thích thành công",
                isFavorited
        ));
    }
    
    /**
     * Upload avatar cho user hiện tại
     */
    @PostMapping("/avatar")
    public ResponseEntity<Response<String>> uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            Long userId = Long.parseLong(AuditListenerUser.getCurrentUserId());
            
            // Upload file và lấy URL
            String avatarUrl = fileUploadService.uploadAvatar(file);
            
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
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new Response<>(
                    ReturnCode.ERROR.getCode(),
                    ReturnCode.ERROR.getStatus(),
                    "Lỗi khi upload avatar: " + e.getMessage(),
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