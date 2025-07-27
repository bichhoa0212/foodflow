package project.foodflow.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomExceptions.InvalidChecksumException.class)
    public ResponseEntity<ErrorResponse> handleInvalidChecksumException(CustomExceptions.InvalidChecksumException ex, WebRequest request) {
        log.error("Invalid Checksum Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Invalid Checksum")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(CustomExceptions.UserAlreadyExistsException ex, WebRequest request) {
        log.error("User Already Exists Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("User Already Exists")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(CustomExceptions.UserNotFoundException ex, WebRequest request) {
        log.error("User Not Found Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("User Not Found")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(CustomExceptions.InvalidTokenException ex, WebRequest request) {
        log.error("Invalid Token Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Invalid Token")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    // Product related exceptions
    @ExceptionHandler(CustomExceptions.ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(CustomExceptions.ProductNotFoundException ex, WebRequest request) {
        log.error("Product Not Found Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Product Not Found")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.ProductOutOfStockException.class)
    public ResponseEntity<ErrorResponse> handleProductOutOfStockException(CustomExceptions.ProductOutOfStockException ex, WebRequest request) {
        log.error("Product Out Of Stock Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Product Out Of Stock")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.badRequest().body(errorResponse);
    }

    // Address related exceptions
    @ExceptionHandler(CustomExceptions.AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAddressNotFoundException(CustomExceptions.AddressNotFoundException ex, WebRequest request) {
        log.error("Address Not Found Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Address Not Found")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.AddressLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleAddressLimitExceededException(CustomExceptions.AddressLimitExceededException ex, WebRequest request) {
        log.error("Address Limit Exceeded Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Address Limit Exceeded")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.AddressAccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAddressAccessDeniedException(CustomExceptions.AddressAccessDeniedException ex, WebRequest request) {
        log.error("Address Access Denied Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .error("Access Denied")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    // Favorite related exceptions
    @ExceptionHandler(CustomExceptions.FavoriteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFavoriteNotFoundException(CustomExceptions.FavoriteNotFoundException ex, WebRequest request) {
        log.error("Favorite Not Found Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Favorite Not Found")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.FavoriteAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleFavoriteAlreadyExistsException(CustomExceptions.FavoriteAlreadyExistsException ex, WebRequest request) {
        log.error("Favorite Already Exists Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Favorite Already Exists")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.FavoriteLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleFavoriteLimitExceededException(CustomExceptions.FavoriteLimitExceededException ex, WebRequest request) {
        log.error("Favorite Limit Exceeded Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Favorite Limit Exceeded")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.badRequest().body(errorResponse);
    }

    // Authentication & Authorization exceptions
    @ExceptionHandler(CustomExceptions.AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(CustomExceptions.AuthenticationException ex, WebRequest request) {
        log.error("Authentication Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Authentication Failed")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.AuthorizationException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationException(CustomExceptions.AuthorizationException ex, WebRequest request) {
        log.error("Authorization Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .error("Authorization Failed")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(CustomExceptions.AccessDeniedException ex, WebRequest request) {
        log.error("Access Denied Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .error("Access Denied")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    // Business rule exceptions
    @ExceptionHandler(CustomExceptions.BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRuleException(CustomExceptions.BusinessRuleException ex, WebRequest request) {
        log.error("Business Rule Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Business Rule Violation")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(CustomExceptions.ResourceNotFoundException ex, WebRequest request) {
        log.error("Resource Not Found Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Resource Not Found")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(CustomExceptions.DuplicateResourceException ex, WebRequest request) {
        log.error("Duplicate Resource Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Duplicate Resource")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.InvalidOperationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOperationException(CustomExceptions.InvalidOperationException ex, WebRequest request) {
        log.error("Invalid Operation Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Invalid Operation")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.badRequest().body(errorResponse);
    }

    // Database exceptions
    @ExceptionHandler(CustomExceptions.DatabaseException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseException(CustomExceptions.DatabaseException ex, WebRequest request) {
        log.error("Database Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Database Error")
                .message("Database operation failed")
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    // External service exceptions
    @ExceptionHandler(CustomExceptions.ExternalServiceException.class)
    public ResponseEntity<ErrorResponse> handleExternalServiceException(CustomExceptions.ExternalServiceException ex, WebRequest request) {
        log.error("External Service Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error("External Service Error")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.ServiceUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleServiceUnavailableException(CustomExceptions.ServiceUnavailableException ex, WebRequest request) {
        log.error("Service Unavailable Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error("Service Unavailable")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

    // Rate limiting exceptions
    @ExceptionHandler(CustomExceptions.RateLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleRateLimitExceededException(CustomExceptions.RateLimitExceededException ex, WebRequest request) {
        log.error("Rate Limit Exceeded Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.TOO_MANY_REQUESTS.value())
                .error("Rate Limit Exceeded")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.TooManyRequestsException.class)
    public ResponseEntity<ErrorResponse> handleTooManyRequestsException(CustomExceptions.TooManyRequestsException ex, WebRequest request) {
        log.error("Too Many Requests Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.TOO_MANY_REQUESTS.value())
                .error("Too Many Requests")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        log.error("Runtime Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        log.error("Bad Credentials Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Unauthorized")
                .message("Invalid username or password")
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        log.error("Username Not Found Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message("User not found")
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("Validation Exception: {}", ex.getMessage(), ex);
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Invalid input data")
                .path(request.getDescription(false))
                .details(errors)
                .build();
        
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        log.error("Generic Exception: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("An unexpected error occurred")
                .path(request.getDescription(false))
                .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
} 