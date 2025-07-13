package project.foodflow.constant;

/**
 * System-wide constants for the FoodFlow application
 * Centralized place for all constants to avoid hardcoding
 */
public final class SystemConstants {

    // Prevent instantiation
    private SystemConstants() {}

    // ==================== ROLES ====================
    public static final class Roles {
        public static final String ADMIN = "ADMIN";
        public static final String MANAGER = "MANAGER";
        public static final String STAFF = "STAFF";
        public static final String CUSTOMER = "CUSTOMER";
        public static final String DRIVER = "DRIVER";
        public static final String RESTAURANT_OWNER = "RESTAURANT_OWNER";
        public static final String RESTAURANT_STAFF = "RESTAURANT_STAFF";
        
        // Role descriptions
        public static final String ADMIN_DESCRIPTION = "System administrator with full access";
        public static final String MANAGER_DESCRIPTION = "Manager with administrative privileges";
        public static final String STAFF_DESCRIPTION = "Staff member with limited access";
        public static final String CUSTOMER_DESCRIPTION = "Regular customer with basic access";
        public static final String DRIVER_DESCRIPTION = "Delivery driver with delivery access";
        public static final String RESTAURANT_OWNER_DESCRIPTION = "Restaurant owner with restaurant management access";
        public static final String RESTAURANT_STAFF_DESCRIPTION = "Restaurant staff with order management access";
    }

    // ==================== GROUPS ====================
    public static final class Groups {
        public static final String ADMINISTRATORS = "ADMINISTRATORS";
        public static final String MANAGERS = "MANAGERS";
        public static final String STAFF_MEMBERS = "STAFF_MEMBERS";
        public static final String CUSTOMERS = "CUSTOMERS";
        public static final String DRIVERS = "DRIVERS";
        public static final String RESTAURANT_OWNERS = "RESTAURANT_OWNERS";
        public static final String RESTAURANT_STAFF = "RESTAURANT_STAFF";
        
        // Group descriptions
        public static final String ADMINISTRATORS_DESCRIPTION = "System administrators group";
        public static final String MANAGERS_DESCRIPTION = "Managers group";
        public static final String STAFF_MEMBERS_DESCRIPTION = "Staff members group";
        public static final String CUSTOMERS_DESCRIPTION = "Regular customers group";
        public static final String DRIVERS_DESCRIPTION = "Delivery drivers group";
        public static final String RESTAURANT_OWNERS_DESCRIPTION = "Restaurant owners group";
        public static final String RESTAURANT_STAFF_DESCRIPTION = "Restaurant staff group";
    }

    // ==================== PERMISSIONS ====================
    public static final class Permissions {
        // User management
        public static final String CREATE_USER = "CREATE_USER";
        public static final String READ_USER = "READ_USER";
        public static final String UPDATE_USER = "UPDATE_USER";
        public static final String DELETE_USER = "DELETE_USER";
        public static final String LIST_USERS = "LIST_USERS";
        
        // Role management
        public static final String CREATE_ROLE = "CREATE_ROLE";
        public static final String READ_ROLE = "READ_ROLE";
        public static final String UPDATE_ROLE = "UPDATE_ROLE";
        public static final String DELETE_ROLE = "DELETE_ROLE";
        public static final String LIST_ROLES = "LIST_ROLES";
        public static final String ASSIGN_ROLE = "ASSIGN_ROLE";
        
        // Group management
        public static final String CREATE_GROUP = "CREATE_GROUP";
        public static final String READ_GROUP = "READ_GROUP";
        public static final String UPDATE_GROUP = "UPDATE_GROUP";
        public static final String DELETE_GROUP = "DELETE_GROUP";
        public static final String LIST_GROUPS = "LIST_GROUPS";
        public static final String ADD_USER_TO_GROUP = "ADD_USER_TO_GROUP";
        
