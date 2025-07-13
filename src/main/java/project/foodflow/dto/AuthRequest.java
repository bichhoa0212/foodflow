package project.foodflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Authentication request")
public class AuthRequest {
    
    @Schema(description = "Authentication provider", example = "PHONE", allowableValues = {"EMAIL", "PHONE", "GOOGLE", "FACEBOOK", "APPLE"})
    private String provider;
    
    @Schema(description = "Provider user ID (phone number, email, etc.)", example = "0348236580")
    private String providerUserId;
    
    @Schema(description = "User's password", example = "123456")
    private String password;
    
    @Schema(description = "SHA256 checksum for security validation", example = "f9f339b206c764044aa8b51b7ccea74ea3f983b0030db9503ee6200e1f15de7f")
    private String checksum;
    
    @Schema(description = "Language preference", example = "1")
    private Integer language;
    
    @Schema(description = "Device name", example = "SM-M146B")
    private String deviceName;
} 