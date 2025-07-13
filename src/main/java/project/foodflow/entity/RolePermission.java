package project.foodflow.entity;

import java.sql.Timestamp;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_permissions")
@Schema(description = "Role-Permission junction entity for managing role-permission relationships")
public class RolePermission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the role-permission relationship", example = "1")
    private Long id;
    
    @Column(name = "role_id", nullable = false)
    @Schema(description = "Role ID", example = "1")
    private Long roleId;
    
    @Column(name = "permission_id", nullable = false)
    @Schema(description = "Permission ID", example = "1")
    private Long permissionId;
    
    @Column(name = "assigned_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Time when permission was assigned to the role", example = "dd/mm/yyyy")
    private Timestamp assignedAt;
    
    @Column(name = "status", nullable = false)
    @Schema(description = "Relationship status, 1 is active, 0 is inactive", example = "1", allowableValues = {"1", "0"})
    private Integer status = 1;
    
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Relationship created at", example = "dd/mm/yyyy")
    private Timestamp createdDate;

    @Column(name = "created_user")
    @Schema(description = "Relationship created by", example = "1")
    private Long createdUser;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Relationship updated at", example = "dd/mm/yyyy")
    private Timestamp modifiedDate;

    @Column(name = "modified_user")
    @Schema(description = "Relationship modified by", example = "1")
    private Long modifiedUser;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", insertable = false, updatable = false)
    private Permission permission;

    @Override
    public String toString() {
        return "RolePermission{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermission that = (RolePermission) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
} 