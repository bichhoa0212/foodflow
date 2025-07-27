package project.foodflow.exception;

public class CustomExceptions {

    public static class InvalidChecksumException extends RuntimeException {
        public InvalidChecksumException(String message) {
            super(message);
        }
    }

    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidTokenException extends RuntimeException {
        public InvalidTokenException(String message) {
            super(message);
        }
    }

    public static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }

    public static class RoleNotFoundException extends RuntimeException {
        public RoleNotFoundException(String message) {
            super(message);
        }
    }

    public static class GroupNotFoundException extends RuntimeException {
        public GroupNotFoundException(String message) {
            super(message);
        }
    }

    // Product related exceptions
    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    public static class ProductAlreadyExistsException extends RuntimeException {
        public ProductAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class ProductOutOfStockException extends RuntimeException {
        public ProductOutOfStockException(String message) {
            super(message);
        }
    }

    public static class ProductInactiveException extends RuntimeException {
        public ProductInactiveException(String message) {
            super(message);
        }
    }

    // Category related exceptions
    public static class CategoryNotFoundException extends RuntimeException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }

    public static class CategoryAlreadyExistsException extends RuntimeException {
        public CategoryAlreadyExistsException(String message) {
            super(message);
        }
    }

    // Supplier related exceptions
    public static class SupplierNotFoundException extends RuntimeException {
        public SupplierNotFoundException(String message) {
            super(message);
        }
    }

    public static class SupplierAlreadyExistsException extends RuntimeException {
        public SupplierAlreadyExistsException(String message) {
            super(message);
        }
    }

    // Order related exceptions
    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }

    public static class OrderAlreadyExistsException extends RuntimeException {
        public OrderAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class OrderStatusException extends RuntimeException {
        public OrderStatusException(String message) {
            super(message);
        }
    }

    public static class OrderCancellationException extends RuntimeException {
        public OrderCancellationException(String message) {
            super(message);
        }
    }

    // Address related exceptions
    public static class AddressNotFoundException extends RuntimeException {
        public AddressNotFoundException(String message) {
            super(message);
        }
    }

    public static class AddressLimitExceededException extends RuntimeException {
        public AddressLimitExceededException(String message) {
            super(message);
        }
    }

    public static class AddressAccessDeniedException extends RuntimeException {
        public AddressAccessDeniedException(String message) {
            super(message);
        }
    }

    // Favorite related exceptions
    public static class FavoriteNotFoundException extends RuntimeException {
        public FavoriteNotFoundException(String message) {
            super(message);
        }
    }

    public static class FavoriteAlreadyExistsException extends RuntimeException {
        public FavoriteAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class FavoriteLimitExceededException extends RuntimeException {
        public FavoriteLimitExceededException(String message) {
            super(message);
        }
    }

    // Promotion related exceptions
    public static class PromotionNotFoundException extends RuntimeException {
        public PromotionNotFoundException(String message) {
            super(message);
        }
    }

    public static class PromotionExpiredException extends RuntimeException {
        public PromotionExpiredException(String message) {
            super(message);
        }
    }

    public static class PromotionUsageLimitExceededException extends RuntimeException {
        public PromotionUsageLimitExceededException(String message) {
            super(message);
        }
    }

    public static class PromotionNotApplicableException extends RuntimeException {
        public PromotionNotApplicableException(String message) {
            super(message);
        }
    }

    // Authentication & Authorization exceptions
    public static class AuthenticationException extends RuntimeException {
        public AuthenticationException(String message) {
            super(message);
        }
    }

    public static class AuthorizationException extends RuntimeException {
        public AuthorizationException(String message) {
            super(message);
        }
    }

    public static class AccessDeniedException extends RuntimeException {
        public AccessDeniedException(String message) {
            super(message);
        }
    }

    public static class InsufficientPermissionsException extends RuntimeException {
        public InsufficientPermissionsException(String message) {
            super(message);
        }
    }

    // File & Media exceptions
    public static class FileNotFoundException extends RuntimeException {
        public FileNotFoundException(String message) {
            super(message);
        }
    }

    public static class FileUploadException extends RuntimeException {
        public FileUploadException(String message) {
            super(message);
        }
    }

    public static class InvalidFileTypeException extends RuntimeException {
        public InvalidFileTypeException(String message) {
            super(message);
        }
    }

    public static class FileSizeExceededException extends RuntimeException {
        public FileSizeExceededException(String message) {
            super(message);
        }
    }

    // Payment & Transaction exceptions
    public static class PaymentException extends RuntimeException {
        public PaymentException(String message) {
            super(message);
        }
    }

    public static class TransactionNotFoundException extends RuntimeException {
        public TransactionNotFoundException(String message) {
            super(message);
        }
    }

    public static class PaymentMethodNotSupportedException extends RuntimeException {
        public PaymentMethodNotSupportedException(String message) {
            super(message);
        }
    }

    public static class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }

    // Inventory & Stock exceptions
    public static class StockNotFoundException extends RuntimeException {
        public StockNotFoundException(String message) {
            super(message);
        }
    }

    public static class InsufficientStockException extends RuntimeException {
        public InsufficientStockException(String message) {
            super(message);
        }
    }

    public static class StockReceiptException extends RuntimeException {
        public StockReceiptException(String message) {
            super(message);
        }
    }

    public static class StockIssueException extends RuntimeException {
        public StockIssueException(String message) {
            super(message);
        }
    }

    // Review & Rating exceptions
    public static class ReviewNotFoundException extends RuntimeException {
        public ReviewNotFoundException(String message) {
            super(message);
        }
    }

    public static class ReviewAlreadyExistsException extends RuntimeException {
        public ReviewAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class InvalidRatingException extends RuntimeException {
        public InvalidRatingException(String message) {
            super(message);
        }
    }

    // Notification exceptions
    public static class NotificationNotFoundException extends RuntimeException {
        public NotificationNotFoundException(String message) {
            super(message);
        }
    }

    public static class NotificationSendException extends RuntimeException {
        public NotificationSendException(String message) {
            super(message);
        }
    }

    // Coupon exceptions
    public static class CouponNotFoundException extends RuntimeException {
        public CouponNotFoundException(String message) {
            super(message);
        }
    }

    public static class CouponExpiredException extends RuntimeException {
        public CouponExpiredException(String message) {
            super(message);
        }
    }

    public static class CouponAlreadyUsedException extends RuntimeException {
        public CouponAlreadyUsedException(String message) {
            super(message);
        }
    }

    public static class CouponUsageLimitExceededException extends RuntimeException {
        public CouponUsageLimitExceededException(String message) {
            super(message);
        }
    }

    // Cart exceptions
    public static class CartNotFoundException extends RuntimeException {
        public CartNotFoundException(String message) {
            super(message);
        }
    }

    public static class CartItemNotFoundException extends RuntimeException {
        public CartItemNotFoundException(String message) {
            super(message);
        }
    }

    public static class CartLimitExceededException extends RuntimeException {
        public CartLimitExceededException(String message) {
            super(message);
        }
    }

    // Shipping exceptions
    public static class ShipperNotFoundException extends RuntimeException {
        public ShipperNotFoundException(String message) {
            super(message);
        }
    }

    public static class ShippingException extends RuntimeException {
        public ShippingException(String message) {
            super(message);
        }
    }

    public static class DeliveryAreaNotSupportedException extends RuntimeException {
        public DeliveryAreaNotSupportedException(String message) {
            super(message);
        }
    }

    // General business exceptions
    public static class BusinessRuleException extends RuntimeException {
        public BusinessRuleException(String message) {
            super(message);
        }
    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateResourceException extends RuntimeException {
        public DuplicateResourceException(String message) {
            super(message);
        }
    }

    public static class InvalidOperationException extends RuntimeException {
        public InvalidOperationException(String message) {
            super(message);
        }
    }

    public static class DataIntegrityException extends RuntimeException {
        public DataIntegrityException(String message) {
            super(message);
        }
    }

    // Database exceptions
    public static class DatabaseException extends RuntimeException {
        public DatabaseException(String message) {
            super(message);
        }
    }

    public static class ConnectionException extends RuntimeException {
        public ConnectionException(String message) {
            super(message);
        }
    }

    public static class TransactionException extends RuntimeException {
        public TransactionException(String message) {
            super(message);
        }
    }

    // External service exceptions
    public static class ExternalServiceException extends RuntimeException {
        public ExternalServiceException(String message) {
            super(message);
        }
    }

    public static class ThirdPartyApiException extends RuntimeException {
        public ThirdPartyApiException(String message) {
            super(message);
        }
    }

    public static class ServiceUnavailableException extends RuntimeException {
        public ServiceUnavailableException(String message) {
            super(message);
        }
    }

    // Rate limiting & throttling exceptions
    public static class RateLimitExceededException extends RuntimeException {
        public RateLimitExceededException(String message) {
            super(message);
        }
    }

    public static class TooManyRequestsException extends RuntimeException {
        public TooManyRequestsException(String message) {
            super(message);
        }
    }

    // Configuration exceptions
    public static class ConfigurationException extends RuntimeException {
        public ConfigurationException(String message) {
            super(message);
        }
    }

    public static class MissingConfigurationException extends RuntimeException {
        public MissingConfigurationException(String message) {
            super(message);
        }
    }

    // Cache exceptions
    public static class CacheException extends RuntimeException {
        public CacheException(String message) {
            super(message);
        }
    }

    public static class CacheKeyNotFoundException extends RuntimeException {
        public CacheKeyNotFoundException(String message) {
            super(message);
        }
    }
} 