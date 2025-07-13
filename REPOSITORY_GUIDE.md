# Repository Layer Guide

## Overview
This document describes the repository layer of the FoodFlow application, which provides data access methods for all entities.

## Repository Interfaces

### 1. UserRepository
Handles user-related database operations.

#### Key Methods:
- `findByEmail(String email)` - Find user by email
- `findByPhone(String phone)` - Find user by phone number
- `findByEmailOrPhone(String email, String phone)` - Find user by email or phone
- `findByStatus(Integer status)` - Find users by status
- `findByProvider(String provider)` - Find users by authentication provider
- `findByProviderAndProviderUserId(String provider, String providerUserId)` - Find user by OAuth provider
- `existsByEmail(String email)` - Check if email exists
- `existsByPhone(String phone)` - Check if phone exists
- `findByRoleName(String roleName)` - Find users by role name
- `findByGroupName(String groupName)` - Find users by group name
- `findUsersWithFilters(...)` - Advanced search with multiple filters
- `countByStatus(Integer status)` - Count users by status
- `findByCreatedDateBetween(...)` - Find users created in date range

#### Usage Examples:
```java
@Autowired
private UserRepository userRepository;

// Find user by email
Optional<User> user = userRepository.findByEmail("admin@foodflow.com");

// Check if email exists
boolean exists = userRepository.existsByEmail("user@example.com");

// Find all admin users
List<User> admins = userRepository.findByRoleName("ADMIN");

// Search users with filters
List<User> users = userRepository.findUsersWithFilters("John", null, null, 1);
```

### 2. RoleRepository
Handles role-related database operations.

#### Key Methods:
- `findByName(String name)` - Find role by name
- `findByStatus(Integer status)` - Find roles by status
- `existsByName(String name)` - Check if role name exists
- `findByDescriptionContainingIgnoreCase(String description)` - Search by description
- `findRolesWithFilters(...)` - Advanced search with filters
- `findRolesByUserId(Long userId)` - Find roles assigned to a user
- `findRolesByPermissionId(Long permissionId)` - Find roles with specific permission
- `countByStatus(Integer status)` - Count roles by status
- `findByCreatedDateBetween(...)` - Find roles created in date range
- `findByNameIn(List<String> names)` - Find multiple roles by names

#### Usage Examples:
```java
@Autowired
private RoleRepository roleRepository;

// Find role by name
Optional<Role> adminRole = roleRepository.findByName("ADMIN");

// Find all active roles
List<Role> activeRoles = roleRepository.findByStatus(1);

// Find roles for a user
List<Role> userRoles = roleRepository.findRolesByUserId(1L);

// Check if role exists
boolean exists = roleRepository.existsByName("MODERATOR");
```

### 3. PermissionRepository
Handles permission-related database operations.

#### Key Methods:
- `findByName(String name)` - Find permission by name
- `findByStatus(Integer status)` - Find permissions by status
- `existsByName(String name)` - Check if permission name exists
- `findByDescriptionContainingIgnoreCase(String description)` - Search by description
- `findPermissionsWithFilters(...)` - Advanced search with filters
- `findPermissionsByRoleId(Long roleId)` - Find permissions for a role
- `findPermissionsByUserId(Long userId)` - Find permissions for a user
- `findByNameIn(List<String> names)` - Find multiple permissions by names
- `countByStatus(Integer status)` - Count permissions by status
- `findByCreatedDateBetween(...)` - Find permissions created in date range
- `findByNameContainingIgnoreCase(String namePattern)` - Search by name pattern
- `findByCategory(String category)` - Find permissions by category (e.g., "user:")

#### Usage Examples:
```java
@Autowired
private PermissionRepository permissionRepository;

// Find permission by name
Optional<Permission> permission = permissionRepository.findByName("user:read");

// Find all user-related permissions
List<Permission> userPermissions = permissionRepository.findByCategory("user:");

// Find permissions for a role
List<Permission> rolePermissions = permissionRepository.findPermissionsByRoleId(1L);

// Find permissions for a user
List<Permission> userPermissions = permissionRepository.findPermissionsByUserId(1L);
```

### 4. GroupRepository
Handles group-related database operations.

#### Key Methods:
- `findByName(String name)` - Find group by name
- `findByStatus(Integer status)` - Find groups by status
- `findByRoleId(Long roleId)` - Find groups by role ID
- `existsByName(String name)` - Check if group name exists
- `findGroupsWithFilters(...)` - Advanced search with filters
- `findGroupsByUserId(Long userId)` - Find groups for a user
- `findGroupsByRoleName(String roleName)` - Find groups by role name
- `countByStatus(Integer status)` - Count groups by status
- `countByRoleId(Long roleId)` - Count groups by role ID
- `findByCreatedDateBetween(...)` - Find groups created in date range
- `findByNameIn(List<String> names)` - Find multiple groups by names
- `findByNameContainingIgnoreCase(String name)` - Search by name

