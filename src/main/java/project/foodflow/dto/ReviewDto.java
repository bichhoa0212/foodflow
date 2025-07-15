package project.foodflow.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReviewDto {
    private Long id;
    private String userName;
    private Integer rating;
    private String comment;
    private LocalDateTime createdDate;
} 