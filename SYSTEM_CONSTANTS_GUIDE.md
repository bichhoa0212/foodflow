# System Constants Guide

## Overview

The `SystemConstants` class provides centralized constants for the FoodFlow application to avoid hardcoding values throughout the codebase. This ensures consistency, maintainability, and reduces the risk of typos.

## Structure

The `SystemConstants` class is organized into logical groups:

### 1. Roles (`SystemConstants.Roles`)
```java
// Available roles
SystemConstants.Roles.ADMIN
SystemConstants.Roles.MANAGER
SystemConstants.Roles.STAFF
SystemConstants.Roles.CUSTOMER
SystemConstants.Roles.DRIVER
SystemConstants.Roles.RESTAURANT_OWNER
SystemConstants.Roles.RESTAURANT_STAFF

// Role descriptions
SystemConstants.Roles.ADMIN_DESCRIPTION
SystemConstants.Roles.CUSTOMER_DESCRIPTION
// ... etc
```

### 2. Groups (`SystemConstants.Groups`)
```java
// Available groups
SystemConstants.Groups.ADMINISTRATORS
SystemConstants.Groups.MANAGERS
SystemConstants.Groups.STAFF_MEMBERS
SystemConstants.Groups.CUSTOMERS
SystemConstants.Groups.DRIVERS
SystemConstants.Groups.RESTAURANT_OWNERS
SystemConstants.Groups.RESTAURANT_STAFF

// Group descriptions
SystemConstants.Groups.ADMINISTRATORS_DESCRIPTION
SystemConstants.Groups.CUSTOMERS_DESCRIPTION
// ... etc
```

### 3. Permissions (`SystemConstants.Permissions`)
```java
// User management
SystemConstants.Permissions.CREATE_USER
SystemConstants.Permissions.READ_USER
SystemConstants.Permissions.UPDATE_USER
SystemConstants.Permissions.DELETE_USER
SystemConstants.Permissions.LIST_USERS

// Role management
SystemConstants.Permissions.CREATE_ROLE
SystemConstants.Permissions.READ_ROLE
// ... etc

// Restaurant management
SystemConstants.Permissions.CREATE_RESTAURANT
SystemConstants.Permissions.READ_RESTAURANT
// ... etc

// Order management
SystemConstants.Permissions.CREATE_ORDER
SystemConstants.Permissions.READ_ORDER
// ... etc

// Customer specific
SystemConstants.Permissions.PLACE_ORDER
SystemConstants.Permissions.VIEW_OWN_ORDERS
SystemConstants.Permissions.UPDATE_PROFILE
SystemConstants.Permissions.VIEW_MENUS

// Driver specific
SystemConstants.Permissions.ACCEPT_DELIVERY
SystemConstants.Permissions.UPDATE_DELIVERY_STATUS
SystemConstants.Permissions.VIEW_ASSIGNED_DELIVERIES
```

### 4. User Status (`SystemConstants.UserStatus`)
```java
SystemConstants.UserStatus.ACTIVE        // 1
SystemConstants.UserStatus.INACTIVE      // 0
SystemConstants.UserStatus.SUSPENDED     // 2
SystemConstants.UserStatus.PENDING_VERIFICATION // 3
SystemConstants.UserStatus.DELETED       // 4
```

### 5. User Providers (`SystemConstants.UserProviders`)
```java
SystemConstants.UserProviders.LOCAL
SystemConstants.UserProviders.GOOGLE
SystemConstants.UserProviders.FACEBOOK
SystemConstants.UserProviders.APPLE
SystemConstants.UserProviders.GITHUB
```

### 6. Gender (`SystemConstants.Gender`)
```java
SystemConstants.Gender.MALE
SystemConstants.Gender.FEMALE
SystemConstants.Gender.OTHER
SystemConstants.Gender.PREFER_NOT_TO_SAY
```

### 7. System Users (`SystemConstants.SystemUsers`)
```java
SystemConstants.SystemUsers.SYSTEM_USER_ID    // 1L
SystemConstants.SystemUsers.SYSTEM_USER_NAME  // "SYSTEM"
SystemConstants.SystemUsers.SYSTEM_USER_EMAIL // "system@foodflow.com"
```

