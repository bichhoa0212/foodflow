package project.foodflow.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import project.foodflow.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    
    User save(User user);
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByEmailOrPhone(String username);
    
    boolean existsByEmail(String email);


    boolean existsByProviderUserIdOrEmailOrPhone(String providerUserId, String email, String phone);
    
    User findByEmailForUserDetails(String email);
    
    Optional<User> findById(Long id);
    
    List<User> findAll();
    
    List<User> findByStatus(Integer status);
    
    List<User> findByRoleName(String roleName);
    
    List<User> findByGroupName(String groupName);
    
    List<User> findUsersWithFilters(String name, String email, String phone, Integer status);
    
    long countByStatus(Integer status);
    
    void deleteById(Long id);
    
    boolean existsByPhone(String phone);
    
    Optional<User> findByPhone(String phone);
    
    List<User> findByProvider(String provider);
    
    Optional<User> findByProviderAndProviderUserId(String provider, String providerUserId);
} 