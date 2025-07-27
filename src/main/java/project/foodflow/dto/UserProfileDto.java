package project.foodflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * DTO cho thông tin profile của người dùng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Integer sex;
    private String dateOfBirth;
    private String avatar;
    private String provider;
    private Integer status;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private List<String> roles;
    private List<String> permissions;
} 