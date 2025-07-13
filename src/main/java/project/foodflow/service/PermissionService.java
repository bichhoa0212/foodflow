package project.foodflow.service;

import project.foodflow.entity.Permission;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface PermissionService {
    
    Permission save(Permission permission);
    
    Optional<Permission> findById(Long id);
    
    Optional<Permission> findByName(String name);
    
    List<Permission> findAll();
    
    List<Permission> findByStatus(Integer status);
    
    List<Permission> findByDescriptionContainingIgnoreCase(String description);
    
    List<Permission> findPermissionsWithFilters(String name, String description, Integer status);
    
    List<Permission> findPermissionsByRoleId(Long roleId);
    
    List<Permission> findPermissionsByUserId(Long userId);
    
    List<Permission> findByNameIn(List<String> names);
    
    long countByStatus(Integer status);
    
    List<Permission> findByCreatedDateBetween(Timestamp startDate, Timestamp endDate);
    
    List<Permission> findByNameContainingIgnoreCase(String namePattern);
    
    List<Permission> findByCategory(String category);
    
    boolean existsByName(String name);
    
    void deleteById(Long id);
    
    void deleteByName(String name);
} 