        // Permission management
        public static final String CREATE_PERMISSION = "CREATE_PERMISSION";
        public static final String READ_PERMISSION = "READ_PERMISSION";
        public static final String UPDATE_PERMISSION = "UPDATE_PERMISSION";
        public static final String DELETE_PERMISSION = "DELETE_PERMISSION";
        public static final String LIST_PERMISSIONS = "LIST_PERMISSIONS";
        public static final String ASSIGN_PERMISSION = "ASSIGN_PERMISSION";
        
        // Restaurant management
        public static final String CREATE_RESTAURANT = "CREATE_RESTAURANT";
        public static final String READ_RESTAURANT = "READ_RESTAURANT";
        public static final String UPDATE_RESTAURANT = "UPDATE_RESTAURANT";
        public static final String DELETE_RESTAURANT = "DELETE_RESTAURANT";
        public static final String LIST_RESTAURANTS = "LIST_RESTAURANTS";
        public static final String MANAGE_RESTAURANT = "MANAGE_RESTAURANT";
        
        // Menu management
        public static final String CREATE_MENU = "CREATE_MENU";
        public static final String READ_MENU = "READ_MENU";
        public static final String UPDATE_MENU = "UPDATE_MENU";
        public static final String DELETE_MENU = "DELETE_MENU";
        public static final String LIST_MENUS = "LIST_MENUS";
        
        // Order management
        public static final String CREATE_ORDER = "CREATE_ORDER";
        public static final String READ_ORDER = "READ_ORDER";
        public static final String UPDATE_ORDER = "UPDATE_ORDER";
        public static final String DELETE_ORDER = "DELETE_ORDER";
        public static final String LIST_ORDERS = "LIST_ORDERS";
        public static final String MANAGE_ORDER = "MANAGE_ORDER";
        public static final String CANCEL_ORDER = "CANCEL_ORDER";
        
        // Delivery management
        public static final String CREATE_DELIVERY = "CREATE_DELIVERY";
        public static final String READ_DELIVERY = "READ_DELIVERY";
        public static final String UPDATE_DELIVERY = "UPDATE_DELIVERY";
        public static final String DELETE_DELIVERY = "DELETE_DELIVERY";
        public static final String LIST_DELIVERIES = "LIST_DELIVERIES";
        public static final String ASSIGN_DELIVERY = "ASSIGN_DELIVERY";
        
        // Payment management
        public static final String CREATE_PAYMENT = "CREATE_PAYMENT";
        public static final String READ_PAYMENT = "READ_PAYMENT";
        public static final String UPDATE_PAYMENT = "UPDATE_PAYMENT";
        public static final String DELETE_PAYMENT = "DELETE_PAYMENT";
        public static final String LIST_PAYMENTS = "LIST_PAYMENTS";
        public static final String PROCESS_PAYMENT = "PROCESS_PAYMENT";
        
        // System management
        public static final String SYSTEM_CONFIG = "SYSTEM_CONFIG";
        public static final String VIEW_LOGS = "VIEW_LOGS";
        public static final String MANAGE_SYSTEM = "MANAGE_SYSTEM";
        
        // Customer specific
        public static final String PLACE_ORDER = "PLACE_ORDER";
        public static final String VIEW_OWN_ORDERS = "VIEW_OWN_ORDERS";
        public static final String UPDATE_PROFILE = "UPDATE_PROFILE";
        public static final String VIEW_MENUS = "VIEW_MENUS";
        
        // Driver specific
        public static final String ACCEPT_DELIVERY = "ACCEPT_DELIVERY";
        public static final String UPDATE_DELIVERY_STATUS = "UPDATE_DELIVERY_STATUS";
        public static final String VIEW_ASSIGNED_DELIVERIES = "VIEW_ASSIGNED_DELIVERIES";
    }

    // ==================== USER STATUS ====================
    public static final class UserStatus {
        public static final int ACTIVE = 1;
        public static final int INACTIVE = 0;
        public static final int SUSPENDED = 2;
        public static final int PENDING_VERIFICATION = 3;
        public static final int DELETED = 4;
    }

