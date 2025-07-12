package project.foodflow.model;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Role entity for defining system roles")
public class Role {
    
    @Schema(description = "Unique identifier for the role", example = "1")
    private Long id;
    
    @Schema(description = "Name of the role", example = "Admin")
    private String name;
    
    @Schema(description = "Description of the role", example = "Administrator with full system access")
    private String description;
    
    @Schema(description = "Role's created at", example = "dd/mm/yyyy")
    private Timestamp createdDate;

    @Schema(description = "Role's created by", example = "1")
    private Long createdUser;

    @Schema(description = "Role's updated at", example = "dd/mm/yyyy")
    private Timestamp modifiedDate;

    @Schema(description = "Role's modified by", example = "1")
    private Long modifiedUser;

    @Schema(description = "Role's status, 1 is active, 0 is inactive", example = "1", allowableValues = {"1", "0"})
    private Integer status;
} 