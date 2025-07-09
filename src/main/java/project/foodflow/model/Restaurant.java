package project.foodflow.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Restaurant entity")
public class Restaurant {
    
    @Schema(description = "Unique identifier for the restaurant", example = "1")
    private Long id;
    
    @Schema(description = "Restaurant name", example = "Pizza Palace")
    private String name;
    
    @Schema(description = "Restaurant description", example = "Best pizza in town")
    private String description;
    
    @Schema(description = "Restaurant address", example = "123 Main St, City, State")
    private String address;
    
    @Schema(description = "Restaurant phone number", example = "+1234567890")
    private String phone;
    
    @Schema(description = "Restaurant rating", example = "4.5", minimum = "0", maximum = "5")
    private Double rating;
    
    @Schema(description = "Restaurant cuisine type", example = "Italian")
    private String cuisine;
} 