### 8. Default Values (`SystemConstants.Defaults`)
```java
SystemConstants.Defaults.DEFAULT_ROLE     // "CUSTOMER"
SystemConstants.Defaults.DEFAULT_GROUP    // "CUSTOMERS"
SystemConstants.Defaults.DEFAULT_STATUS   // 1 (ACTIVE)
SystemConstants.Defaults.DEFAULT_PROVIDER // "LOCAL"
```

### 9. JWT Constants (`SystemConstants.Jwt`)
```java
SystemConstants.Jwt.TOKEN_PREFIX           // "Bearer "
SystemConstants.Jwt.HEADER_STRING          // "Authorization"
SystemConstants.Jwt.ACCESS_TOKEN_EXPIRY    // 3600L (1 hour)
SystemConstants.Jwt.REFRESH_TOKEN_EXPIRY   // 86400L (24 hours)
```

### 10. Pagination (`SystemConstants.Pagination`)
```java
SystemConstants.Pagination.DEFAULT_PAGE_SIZE      // 20
SystemConstants.Pagination.MAX_PAGE_SIZE          // 100
SystemConstants.Pagination.DEFAULT_SORT_FIELD     // "id"
SystemConstants.Pagination.DEFAULT_SORT_DIRECTION // "DESC"
```

### 11. Validation (`SystemConstants.Validation`)
```java
SystemConstants.Validation.MIN_PASSWORD_LENGTH  // 8
SystemConstants.Validation.MAX_PASSWORD_LENGTH  // 128
SystemConstants.Validation.MIN_NAME_LENGTH      // 2
SystemConstants.Validation.MAX_NAME_LENGTH      // 100
SystemConstants.Validation.MAX_EMAIL_LENGTH     // 255
SystemConstants.Validation.MAX_PHONE_LENGTH     // 20
SystemConstants.Validation.MAX_ADDRESS_LENGTH   // 500
```

### 12. Error Messages (`SystemConstants.ErrorMessages`)
```java
SystemConstants.ErrorMessages.USER_NOT_FOUND
SystemConstants.ErrorMessages.USER_ALREADY_EXISTS
SystemConstants.ErrorMessages.ROLE_NOT_FOUND
SystemConstants.ErrorMessages.GROUP_NOT_FOUND
SystemConstants.ErrorMessages.PERMISSION_NOT_FOUND
SystemConstants.ErrorMessages.INVALID_CREDENTIALS
SystemConstants.ErrorMessages.INVALID_TOKEN
SystemConstants.ErrorMessages.INVALID_CHECKSUM
SystemConstants.ErrorMessages.ACCESS_DENIED
SystemConstants.ErrorMessages.VALIDATION_ERROR
```

### 13. Success Messages (`SystemConstants.SuccessMessages`)
```java
SystemConstants.SuccessMessages.USER_CREATED
SystemConstants.SuccessMessages.USER_UPDATED
SystemConstants.SuccessMessages.USER_DELETED
SystemConstants.SuccessMessages.ROLE_ASSIGNED
SystemConstants.SuccessMessages.GROUP_ASSIGNED
SystemConstants.SuccessMessages.LOGIN_SUCCESS
SystemConstants.SuccessMessages.LOGOUT_SUCCESS
SystemConstants.SuccessMessages.TOKEN_REFRESHED
```

## Usage Examples

### 1. In Service Classes
```java
@Service
public class UserService {
    
    public User createUser(UserRequest request) {
        User user = new User();
        user.setStatus(SystemConstants.Defaults.DEFAULT_STATUS);
        user.setProvider(SystemConstants.Defaults.DEFAULT_PROVIDER);
        user.setCreatedUser(SystemConstants.SystemUsers.SYSTEM_USER_ID);
        // ... rest of the logic
    }
    
    public void assignDefaultRole(User user) {
        Role customerRole = roleService.findByName(SystemConstants.Defaults.DEFAULT_ROLE)
            .orElseThrow(() -> new RoleNotFoundException(SystemConstants.ErrorMessages.ROLE_NOT_FOUND));
        userRoleService.assignRoleToUser(user.getId(), customerRole.getId());
    }
}
```

