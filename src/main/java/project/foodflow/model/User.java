package project.foodflow.model;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User entity")
public class User {
    
    @Schema(description = "Unique identifier for the user", example = "1")
    private Long id;
    
    @Schema(description = "User's full name", example = "John Doe")
    private String name;

    @Schema(description = "User's phone number", example = "0866666666")
    private String phone;

    @Schema(description = "User's email address", example = "john.doe@example.com")
    private String email;
    
    //Bycrypt Password Encoder  
    @Schema(description = "User's password (encrypted)", example = "hashedPassword123")
    private String password;
    
    @Schema(description = "User's sex, 1 is male, 0 is female, 2 is other", example = "1", allowableValues = {"1", "0", "2"})
    private Integer sex;

    @Schema(description = "User's address", example = "123 Main St, Anytown, USA")
    private String address;


    @Schema(description = "User's status, 1 is active, 0 is inactive", example = "1", allowableValues = {"1", "0"})
    private Integer status;
    
    @Schema(description = "User's date of birth", example = "dd/mm/yyyy")
    private String dateOfBirth;
  
    // email, phone, google, facebook, apple
    @Schema(description = "User's provider", example = "GOOGLE", allowableValues = {"EMAIL", "PHONE", "GOOGLE", "FACEBOOK", "APPLE"})
    private String provider;

    @Schema(description = "User's provider id", example = "1234567890")
    private String providerUserId;
    
    @Schema(description = "User's provider metadata", example = "{\"name\": \"John Doe\", \"email\": \"john.doe@example.com\"}")
    private String providerMetaData;



    @Schema(description = "User's created at", example = "dd/mm/yyyy")
    private Timestamp createdDate;

    @Schema(description = "User's created by", example = "1")
    private Long createdUser;

    @Schema(description = "User's updated at", example = "dd/mm/yyyy")
    private Timestamp modifiedDate;

    @Schema(description = "User's modified by", example = "1")
    private Long modifiedUser;

    @Schema(description = "User's active code", example = "123456")
    private String activeCode;

    @Schema(description = "User's active date", example = "dd/mm/yyyy")
    private Timestamp activeDate;

} 