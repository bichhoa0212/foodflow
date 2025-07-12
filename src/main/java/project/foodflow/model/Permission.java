package project.foodflow.model;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Permission entity for defining system permissions")
public class Permission {
    
    @Schema(description = "Unique identifier for the permission", example = "1")
    private Long id;
    
    @Schema(description = "Name of the permission", example = "create_post")
    private String name;
    
    @Schema(description = "Description of the permission", example = "Permission to create new posts")
    private String description;
    
    @Schema(description = "Permission's created at", example = "dd/mm/yyyy")
    private Timestamp createdDate;

    @Schema(description = "Permission's created by", example = "1")
    private Long createdUser;

    @Schema(description = "Permission's updated at", example = "dd/mm/yyyy")
    private Timestamp modifiedDate;

    @Schema(description = "Permission's modified by", example = "1")
    private Long modifiedUser;

    @Schema(description = "Permission's status, 1 is active, 0 is inactive", example = "1", allowableValues = {"1", "0"})
    private Integer status;
} 