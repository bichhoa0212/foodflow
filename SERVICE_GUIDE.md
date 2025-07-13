# Service Layer Guide

## Overview
This document describes the service layer of the FoodFlow application, which provides business logic and data access through repositories.

## Service Interfaces and Implementations

### 1. UserService & UserServiceImpl
Handles user-related business operations.

#### Key Features:
- **UserDetailsService Implementation**: Integrates with Spring Security
- **Permission-based Authorities**: Dynamically loads user permissions
- **Comprehensive User Management**: CRUD operations with advanced filtering
- **OAuth Support**: Provider-based user lookup

#### Key Methods:
```java
// Authentication & Security
UserDetails loadUserByUsername(String username)
User findByEmailForUserDetails(String email)

// Basic CRUD
User save(User user)
Optional<User> findById(Long id)
List<User> findAll()
void deleteById(Long id)

// Search & Filtering
Optional<User> findByEmail(String email)
Optional<User> findByPhone(String phone)
Optional<User> findByEmailOrPhone(String username)
List<User> findByStatus(Integer status)
List<User> findByRoleName(String roleName)
List<User> findByGroupName(String groupName)
List<User> findUsersWithFilters(String name, String email, String phone, Integer status)

// OAuth & Provider
List<User> findByProvider(String provider)
Optional<User> findByProviderAndProviderUserId(String provider, String providerUserId)

// Validation
boolean existsByEmail(String email)
boolean existsByPhone(String phone)

// Statistics
long countByStatus(Integer status)
```

#### Usage Examples:
```java
@Autowired
private UserService userService;

// Create new user
User user = new User();
user.setName("John Doe");
user.setEmail("john@example.com");
user.setPassword(encodedPassword);
User savedUser = userService.save(user);

// Find user by email
Optional<User> user = userService.findByEmail("admin@foodflow.com");

// Search users with filters
List<User> users = userService.findUsersWithFilters("John", null, null, 1);

// Check if email exists
boolean exists = userService.existsByEmail("user@example.com");

// Get user count by status
long activeUsers = userService.countByStatus(1);
```

### 2. RoleService & RoleServiceImpl
Handles role-related business operations.

#### Key Features:
- **Role Management**: Complete CRUD operations
- **Relationship Queries**: Find roles by users and permissions
- **Advanced Filtering**: Search with multiple criteria
- **Batch Operations**: Handle multiple roles efficiently

#### Key Methods:
```java
// Basic CRUD
Role save(Role role)
Optional<Role> findById(Long id)
Optional<Role> findByName(String name)
List<Role> findAll()
void deleteById(Long id)
void deleteByName(String name)

// Search & Filtering
List<Role> findByStatus(Integer status)
List<Role> findByDescriptionContainingIgnoreCase(String description)
List<Role> findRolesWithFilters(String name, String description, Integer status)

// Relationship Queries
List<Role> findRolesByUserId(Long userId)
List<Role> findRolesByPermissionId(Long permissionId)

// Batch Operations
List<Role> findByNameIn(List<String> names)

// Validation
boolean existsByName(String name)

// Statistics
long countByStatus(Integer status)

// Date Range Queries
List<Role> findByCreatedDateBetween(Timestamp startDate, Timestamp endDate)
```

#### Usage Examples:
```java
@Autowired
private RoleService roleService;

// Create new role
Role role = new Role();
role.setName("MODERATOR");
role.setDescription("Moderator with limited access");
Role savedRole = roleService.save(role);

// Find role by name
Optional<Role> adminRole = roleService.findByName("ADMIN");

// Find roles for a user
List<Role> userRoles = roleService.findRolesByUserId(1L);

// Search roles with filters
List<Role> roles = roleService.findRolesWithFilters("Admin", null, 1);

// Check if role exists
boolean exists = roleService.existsByName("MODERATOR");
```

### 3. PermissionService & PermissionServiceImpl
Handles permission-related business operations.

#### Key Features:
- **Permission Management**: Complete CRUD operations
- **Category-based Queries**: Group permissions by category
- **Relationship Queries**: Find permissions by roles and users
- **Pattern Matching**: Search by name patterns

#### Key Methods:
```java
// Basic CRUD
Permission save(Permission permission)
Optional<Permission> findById(Long id)
Optional<Permission> findByName(String name)
List<Permission> findAll()
void deleteById(Long id)
void deleteByName(String name)

// Search & Filtering
List<Permission> findByStatus(Integer status)
List<Permission> findByDescriptionContainingIgnoreCase(String description)
List<Permission> findPermissionsWithFilters(String name, String description, Integer status)

// Relationship Queries
List<Permission> findPermissionsByRoleId(Long roleId)
List<Permission> findPermissionsByUserId(Long userId)

// Category & Pattern Queries
List<Permission> findByCategory(String category) // e.g., "user:", "role:"
List<Permission> findByNameContainingIgnoreCase(String namePattern)

// Batch Operations
List<Permission> findByNameIn(List<String> names)

// Validation
boolean existsByName(String name)

// Statistics
long countByStatus(Integer status)

// Date Range Queries
List<Permission> findByCreatedDateBetween(Timestamp startDate, Timestamp endDate)
```