    // ==================== USER PROVIDERS ====================
    public static final class UserProviders {
        public static final String LOCAL = "LOCAL";
        public static final String GOOGLE = "GOOGLE";
        public static final String FACEBOOK = "FACEBOOK";
        public static final String APPLE = "APPLE";
        public static final String GITHUB = "GITHUB";
    }

    // ==================== GENDER ====================
    public static final class Gender {
        public static final String MALE = "MALE";
        public static final String FEMALE = "FEMALE";
        public static final String OTHER = "OTHER";
        public static final String PREFER_NOT_TO_SAY = "PREFER_NOT_TO_SAY";
    }

    // ==================== SYSTEM USERS ====================
    public static final class SystemUsers {
        public static final Long SYSTEM_USER_ID = 1L;
        public static final String SYSTEM_USER_NAME = "SYSTEM";
        public static final String SYSTEM_USER_EMAIL = "system@foodflow.com";
    }

    // ==================== DEFAULT VALUES ====================
    public static final class Defaults {
        public static final String DEFAULT_ROLE = Roles.CUSTOMER;
        public static final String DEFAULT_GROUP = Groups.CUSTOMERS;
        public static final int DEFAULT_STATUS = UserStatus.ACTIVE;
        public static final String DEFAULT_PROVIDER = UserProviders.LOCAL;
    }

    // ==================== JWT CONSTANTS ====================
    public static final class Jwt {
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final String HEADER_STRING = "Authorization";
        public static final long ACCESS_TOKEN_EXPIRY = 3600L; // 1 hour
        public static final long REFRESH_TOKEN_EXPIRY = 86400L; // 24 hours
    }

    // ==================== PAGINATION ====================
    public static final class Pagination {
        public static final int DEFAULT_PAGE_SIZE = 20;
        public static final int MAX_PAGE_SIZE = 100;
        public static final String DEFAULT_SORT_FIELD = "id";
        public static final String DEFAULT_SORT_DIRECTION = "DESC";
    }

    // ==================== VALIDATION ====================
    public static final class Validation {
        public static final int MIN_PASSWORD_LENGTH = 8;
        public static final int MAX_PASSWORD_LENGTH = 128;
        public static final int MIN_NAME_LENGTH = 2;
        public static final int MAX_NAME_LENGTH = 100;
        public static final int MAX_EMAIL_LENGTH = 255;
        public static final int MAX_PHONE_LENGTH = 20;
        public static final int MAX_ADDRESS_LENGTH = 500;
    }

    // ==================== ERROR MESSAGES ====================
    public static final class ErrorMessages {
        public static final String USER_NOT_FOUND = "User not found";
        public static final String USER_ALREADY_EXISTS = "User already exists";
        public static final String ROLE_NOT_FOUND = "Role not found";
        public static final String GROUP_NOT_FOUND = "Group not found";
        public static final String PERMISSION_NOT_FOUND = "Permission not found";
        public static final String INVALID_CREDENTIALS = "Invalid credentials";
        public static final String INVALID_TOKEN = "Invalid token";
        public static final String INVALID_CHECKSUM = "Invalid checksum";
        public static final String ACCESS_DENIED = "Access denied";
        public static final String VALIDATION_ERROR = "Validation error";
    }

    // ==================== SUCCESS MESSAGES ====================
    public static final class SuccessMessages {
        public static final String USER_CREATED = "User created successfully";
        public static final String USER_UPDATED = "User updated successfully";
        public static final String USER_DELETED = "User deleted successfully";
        public static final String ROLE_ASSIGNED = "Role assigned successfully";
        public static final String GROUP_ASSIGNED = "Group assigned successfully";
        public static final String LOGIN_SUCCESS = "Login successful";
        public static final String LOGOUT_SUCCESS = "Logout successful";
        public static final String TOKEN_REFRESHED = "Token refreshed successfully";
    }
} 