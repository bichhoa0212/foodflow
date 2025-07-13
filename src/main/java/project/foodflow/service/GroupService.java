package project.foodflow.service;

import project.foodflow.entity.Group;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface GroupService {
    
    Group save(Group group);
    
    Optional<Group> findById(Long id);
    
    Optional<Group> findByName(String name);
    
    List<Group> findAll();
    
    List<Group> findByStatus(Integer status);
    
    List<Group> findByRoleId(Long roleId);
    
    List<Group> findGroupsWithFilters(String name, Long roleId, Integer status);
    
    List<Group> findGroupsByUserId(Long userId);
    
    List<Group> findGroupsByRoleName(String roleName);
    
    long countByStatus(Integer status);
    
    long countByRoleId(Long roleId);
    
    List<Group> findByCreatedDateBetween(Timestamp startDate, Timestamp endDate);
    
    List<Group> findByNameIn(List<String> names);
    
    List<Group> findByNameContainingIgnoreCase(String name);
    
    boolean existsByName(String name);
    
    void deleteById(Long id);
    
    void deleteByName(String name);
    
    // New method for AuthService
    void addUserToGroup(Long userId, Long groupId);
} 