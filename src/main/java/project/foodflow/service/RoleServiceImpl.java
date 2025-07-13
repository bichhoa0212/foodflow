package project.foodflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.foodflow.entity.Role;
import project.foodflow.repository.RoleRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        if (role.getCreatedDate() == null) {
            role.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        }
        role.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findByStatus(Integer status) {
        return roleRepository.findByStatus(status);
    }

    @Override
    public List<Role> findByDescriptionContainingIgnoreCase(String description) {
        return roleRepository.findByDescriptionContainingIgnoreCase(description);
    }

    @Override
    public List<Role> findRolesWithFilters(String name, String description, Integer status) {
        return roleRepository.findRolesWithFilters(name, description, status);
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return roleRepository.findRolesByUserId(userId);
    }

    @Override
    public List<Role> findRolesByPermissionId(Long permissionId) {
        return roleRepository.findRolesByPermissionId(permissionId);
    }

    @Override
    public long countByStatus(Integer status) {
        return roleRepository.countByStatus(status);
    }

    @Override
    public List<Role> findByCreatedDateBetween(Timestamp startDate, Timestamp endDate) {
        return roleRepository.findByCreatedDateBetween(startDate, endDate);
    }

    @Override
    public List<Role> findByNameIn(List<String> names) {
        return roleRepository.findByNameIn(names);
    }

    @Override
    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        roleRepository.findByName(name).ifPresent(role -> roleRepository.delete(role));
    }
} 