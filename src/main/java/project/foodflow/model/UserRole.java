package project.foodflow.model;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User-Role junction entity for managing user-role relationships")
public class UserRole {
    
    @Schema(description = "Unique identifier for the user-role relationship", example = "1")
    private Long id;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Role ID", example = "1")
    private Long roleId;
    
    @Schema(description = "Time when role was assigned to the user", example = "dd/mm/yyyy")
    private Timestamp assignedAt;
    
    @Schema(description = "Relationship status, 1 is active, 0 is inactive", example = "1", allowableValues = {"1", "0"})
    private Integer status;
    
    @Schema(description = "Relationship created at", example = "dd/mm/yyyy")
    private Timestamp createdDate;

    @Schema(description = "Relationship created by", example = "1")
    private Long createdUser;

    @Schema(description = "Relationship updated at", example = "dd/mm/yyyy")
    private Timestamp modifiedDate;

    @Schema(description = "Relationship modified by", example = "1")
    private Long modifiedUser;
} 