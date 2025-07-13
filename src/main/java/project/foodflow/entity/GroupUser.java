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
@Table(name = "group_users")
@Schema(description = "Group-User junction entity for managing group-user relationships")
public class GroupUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the group-user relationship", example = "1")
    private Long id;
    
    @Column(name = "group_id", nullable = false)
    @Schema(description = "Group ID", example = "1")
    private Long groupId;
    
    @Column(name = "user_id", nullable = false)
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Column(name = "assigned_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Time when user was assigned to the group", example = "dd/mm/yyyy")
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
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Override
    public String toString() {
        return "GroupUser{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", userId=" + userId +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupUser groupUser = (GroupUser) o;
        return id != null && id.equals(groupUser.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
} 