package project.foodflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.foodflow.dto.UserProfileDto;
import project.foodflow.dto.UserAddressDto;
import project.foodflow.dto.UserFavoriteDto;
import project.foodflow.dto.PageData;
import project.foodflow.entity.User;
import project.foodflow.entity.UserAddress;
import project.foodflow.entity.UserFavorite;
import project.foodflow.entity.Product;
import project.foodflow.repository.UserRepository;
import project.foodflow.repository.UserAddressRepository;
import project.foodflow.repository.UserFavoriteRepository;
import project.foodflow.repository.ProductRepository;
import project.foodflow.exception.CustomExceptions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation cho quản lý profile người dùng
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserProfileServiceImpl implements UserProfileService {
    
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    private final UserFavoriteRepository userFavoriteRepository;
    private final ProductRepository productRepository;
    
    @Override
    @Transactional(readOnly = true)
    public UserProfileDto getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptions.UserNotFoundException("User not found with id: " + userId));
        
        return mapToUserProfileDto(user);
    }
    
    @Override
    public UserProfileDto updateUserProfile(Long userId, UserProfileDto profileDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptions.UserNotFoundException("User not found with id: " + userId));
        
        // Cập nhật thông tin cơ bản
        if (profileDto.getName() != null) {
            user.setName(profileDto.getName());
        }
        if (profileDto.getPhone() != null) {
            user.setPhone(profileDto.getPhone());
        }
        if (profileDto.getAddress() != null) {
            user.setAddress(profileDto.getAddress());
        }
        if (profileDto.getSex() != null) {
            user.setSex(profileDto.getSex());
        }
        if (profileDto.getDateOfBirth() != null) {
            user.setDateOfBirth(profileDto.getDateOfBirth());
        }
        
        User savedUser = userRepository.save(user);
        return mapToUserProfileDto(savedUser);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<UserAddressDto> getUserAddresses(Long userId) {
        List<UserAddress> addresses = userAddressRepository.findByUserIdAndStatusOrderByIsDefaultDescCreatedDateDesc(userId, 1);
        return addresses.stream()
                .map(this::mapToUserAddressDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public UserAddressDto addUserAddress(Long userId, UserAddressDto addressDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptions.UserNotFoundException("User not found with id: " + userId));
        
        // Kiểm tra giới hạn số địa chỉ (tối đa 10 địa chỉ)
        long addressCount = userAddressRepository.countByUserIdAndStatus(userId, 1);
        if (addressCount >= 10) {
            throw new CustomExceptions.AddressLimitExceededException("Maximum 10 addresses allowed per user");
        }
        
        UserAddress address = new UserAddress();
        address.setUser(user);
        address.setName(addressDto.getName());
        address.setPhone(addressDto.getPhone());
        address.setAddress(addressDto.getAddress());
        address.setProvince(addressDto.getProvince());
        address.setDistrict(addressDto.getDistrict());
        address.setWard(addressDto.getWard());
        address.setIsDefault(addressDto.getIsDefault() != null ? addressDto.getIsDefault() : false);
        address.setStatus(1);
        
        // Nếu đây là địa chỉ mặc định, xóa mặc định của các địa chỉ khác
        if (address.getIsDefault()) {
            userAddressRepository.clearDefaultAddress(userId, 1);
        }
        
        UserAddress savedAddress = userAddressRepository.save(address);
        return mapToUserAddressDto(savedAddress);
    }
    
    @Override
    public UserAddressDto updateUserAddress(Long userId, Long addressId, UserAddressDto addressDto) {
        UserAddress address = userAddressRepository.findById(addressId)
                .orElseThrow(() -> new CustomExceptions.AddressNotFoundException("Address not found with id: " + addressId));
        
        // Kiểm tra xem địa chỉ có thuộc về user không
        if (!address.getUser().getId().equals(userId)) {
            throw new CustomExceptions.AddressAccessDeniedException("Access denied to this address");
        }
        
        if (addressDto.getName() != null) {
            address.setName(addressDto.getName());
        }
        if (addressDto.getPhone() != null) {
            address.setPhone(addressDto.getPhone());
        }
        if (addressDto.getAddress() != null) {
            address.setAddress(addressDto.getAddress());
        }
        if (addressDto.getProvince() != null) {
            address.setProvince(addressDto.getProvince());
        }
        if (addressDto.getDistrict() != null) {
            address.setDistrict(addressDto.getDistrict());
        }
        if (addressDto.getWard() != null) {
            address.setWard(addressDto.getWard());
        }
        
        UserAddress savedAddress = userAddressRepository.save(address);
        return mapToUserAddressDto(savedAddress);
    }
    
    @Override
    public void deleteUserAddress(Long userId, Long addressId) {
        UserAddress address = userAddressRepository.findById(addressId)
                .orElseThrow(() -> new CustomExceptions.AddressNotFoundException("Address not found with id: " + addressId));
        
        // Kiểm tra xem địa chỉ có thuộc về user không
        if (!address.getUser().getId().equals(userId)) {
            throw new CustomExceptions.AddressAccessDeniedException("Access denied to this address");
        }
        
        address.setStatus(0);
        userAddressRepository.save(address);
    }
    
    @Override
    public void setDefaultAddress(Long userId, Long addressId) {
        UserAddress address = userAddressRepository.findById(addressId)
                .orElseThrow(() -> new CustomExceptions.AddressNotFoundException("Address not found with id: " + addressId));
        
        // Kiểm tra xem địa chỉ có thuộc về user không
        if (!address.getUser().getId().equals(userId)) {
            throw new CustomExceptions.AddressAccessDeniedException("Access denied to this address");
        }
        
        // Xóa mặc định của tất cả địa chỉ khác
        userAddressRepository.clearDefaultAddress(userId, 1);
        
        // Đặt địa chỉ này làm mặc định
        userAddressRepository.setDefaultAddress(addressId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public PageData<UserFavoriteDto> getUserFavorites(Long userId, int page, int size) {
        Page<UserFavorite> favoritesPage = userFavoriteRepository.findByUserIdAndStatusOrderByCreatedDateDesc(
                userId, 1, PageRequest.of(page, size));
        
        List<UserFavoriteDto> favorites = favoritesPage.getContent().stream()
                .map(this::mapToUserFavoriteDto)
                .collect(Collectors.toList());
        
        return new PageData<>(
                favorites,
                favoritesPage.getTotalElements(),
                favoritesPage.getTotalPages(),
                favoritesPage.getNumber(),
                favoritesPage.getSize()
        );
    }
    
    @Override
    public void addToFavorites(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptions.UserNotFoundException("User not found with id: " + userId));
        
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomExceptions.ProductNotFoundException("Product not found with id: " + productId));
        
        // Kiểm tra xem đã yêu thích chưa
        if (userFavoriteRepository.findByUserIdAndProductIdAndStatus(userId, productId, 1).isPresent()) {
            throw new CustomExceptions.FavoriteAlreadyExistsException("Product already in favorites");
        }
        
        // Kiểm tra giới hạn số sản phẩm yêu thích (tối đa 100 sản phẩm)
        long favoriteCount = userFavoriteRepository.countByUserIdAndStatus(userId, 1);
        if (favoriteCount >= 100) {
            throw new CustomExceptions.FavoriteLimitExceededException("Maximum 100 favorites allowed per user");
        }
        
        UserFavorite favorite = new UserFavorite();
        favorite.setUser(user);
        favorite.setProduct(product);
        favorite.setStatus(1);
        
        userFavoriteRepository.save(favorite);
    }
    
    @Override
    public void removeFromFavorites(Long userId, Long productId) {
        UserFavorite favorite = userFavoriteRepository.findByUserIdAndProductIdAndStatus(userId, productId, 1)
                .orElseThrow(() -> new CustomExceptions.FavoriteNotFoundException("Favorite not found"));
        
        favorite.setStatus(0);
        userFavoriteRepository.save(favorite);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isProductFavorited(Long userId, Long productId) {
        return userFavoriteRepository.findByUserIdAndProductIdAndStatus(userId, productId, 1).isPresent();
    }
    
    // Helper methods để map entity sang DTO
    private UserProfileDto mapToUserProfileDto(User user) {
        UserProfileDto dto = new UserProfileDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setSex(user.getSex());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setAvatar(user.getAvatar());
        dto.setProvider(user.getProvider());
        dto.setStatus(user.getStatus());
        dto.setCreatedDate(user.getCreatedDate());
        dto.setModifiedDate(user.getModifiedDate());
        
        // TODO: Lấy roles và permissions từ UserRole và RolePermission
        // dto.setRoles(...);
        // dto.setPermissions(...);
        
        return dto;
    }
    
    private UserAddressDto mapToUserAddressDto(UserAddress address) {
        UserAddressDto dto = new UserAddressDto();
        dto.setId(address.getId());
        dto.setName(address.getName());
        dto.setPhone(address.getPhone());
        dto.setAddress(address.getAddress());
        dto.setProvince(address.getProvince());
        dto.setDistrict(address.getDistrict());
        dto.setWard(address.getWard());
        dto.setIsDefault(address.getIsDefault());
        dto.setStatus(address.getStatus());
        dto.setCreatedDate(address.getCreatedDate());
        dto.setModifiedDate(address.getModifiedDate());
        return dto;
    }
    
    private UserFavoriteDto mapToUserFavoriteDto(UserFavorite favorite) {
        Product product = favorite.getProduct();
        UserFavoriteDto dto = new UserFavoriteDto();
        dto.setId(favorite.getId());
        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setProductDescription(product.getDescription());
        dto.setProductImageUrl(product.getImageUrl());
        dto.setProductPrice(product.getPrice());
        dto.setDiscountType(product.getDiscountType());
        dto.setDiscountValue(product.getDiscountValue());
        dto.setProductStatus(product.getStatus());
        dto.setCreatedDate(favorite.getCreatedDate());
        return dto;
    }
    
    @Override
    public void updateAvatar(Long userId, String avatarUrl) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptions.UserNotFoundException("User not found with id: " + userId));
        
        user.setAvatar(avatarUrl);
        userRepository.save(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public String getCurrentAvatarUrl(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptions.UserNotFoundException("User not found with id: " + userId));
        
        return user.getAvatar();
    }
} 