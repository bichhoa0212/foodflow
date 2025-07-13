package project.foodflow.entity;

import java.sql.Timestamp;
import java.util.List;
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
@Table(name = "app_permissions")
@Schema(description = "Permission entity for defining system permissions")
public class Permission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the permission", example = "1")
    private Long id;
    
    @Column(name = "name", nullable = false, unique = true, length = 100)
    @Schema(description = "Name of the permission", example = "create_post")
    private String name;
    
    @Column(name = "description", length = 500)
    @Schema(description = "Description of the permission", example = "Permission to create new posts")
    private String description;
    
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Permission's created at", example = "dd/mm/yyyy")
    private Timestamp createdDate;

    @Column(name = "created_user")
    @Schema(description = "Permission's created by", example = "1")
    private Long createdUser;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Permission's updated at", example = "dd/mm/yyyy")
    private Timestamp modifiedDate;

    @Column(name = "modified_user")
    @Schema(description = "Permission's modified by", example = "1")
    private Long modifiedUser;

    @Column(name = "status", nullable = false)
    @Schema(description = "Permission's status, 1 is active, 0 is inactive", example = "1", allowableValues = {"1", "0"})
    private Integer status = 1;

    // Relationships
    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RolePermission> rolePermissions;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
} 