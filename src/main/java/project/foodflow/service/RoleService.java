package project.foodflow.service;

import project.foodflow.entity.Role;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    
    Role save(Role role);
    
    Optional<Role> findById(Long id);
    
    Optional<Role> findByName(String name);
    
    List<Role> findAll();
    
    List<Role> findByStatus(Integer status);
    
    List<Role> findByDescriptionContainingIgnoreCase(String description);
    
    List<Role> findRolesWithFilters(String name, String description, Integer status);
    
    List<Role> findRolesByUserId(Long userId);
    
    List<Role> findRolesByPermissionId(Long permissionId);
    
    long countByStatus(Integer status);
    
    List<Role> findByCreatedDateBetween(Timestamp startDate, Timestamp endDate);
    
    List<Role> findByNameIn(List<String> names);
    
    boolean existsByName(String name);
    
    void deleteById(Long id);
    
    void deleteByName(String name);
} 