### 2. In Controllers
```java
@RestController
public class AuthController {
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse(SystemConstants.ErrorMessages.USER_ALREADY_EXISTS));
        }
    }
}
```

### 3. In Security Configuration
```java
@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/api/admin/**").hasRole(SystemConstants.Roles.ADMIN)
            .requestMatchers("/api/manager/**").hasRole(SystemConstants.Roles.MANAGER)
            .requestMatchers("/api/customer/**").hasRole(SystemConstants.Roles.CUSTOMER)
            .anyRequest().authenticated()
        );
        return http.build();
    }
}
```

### 4. In Validation
```java
@Component
public class UserValidator {
    
    public void validatePassword(String password) {
        if (password.length() < SystemConstants.Validation.MIN_PASSWORD_LENGTH) {
            throw new ValidationException("Password too short");
        }
        if (password.length() > SystemConstants.Validation.MAX_PASSWORD_LENGTH) {
            throw new ValidationException("Password too long");
        }
    }
    
    public void validateName(String name) {
        if (name.length() < SystemConstants.Validation.MIN_NAME_LENGTH) {
            throw new ValidationException("Name too short");
        }
        if (name.length() > SystemConstants.Validation.MAX_NAME_LENGTH) {
            throw new ValidationException("Name too long");
        }
    }
}
```

### 5. In JWT Service
```java
@Service
public class JwtService {
    
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + SystemConstants.Jwt.ACCESS_TOKEN_EXPIRY * 1000))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }
}
```

## Benefits

### 1. Consistency
- All parts of the application use the same values
- No typos or inconsistencies in role names, group names, etc.

### 2. Maintainability
- Changes to constants only need to be made in one place
- Easy to update role names, permission names, etc.

### 3. Type Safety
- Compile-time checking for constant usage
- IDE autocomplete support

### 4. Documentation
- Constants serve as living documentation
- Clear understanding of available options

### 5. Refactoring
- Easy to rename roles, permissions, etc.
- IDE can help with refactoring across the entire codebase

## Best Practices

### 1. Always Use Constants
```java
// ❌ Bad - Hardcoded values
user.setStatus(1);
roleService.findByName("CUSTOMER");

// ✅ Good - Using constants
user.setStatus(SystemConstants.UserStatus.ACTIVE);
roleService.findByName(SystemConstants.Roles.CUSTOMER);
```

### 2. Use Descriptive Names
```java
// ❌ Bad - Unclear meaning
if (status == 1) { ... }

// ✅ Good - Clear meaning
if (status == SystemConstants.UserStatus.ACTIVE) { ... }
```

### 3. Group Related Constants
```java
// ✅ Good - Logical grouping
SystemConstants.Roles.ADMIN
SystemConstants.Roles.CUSTOMER
SystemConstants.Groups.ADMINISTRATORS
SystemConstants.Groups.CUSTOMERS
```

### 4. Add New Constants When Needed
When adding new functionality, always add corresponding constants:

```java
// Add to SystemConstants.Permissions
public static final String MANAGE_NOTIFICATIONS = "MANAGE_NOTIFICATIONS";

// Add to SystemConstants.Roles
public static final String NOTIFICATION_MANAGER = "NOTIFICATION_MANAGER";

// Add to SystemConstants.Groups
public static final String NOTIFICATION_TEAM = "NOTIFICATION_TEAM";
```

### 5. Update Documentation
When adding new constants, update this guide and any related documentation.

## Migration Guide

If you have existing hardcoded values in your codebase:

1. **Identify hardcoded values**: Search for strings like "ADMIN", "CUSTOMER", etc.
2. **Replace with constants**: Use the appropriate constant from `SystemConstants`
3. **Test thoroughly**: Ensure all functionality still works
4. **Update database**: Ensure database values match the constants

## Database Alignment

The `data.sql` file has been updated to use the same constants as defined in `SystemConstants`. This ensures consistency between the application code and database data.

## Conclusion

Using `SystemConstants` provides a robust foundation for managing application-wide constants. It improves code quality, maintainability, and reduces the risk of errors. Always prefer using constants over hardcoded values throughout the application. 