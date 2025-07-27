package project.foodflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO cho địa chỉ giao hàng của người dùng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressDto {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String province;
    private String district;
    private String ward;
    private Boolean isDefault;
    private Integer status;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
} 