#### Usage Examples:
```java
@Autowired
private PermissionService permissionService;

// Create new permission
Permission permission = new Permission();
permission.setName("user:create");
permission.setDescription("Create new users");
Permission savedPermission = permissionService.save(permission);

// Find permission by name
Optional<Permission> permission = permissionService.findByName("user:read");

// Find all user-related permissions
List<Permission> userPermissions = permissionService.findByCategory("user:");

// Find permissions for a role
List<Permission> rolePermissions = permissionService.findPermissionsByRoleId(1L);

// Find permissions for a user
List<Permission> userPermissions = permissionService.findPermissionsByUserId(1L);

// Search by pattern
List<Permission> readPermissions = permissionService.findByNameContainingIgnoreCase("read");
```

### 4. GroupService & GroupServiceImpl
Handles group-related business operations.

#### Key Features:
- **Group Management**: Complete CRUD operations
- **Role-based Groups**: Find groups by associated roles
- **User Membership**: Find groups for specific users
- **Advanced Filtering**: Search with multiple criteria

#### Key Methods:
```java
// Basic CRUD
Group save(Group group)
Optional<Group> findById(Long id)
Optional<Group> findByName(String name)
List<Group> findAll()
void deleteById(Long id)
void deleteByName(String name)

// Search & Filtering
List<Group> findByStatus(Integer status)
List<Group> findByRoleId(Long roleId)
List<Group> findGroupsWithFilters(String name, Long roleId, Integer status)

// Relationship Queries
List<Group> findGroupsByUserId(Long userId)
List<Group> findGroupsByRoleName(String roleName)

// Pattern Matching
List<Group> findByNameContainingIgnoreCase(String name)

// Batch Operations
List<Group> findByNameIn(List<String> names)

// Validation
boolean existsByName(String name)

// Statistics
long countByStatus(Integer status)
long countByRoleId(Long roleId)

// Date Range Queries
List<Group> findByCreatedDateBetween(Timestamp startDate, Timestamp endDate)
```

#### Usage Examples:
```java
@Autowired
private GroupService groupService;

// Create new group
Group group = new Group();
group.setName("Sales Team");
group.setRoleId(2L);
Group savedGroup = groupService.save(group);

// Find group by name
Optional<Group> group = groupService.findByName("Administrators");

// Find groups for a user
List<Group> userGroups = groupService.findGroupsByUserId(1L);

// Find groups by role
List<Group> adminGroups = groupService.findGroupsByRoleName("ADMIN");

// Search groups with filters
List<Group> groups = groupService.findGroupsWithFilters("Team", null, 1);

// Count groups by status
long activeGroups = groupService.countByStatus(1);
```

### 5. UserRoleService & UserRoleServiceImpl
Handles user-role relationship operations.

#### Key Features:
- **Relationship Management**: Assign/remove roles from users
- **Business Logic**: High-level operations for role assignment
- **Efficient Queries**: JOIN FETCH for related data
- **Status Management**: Active/inactive role assignments

#### Key Methods:
```java
// Basic CRUD
UserRole save(UserRole userRole)
Optional<UserRole> findById(Long id)
List<UserRole> findAll()

// Relationship Queries
List<UserRole> findByUserId(Long userId)
List<UserRole> findByRoleId(Long roleId)
Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId)

// Status-based Queries
List<UserRole> findByUserIdAndStatus(Long userId, Integer status)
List<UserRole> findByRoleIdAndStatus(Long roleId, Integer status)

// Advanced Queries
List<UserRole> findByUserIdAndRoleName(Long userId, String roleName)
List<UserRole> findByRoleName(String roleName)
List<UserRole> findAllActiveWithUserAndRole()
List<UserRole> findByUserIdWithRole(Long userId)
List<UserRole> findByRoleIdWithUser(Long roleId)

// Business Methods
void assignRoleToUser(Long userId, Long roleId)
void removeRoleFromUser(Long userId, Long roleId)
boolean userHasRole(Long userId, String roleName)
List<String> getUserRoleNames(Long userId)

// Validation
boolean existsByUserIdAndRoleIdAndStatus(Long userId, Long roleId, Integer status)

// Statistics
long countByUserId(Long userId)
long countByRoleId(Long roleId)
long countByUserIdAndStatus(Long userId, Integer status)

// Deletion
void deleteByUserId(Long userId)
void deleteByRoleId(Long roleId)
void deleteByUserIdAndRoleId(Long userId, Long roleId)

// Date Range Queries
List<UserRole> findByCreatedDateBetween(Timestamp startDate, Timestamp endDate)
```

