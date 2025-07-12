package project.foodflow.model;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Group-User junction entity for managing user-group relationships")
public class GroupUser {
    
    @Schema(description = "Unique identifier for the group-user relationship", example = "1")
    private Long id;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Group ID", example = "1")
    private Long groupId;
    
    @Schema(description = "Time when user was added to the group", example = "dd/mm/yyyy")
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