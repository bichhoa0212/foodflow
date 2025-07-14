package project.foodflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Định dạng chuẩn cho response trả về từ API")
public class Response<T> {
    @Schema(description = "Mã code trả về (ví dụ: 200, 400, 500)")
    private int code;
    @Schema(description = "Trạng thái (success, error, fail, ...)")
    private String status;
    @Schema(description = "Thông điệp mô tả kết quả")
    private String message;
    @Schema(description = "Dữ liệu trả về")
    private T data;
} 