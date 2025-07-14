package project.foodflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageData<T> {
    private List<T> data;
    private long count; // số lượng phần tử trong trang hiện tại
    private long total; // tổng số phần tử
    private int page;   // trang hiện tại
    private int size;   // kích thước trang
} 