package project.foodflow.model;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Group entity for managing user groups")
public class Group {
    
    @Schema(description = "Unique identifier for the group", example = "1")
    private Long id;
    
    @Schema(description = "Name of the group", example = "Sales Team")
    private String name;
    
    @Schema(description = "Role ID assigned to this group", example = "1")
    private Long roleId;
    
    @Schema(description = "Group's created at", example = "dd/mm/yyyy")
    private Timestamp createdDate;

    @Schema(description = "Group's created by", example = "1")
    private Long createdUser;

    @Schema(description = "Group's updated at", example = "dd/mm/yyyy")
    private Timestamp modifiedDate;

    @Schema(description = "Group's modified by", example = "1")
    private Long modifiedUser;

    @Schema(description = "Group's status, 1 is active, 0 is inactive", example = "1", allowableValues = {"1", "0"})
    private Integer status;
} 