#### Usage Examples:
```java
@Autowired
private GroupRepository groupRepository;

// Find group by name
Optional<Group> group = groupRepository.findByName("Administrators");

// Find groups for a user
List<Group> userGroups = groupRepository.findGroupsByUserId(1L);

// Find groups by role
List<Group> adminGroups = groupRepository.findGroupsByRoleName("ADMIN");

// Count groups by status
long activeGroups = groupRepository.countByStatus(1);
```

### 5. UserRoleRepository
Handles user-role relationship operations.

#### Key Methods:
- `findByUserId(Long userId)` - Find roles for a user
- `findByRoleId(Long roleId)` - Find users for a role
- `findByUserIdAndRoleId(Long userId, Long roleId)` - Find specific user-role
- `findByUserIdAndStatus(Long userId, Integer status)` - Find active roles for user
- `findByRoleIdAndStatus(Long roleId, Integer status)` - Find active users for role
- `existsByUserIdAndRoleIdAndStatus(...)` - Check if user has role
- `countByUserId(Long userId)` - Count roles for user
- `countByRoleId(Long roleId)` - Count users for role
- `findByUserIdAndRoleName(...)` - Find user roles by role name
- `findByRoleName(String roleName)` - Find all users with role
- `findAllActiveWithUserAndRole()` - Find all active user roles with details
- `findByUserIdWithRole(Long userId)` - Find user roles with role details
- `findByRoleIdWithUser(Long roleId)` - Find role users with user details
- `deleteByUserId(Long userId)` - Remove all roles from user
- `deleteByRoleId(Long roleId)` - Remove all users from role
- `deleteByUserIdAndRoleId(Long userId, Long roleId)` - Remove specific user-role

#### Usage Examples:
```java
@Autowired
private UserRoleRepository userRoleRepository;

// Find all roles for a user
List<UserRole> userRoles = userRoleRepository.findByUserId(1L);

// Check if user has admin role
boolean hasAdminRole = userRoleRepository.existsByUserIdAndRoleIdAndStatus(1L, 1L, 1);

// Find all admin users
List<UserRole> adminUsers = userRoleRepository.findByRoleName("ADMIN");

// Remove role from user
userRoleRepository.deleteByUserIdAndRoleId(1L, 2L);
```

### 6. RolePermissionRepository
Handles role-permission relationship operations.

#### Key Methods:
- `findByRoleId(Long roleId)` - Find permissions for a role
- `findByPermissionId(Long permissionId)` - Find roles for a permission
- `findByRoleIdAndPermissionId(Long roleId, Long permissionId)` - Find specific role-permission
- `findByRoleIdAndStatus(Long roleId, Integer status)` - Find active permissions for role
- `findByPermissionIdAndStatus(Long permissionId, Integer status)` - Find active roles for permission
- `existsByRoleIdAndPermissionIdAndStatus(...)` - Check if role has permission
- `countByRoleId(Long roleId)` - Count permissions for role
- `countByPermissionId(Long permissionId)` - Count roles for permission
- `findByRoleName(String roleName)` - Find permissions by role name
- `findByPermissionName(String permissionName)` - Find roles by permission name
- `findByRoleNameAndPermissionName(...)` - Find specific role-permission by names
- `findAllActiveWithRoleAndPermission()` - Find all active role permissions with details
- `findByRoleIdWithPermission(Long roleId)` - Find role permissions with permission details
- `findByPermissionIdWithRole(Long permissionId)` - Find permission roles with role details
- `findPermissionNamesByRoleId(Long roleId)` - Get permission names for role
- `findRoleNamesByPermissionId(Long permissionId)` - Get role names for permission
- `deleteByRoleId(Long roleId)` - Remove all permissions from role
- `deleteByPermissionId(Long permissionId)` - Remove all roles from permission
- `deleteByRoleIdAndPermissionId(Long roleId, Long permissionId)` - Remove specific role-permission

#### Usage Examples:
```java
@Autowired
private RolePermissionRepository rolePermissionRepository;

// Find all permissions for admin role
List<RolePermission> adminPermissions = rolePermissionRepository.findByRoleId(1L);

// Check if admin role has user:read permission
boolean hasPermission = rolePermissionRepository.existsByRoleIdAndPermissionIdAndStatus(1L, 1L, 1);

// Get permission names for a role
List<String> permissionNames = rolePermissionRepository.findPermissionNamesByRoleId(1L);

// Find all roles with user:read permission
List<RolePermission> rolesWithPermission = rolePermissionRepository.findByPermissionName("user:read");
```

