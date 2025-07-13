package project.foodflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.foodflow.entity.Permission;
import project.foodflow.repository.PermissionRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission save(Permission permission) {
        if (permission.getCreatedDate() == null) {
            permission.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        }
        permission.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        return permissionRepository.save(permission);
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Optional<Permission> findByName(String name) {
        return permissionRepository.findByName(name);
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public List<Permission> findByStatus(Integer status) {
        return permissionRepository.findByStatus(status);
    }

    @Override
    public List<Permission> findByDescriptionContainingIgnoreCase(String description) {
        return permissionRepository.findByDescriptionContainingIgnoreCase(description);
    }

    @Override
    public List<Permission> findPermissionsWithFilters(String name, String description, Integer status) {
        return permissionRepository.findPermissionsWithFilters(name, description, status);
    }

    @Override
    public List<Permission> findPermissionsByRoleId(Long roleId) {
        return permissionRepository.findPermissionsByRoleId(roleId);
    }

    @Override
    public List<Permission> findPermissionsByUserId(Long userId) {
        return permissionRepository.findPermissionsByUserId(userId);
    }

    @Override
    public List<Permission> findByNameIn(List<String> names) {
        return permissionRepository.findByNameIn(names);
    }

    @Override
    public long countByStatus(Integer status) {
        return permissionRepository.countByStatus(status);
    }

    @Override
    public List<Permission> findByCreatedDateBetween(Timestamp startDate, Timestamp endDate) {
        return permissionRepository.findByCreatedDateBetween(startDate, endDate);
    }

    @Override
    public List<Permission> findByNameContainingIgnoreCase(String namePattern) {
        return permissionRepository.findByNameContainingIgnoreCase(namePattern);
    }

    @Override
    public List<Permission> findByCategory(String category) {
        return permissionRepository.findByCategory(category);
    }

    @Override
    public boolean existsByName(String name) {
        return permissionRepository.existsByName(name);
    }

    @Override
    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        permissionRepository.findByName(name).ifPresent(permission -> permissionRepository.delete(permission));
    }
} 