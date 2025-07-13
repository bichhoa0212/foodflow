package project.foodflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.foodflow.constant.SystemConstants;
import project.foodflow.entity.Group;
import project.foodflow.entity.GroupUser;
import project.foodflow.repository.GroupRepository;
import project.foodflow.repository.GroupUserRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    
    @Autowired
    private GroupUserRepository groupUserRepository;

    @Override
    public Group save(Group group) {
        if (group.getCreatedDate() == null) {
            group.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        }
        group.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        return groupRepository.save(group);
    }

    @Override
    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public Optional<Group> findByName(String name) {
        return groupRepository.findByName(name);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> findByStatus(Integer status) {
        return groupRepository.findByStatus(status);
    }

    @Override
    public List<Group> findByRoleId(Long roleId) {
        return groupRepository.findByRoleId(roleId);
    }

    @Override
    public List<Group> findGroupsWithFilters(String name, Long roleId, Integer status) {
        return groupRepository.findGroupsWithFilters(name, roleId, status);
    }

    @Override
    public List<Group> findGroupsByUserId(Long userId) {
        return groupRepository.findGroupsByUserId(userId);
    }

    @Override
    public List<Group> findGroupsByRoleName(String roleName) {
        return groupRepository.findGroupsByRoleName(roleName);
    }

    @Override
    public long countByStatus(Integer status) {
        return groupRepository.countByStatus(status);
    }

    @Override
    public long countByRoleId(Long roleId) {
        return groupRepository.countByRoleId(roleId);
    }

    @Override
    public List<Group> findByCreatedDateBetween(Timestamp startDate, Timestamp endDate) {
        return groupRepository.findByCreatedDateBetween(startDate, endDate);
    }

    @Override
    public List<Group> findByNameIn(List<String> names) {
        return groupRepository.findByNameIn(names);
    }

    @Override
    public List<Group> findByNameContainingIgnoreCase(String name) {
        return groupRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public boolean existsByName(String name) {
        return groupRepository.existsByName(name);
    }

    @Override
    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        groupRepository.findByName(name).ifPresent(group -> groupRepository.delete(group));
    }

    @Override
    public void addUserToGroup(Long userId, Long groupId) {
        // Check if group exists
        if (!groupRepository.existsById(groupId)) {
            throw new IllegalArgumentException("Group not found with ID: " + groupId);
        }
        
        // Check if user-group relationship already exists
        Optional<GroupUser> existingGroupUser = groupUserRepository.findByGroupIdAndUserId(groupId, userId);
        if (existingGroupUser.isEmpty()) {
            GroupUser groupUser = new GroupUser();
            groupUser.setUserId(userId);
            groupUser.setGroupId(groupId);
            groupUser.setStatus(1);
            groupUser.setAssignedAt(new Timestamp(System.currentTimeMillis()));
            groupUser.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            groupUser.setCreatedUser(SystemConstants.SystemUsers.SYSTEM_USER_ID);
            groupUserRepository.save(groupUser);
        }
    }
} 