### 7. GroupUserRepository
Handles group-user relationship operations.

#### Key Methods:
- `findByGroupId(Long groupId)` - Find users in a group
- `findByUserId(Long userId)` - Find groups for a user
- `findByGroupIdAndUserId(Long groupId, Long userId)` - Find specific group-user
- `findByGroupIdAndStatus(Long groupId, Integer status)` - Find active users in group
- `findByUserIdAndStatus(Long userId, Integer status)` - Find active groups for user
- `existsByGroupIdAndUserIdAndStatus(...)` - Check if user is in group
- `countByGroupId(Long groupId)` - Count users in group
- `countByUserId(Long userId)` - Count groups for user
- `findByGroupName(String groupName)` - Find users by group name
- `findByUserEmail(String email)` - Find groups by user email
- `findByGroupNameAndUserEmail(...)` - Find specific group-user by names
- `findAllActiveWithGroupAndUser()` - Find all active group users with details
- `findByGroupIdWithUser(Long groupId)` - Find group users with user details
- `findByUserIdWithGroup(Long userId)` - Find user groups with group details
- `findUserIdsByGroupId(Long groupId)` - Get user IDs in group
- `findGroupIdsByUserId(Long userId)` - Get group IDs for user
- `findGroupNamesByUserId(Long userId)` - Get group names for user
- `findUserEmailsByGroupId(Long groupId)` - Get user emails in group
- `deleteByGroupId(Long groupId)` - Remove all users from group
- `deleteByUserId(Long userId)` - Remove user from all groups
- `deleteByGroupIdAndUserId(Long groupId, Long userId)` - Remove specific group-user
- `findByRoleName(String roleName)` - Find group users by role name

#### Usage Examples:
```java
@Autowired
private GroupUserRepository groupUserRepository;

// Find all users in admin group
List<GroupUser> adminUsers = groupUserRepository.findByGroupId(1L);

// Check if user is in admin group
boolean inAdminGroup = groupUserRepository.existsByGroupIdAndUserIdAndStatus(1L, 1L, 1);

// Get group names for a user
List<String> groupNames = groupUserRepository.findGroupNamesByUserId(1L);

// Find all users in admin group
List<GroupUser> adminGroupUsers = groupUserRepository.findByGroupName("Administrators");

// Remove user from group
groupUserRepository.deleteByGroupIdAndUserId(1L, 2L);
```

## Best Practices

### 1. Use Optional for Single Results
```java
// Good
Optional<User> user = userRepository.findByEmail("admin@foodflow.com");
if (user.isPresent()) {
    // Handle user
}

// Avoid
User user = userRepository.findByEmail("admin@foodflow.com");
if (user != null) {
    // Handle user
}
```

### 2. Use Status Filtering
```java
// Always filter by status for active records
List<User> activeUsers = userRepository.findByStatus(1);
List<Role> activeRoles = roleRepository.findByStatus(1);
```

### 3. Use JOIN FETCH for Related Data
```java
// Use methods with JOIN FETCH for better performance
List<UserRole> userRoles = userRoleRepository.findByUserIdWithRole(userId);
List<GroupUser> groupUsers = groupUserRepository.findByGroupIdWithUser(groupId);
```

### 4. Use Batch Operations
```java
// For multiple records, use batch operations
List<Role> roles = roleRepository.findByNameIn(Arrays.asList("ADMIN", "USER", "MODERATOR"));
List<Permission> permissions = permissionRepository.findByNameIn(permissionNames);
```

### 5. Use Custom Queries for Complex Operations
```java
// Use custom queries for complex filtering
List<User> users = userRepository.findUsersWithFilters(name, email, phone, status);
List<Role> roles = roleRepository.findRolesWithFilters(name, description, status);
```

## Transaction Management

All repository methods are transactional by default. For complex operations involving multiple repositories, use `@Transactional` at the service layer:

```java
@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserRoleRepository userRoleRepository;
    
    public void createUserWithRole(User user, String roleName) {
        // Save user
        User savedUser = userRepository.save(user);
        
        // Find role
        Role role = roleRepository.findByName(roleName).orElseThrow();
        
        // Create user-role relationship
        UserRole userRole = new UserRole();
        userRole.setUserId(savedUser.getId());
        userRole.setRoleId(role.getId());
        userRoleRepository.save(userRole);
    }
}
```

## Error Handling

Handle repository exceptions appropriately:

```java
try {
    User user = userRepository.findByEmail(email).orElseThrow();
} catch (NoSuchElementException e) {
    throw new UserNotFoundException("User not found with email: " + email);
} catch (DataIntegrityViolationException e) {
    throw new DuplicateUserException("User with this email already exists");
}
``` 