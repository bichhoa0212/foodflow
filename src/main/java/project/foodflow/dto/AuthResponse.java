package project.foodflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Authentication response")
public class AuthResponse {
    
    @Schema(description = "Access token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;
    
    @Schema(description = "Refresh token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String refreshToken;
    
    @Schema(description = "Token type", example = "Bearer")
    private String tokenType = "Bearer";
    
    @Schema(description = "Access token expiration time in seconds", example = "3600")
    private Long expiresIn;
    
    @Schema(description = "User information")
    private UserInfo userInfo;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "User information in auth response")
    public static class UserInfo {
        
        @Schema(description = "User ID", example = "1")
        private Long id;
        
        @Schema(description = "User's name", example = "John Doe")
        private String name;
        
        @Schema(description = "User's email", example = "john.doe@example.com")
        private String email;
        
        @Schema(description = "User's phone", example = "0866666666")
        private String phone;
        
        @Schema(description = "User's roles", example = "[\"CUSTOMER\", \"ADMIN\"]")
        private String[] roles;
        
        @Schema(description = "User's permissions", example = "[\"READ_ORDERS\", \"CREATE_ORDERS\"]")
        private String[] permissions;
    }
} 