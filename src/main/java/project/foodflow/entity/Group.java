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
@Table(name = "user_groups")
@Schema(description = "Group entity for managing user groups")
public class Group {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the group", example = "1")
    private Long id;
    
    @Column(name = "name", nullable = false, length = 100)
    @Schema(description = "Name of the group", example = "Sales Team")
    private String name;
    
    @Column(name = "role_id")
    @Schema(description = "Role ID assigned to this group", example = "1")
    private Long roleId;
    
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Group's created at", example = "dd/mm/yyyy")
    private Timestamp createdDate;

    @Column(name = "created_user")
    @Schema(description = "Group's created by", example = "1")
    private Long createdUser;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Group's updated at", example = "dd/mm/yyyy")
    private Timestamp modifiedDate;

    @Column(name = "modified_user")
    @Schema(description = "Group's modified by", example = "1")
    private Long modifiedUser;

    @Column(name = "status", nullable = false)
    @Schema(description = "Group's status, 1 is active, 0 is inactive", example = "1", allowableValues = {"1", "0"})
    private Integer status = 1;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupUser> groupUsers;

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roleId=" + roleId +
                ", createdDate=" + createdDate +
                ", createdUser=" + createdUser +
                ", modifiedDate=" + modifiedDate +
                ", modifiedUser=" + modifiedUser +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && 
               Objects.equals(name, group.name) && 
               Objects.equals(roleId, group.roleId) && 
               Objects.equals(createdDate, group.createdDate) && 
               Objects.equals(createdUser, group.createdUser) && 
               Objects.equals(modifiedDate, group.modifiedDate) && 
               Objects.equals(modifiedUser, group.modifiedUser) && 
               Objects.equals(status, group.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, roleId, createdDate, createdUser, modifiedDate, modifiedUser, status);
    }
}