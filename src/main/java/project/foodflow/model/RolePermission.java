package project.foodflow.model;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Role-Permission junction entity for managing role-permission relationships")
public class RolePermission {
    
    @Schema(description = "Unique identifier for the role-permission relationship", example = "1")
    private Long id;
    
    @Schema(description = "Role ID", example = "1")
    private Long roleId;
    
    @Schema(description = "Permission ID", example = "1")
    private Long permissionId;
    
    @Schema(description = "Time when permission was granted to the role", example = "dd/mm/yyyy")
    private Timestamp grantedAt;
    
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