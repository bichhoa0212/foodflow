package project.foodflow.service;

import project.foodflow.dto.UserProfileDto;
import project.foodflow.dto.UserAddressDto;
import project.foodflow.dto.UserFavoriteDto;
import project.foodflow.dto.PageData;

import java.util.List;

/**
 * Service interface cho quản lý profile người dùng
 */
public interface UserProfileService {
    
    /**
     * Lấy thông tin profile của user
     */
    UserProfileDto getUserProfile(Long userId);
    
    /**
     * Cập nhật thông tin profile của user
     */
    UserProfileDto updateUserProfile(Long userId, UserProfileDto profileDto);
    
    /**
     * Lấy danh sách địa chỉ của user
     */
    List<UserAddressDto> getUserAddresses(Long userId);
    
    /**
     * Thêm địa chỉ mới cho user
     */
    UserAddressDto addUserAddress(Long userId, UserAddressDto addressDto);
    
    /**
     * Cập nhật địa chỉ của user
     */
    UserAddressDto updateUserAddress(Long userId, Long addressId, UserAddressDto addressDto);
    
    /**
     * Xóa địa chỉ của user
     */
    void deleteUserAddress(Long userId, Long addressId);
    
    /**
     * Đặt địa chỉ làm mặc định
     */
    void setDefaultAddress(Long userId, Long addressId);
    
    /**
     * Lấy danh sách sản phẩm yêu thích của user
     */
    PageData<UserFavoriteDto> getUserFavorites(Long userId, int page, int size);
    
    /**
     * Thêm sản phẩm vào yêu thích
     */
    void addToFavorites(Long userId, Long productId);
    
    /**
     * Xóa sản phẩm khỏi yêu thích
     */
    void removeFromFavorites(Long userId, Long productId);
    
    /**
     * Kiểm tra xem sản phẩm có trong yêu thích không
     */
    boolean isProductFavorited(Long userId, Long productId);
    
    /**
     * Cập nhật avatar URL cho user
     */
    void updateAvatar(Long userId, String avatarUrl);
    
    /**
     * Lấy avatar URL hiện tại của user
     */
    String getCurrentAvatarUrl(Long userId);
} 