#### Usage Examples:
```java
@Autowired
private UserRoleService userRoleService;

// Assign role to user
userRoleService.assignRoleToUser(1L, 2L);

// Remove role from user
userRoleService.removeRoleFromUser(1L, 2L);

// Check if user has role
boolean hasAdminRole = userRoleService.userHasRole(1L, "ADMIN");

// Get user's role names
List<String> roleNames = userRoleService.getUserRoleNames(1L);

// Find all roles for a user
List<UserRole> userRoles = userRoleService.findByUserId(1L);

// Find all users with admin role
List<UserRole> adminUsers = userRoleService.findByRoleName("ADMIN");

// Find user roles with role details
List<UserRole> userRolesWithDetails = userRoleService.findByUserIdWithRole(1L);
```

## Best Practices

### 1. Transaction Management
All service methods are transactional by default. Use `@Transactional` for complex operations:

```java
@Service
@Transactional
public class UserService {
    
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    @Transactional
    public User createUserWithRole(User user, String roleName) {
        // Complex operation involving multiple repositories
        User savedUser = userRepository.save(user);
        Role role = roleRepository.findByName(roleName).orElseThrow();
        userRoleService.assignRoleToUser(savedUser.getId(), role.getId());
        return savedUser;
    }
}
```

### 2. Error Handling
Handle business exceptions appropriately:

```java
@Service
public class UserServiceImpl implements UserService {
    
    @Override
    public User save(User user) {
        if (userService.existsByEmail(user.getEmail())) {
            throw new DuplicateUserException("User with email " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }
    
    @Override
    public void assignRoleToUser(Long userId, Long roleId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        if (!roleRepository.existsById(roleId)) {
            throw new RoleNotFoundException("Role not found with ID: " + roleId);
        }
        userRoleService.assignRoleToUser(userId, roleId);
    }
}
```

### 3. Validation
Implement proper validation in service layer:

```java
@Service
public class UserServiceImpl implements UserService {
    
    @Override
    public User save(User user) {
        // Validate required fields
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("User name is required");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("User email is required");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("User password is required");
        }
        
        // Validate email format
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        
        // Check for duplicates
        if (existsByEmail(user.getEmail())) {
            throw new DuplicateUserException("User with email " + user.getEmail() + " already exists");
        }
        
        return userRepository.save(user);
    }
    
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
```

### 4. Business Logic
Implement business logic in service layer:

```java
@Service
public class UserRoleServiceImpl implements UserRoleService {
    
    @Override
    public void assignRoleToUser(Long userId, Long roleId) {
        // Business rule: Check if user already has this role
        if (existsByUserIdAndRoleIdAndStatus(userId, roleId, 1)) {
            throw new DuplicateRoleAssignmentException("User already has this role");
        }
        
        // Business rule: Check role limits
        long userRoleCount = countByUserIdAndStatus(userId, 1);
        if (userRoleCount >= 5) {
            throw new RoleLimitExceededException("User cannot have more than 5 roles");
        }
        
        // Create user-role relationship
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRole.setStatus(1);
        userRole.setAssignedAt(new Timestamp(System.currentTimeMillis()));
        save(userRole);
    }
}
```

### 5. Performance Optimization
Use appropriate methods for performance:

```java
@Service
public class UserServiceImpl implements UserService {
    
    // Use JOIN FETCH for related data
    public List<User> getUsersWithRoles() {
        return userRepository.findAll(); // Will use JOIN FETCH if configured
    }
    
    // Use batch operations for multiple records
    public void assignRolesToUsers(Map<Long, List<Long>> userRoleMap) {
        userRoleMap.forEach((userId, roleIds) -> {
            roleIds.forEach(roleId -> userRoleService.assignRoleToUser(userId, roleId));
        });
    }
    
    // Use read-only transactions for queries
    @Transactional(readOnly = true)
    public List<User> findActiveUsers() {
        return userRepository.findByStatus(1);
    }
}
```

## Integration with Controllers

Services are used in controllers to handle business logic:

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    
    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<Void> assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        userRoleService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{userId}/roles")
    public ResponseEntity<List<String>> getUserRoles(@PathVariable Long userId) {
        List<String> roleNames = userRoleService.getUserRoleNames(userId);
        return ResponseEntity.ok(roleNames);
    }
}
```

This service layer provides a clean separation of concerns, proper business logic implementation, and efficient data access through repositories. 