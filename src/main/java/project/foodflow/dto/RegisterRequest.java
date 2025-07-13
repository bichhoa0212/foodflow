package project.foodflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User registration request")
public class RegisterRequest {
    
    @Schema(description = "User's full name", example = "John Doe")
    private String name;
    
    @Schema(description = "User's email address", example = "john.doe@example.com")
    private String email;
    
    @Schema(description = "User's phone number", example = "0866666666")
    private String phone;
    
    @Schema(description = "User's password", example = "password123")
    private String password;
    
    @Schema(description = "User's sex, 1 is male, 0 is female, 2 is other", example = "1", allowableValues = {"1", "0", "2"})
    private Integer sex;
    
    @Schema(description = "User's address", example = "123 Main St, Anytown, USA")
    private String address;
    
    @Schema(description = "User's date of birth", example = "01/01/1990")
    private String dateOfBirth;
    
    @Schema(description = "Authentication provider", example = "EMAIL", allowableValues = {"EMAIL", "PHONE", "GOOGLE", "FACEBOOK", "APPLE"})
    private String provider;
    
    @Schema(description = "Provider user ID (for social login)", example = "1234567890")
    private String providerUserId;
    
    @Schema(description = "Provider metadata (for social login)", example = "{\"name\": \"John Doe\", \"email\": \"john.doe@example.com\"}")
    private String providerMetaData;
    
    @Schema(description = "SHA256 checksum for security validation", example = "f9f339b206c764044aa8b51b7ccea74ea3f983b0030db9503ee6200e1f15de7f")
    private String checksum;
    
    @Schema(description = "Language preference", example = "1")
    private Integer language;
    
    @Schema(description = "Device name", example = "SM-M146B")
    private String deviceName;
} 