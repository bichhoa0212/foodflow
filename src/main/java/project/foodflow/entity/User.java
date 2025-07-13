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
@Table(name = "app_users")
@Schema(description = "User entity")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the user", example = "1")
    private Long id;
    
    @Column(name = "name", nullable = false, length = 255)
    @Schema(description = "User's full name", example = "John Doe")
    private String name;

    @Column(name = "phone", unique = true, length = 20)
    @Schema(description = "User's phone number", example = "0866666666")
    private String phone;

    @Column(name = "email", unique = true, nullable = false, length = 255)
    @Schema(description = "User's email address", example = "john.doe@example.com")
    private String email;
    
    @Column(name = "password", nullable = false, length = 255)
    @Schema(description = "User's password (encrypted)", example = "hashedPassword123")
    private String password;
    
    @Column(name = "sex")
    @Schema(description = "User's sex, 1 is male, 0 is female, 2 is other", example = "1", allowableValues = {"1", "0", "2"})
    private Integer sex;

    @Column(name = "address", length = 500)
    @Schema(description = "User's address", example = "123 Main St, Anytown, USA")
    private String address;

    @Column(name = "status", nullable = false)
    @Schema(description = "User's status, 1 is active, 0 is inactive", example = "1", allowableValues = {"1", "0"})
    private Integer status = 1;
    
    @Column(name = "date_of_birth", length = 20)
    @Schema(description = "User's date of birth", example = "dd/mm/yyyy")
    private String dateOfBirth;
  
    @Column(name = "provider", length = 20)
    @Schema(description = "User's provider", example = "GOOGLE", allowableValues = {"EMAIL", "PHONE", "GOOGLE", "FACEBOOK", "APPLE"})
    private String provider;

    @Column(name = "provider_user_id", unique = true, length = 255)
    @Schema(description = "User's provider id", example = "1234567890")
    private String providerUserId;
    
    @Column(name = "provider_meta_data", columnDefinition = "TEXT")
    @Schema(description = "User's provider metadata", example = "{\"name\": \"John Doe\", \"email\": \"john.doe@example.com\"}")
    private String providerMetaData;

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "User's created at", example = "dd/mm/yyyy")
    private Timestamp createdDate;

    @Column(name = "created_user")
    @Schema(description = "User's created by", example = "1")
    private Long createdUser;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "User's updated at", example = "dd/mm/yyyy")
    private Timestamp modifiedDate;

    @Column(name = "modified_user")
    @Schema(description = "User's modified by", example = "1")
    private Long modifiedUser;

    @Column(name = "active_code", length = 10)
    @Schema(description = "User's active code", example = "123456")
    private String activeCode;

    @Column(name = "active_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "User's active date", example = "dd/mm/yyyy")
    private Timestamp activeDate;

    // Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupUser> groupUsers;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", provider='" + provider + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && 
               Objects.equals(email, user.email) && 
               Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, phone);
    }
} 