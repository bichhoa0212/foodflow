package project.foodflow.entity;

import java.sql.Timestamp;

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
@Table(name = "user_roles")
@Schema(description = "User-Role junction entity for managing user-role relationships")
public class UserRole {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the user-role relationship", example = "1")
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Column(name = "role_id", nullable = false)
    @Schema(description = "Role ID", example = "1")
    private Long roleId;
    
    @Column(name = "assigned_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Time when role was assigned to the user", example = "dd/mm/yyyy")
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
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", assignedAt=" + assignedAt +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", createdUser=" + createdUser +
                ", modifiedDate=" + modifiedDate +
                ", modifiedUser=" + modifiedUser +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return id != null && id.equals(userRole.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
} 