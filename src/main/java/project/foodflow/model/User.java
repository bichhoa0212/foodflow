package project.foodflow.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User entity")
public class User {
    
    @Schema(description = "Unique identifier for the user", example = "1")
    private Long id;
    
    @Schema(description = "User's full name", example = "John Doe")
    private String name;
    
    @Schema(description = "User's email address", example = "john.doe@example.com")
    private String email;
    
    @Schema(description = "User's phone number", example = "+1234567890")
    private String phone;
    
    @Schema(description = "User's role", example = "CUSTOMER", allowableValues = {"ADMIN", "CUSTOMER", "RESTAURANT"})
    private String role;
} 