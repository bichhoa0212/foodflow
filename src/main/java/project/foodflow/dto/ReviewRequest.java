package project.foodflow.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long restaurantId;
    private Integer rating;
    private String comment;
} 