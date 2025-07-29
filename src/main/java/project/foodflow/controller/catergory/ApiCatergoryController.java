package project.foodflow.controller.catergory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.foodflow.constant.ReturnCode;
import project.foodflow.dto.CategoryDto;
import project.foodflow.dto.Response;
import project.foodflow.entity.Category;
import project.foodflow.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class ApiCatergoryController {

    @Autowired
    private CategoryService categoryService;

    // viết cho tôi api lấy danh sách loại sản phẩm
    @GetMapping("/list")
    public ResponseEntity<Response<List<CategoryDto>>> getAllCatergory() {
        List<Category> categories = categoryService.getAllCategories();

        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> new CategoryDto(category.getId(), category.getName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new Response<>(
            ReturnCode.SUCCESS.getCode(),
            ReturnCode.SUCCESS.getStatus(),
            "Lấy danh sách loại sản phẩm thành công",
            categoryDtos
        ));
    }

    // API lấy danh sách categories với pagination
    @GetMapping
    public ResponseEntity<Response<org.springframework.data.domain.Page<CategoryDto>>> getCategories(
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "0") int page,
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "50") int size
    ) {
        try {
            org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size);
            org.springframework.data.domain.Page<Category> categoryPage = categoryService.getAllCategories(pageable);
            
            org.springframework.data.domain.Page<CategoryDto> dtoPage = categoryPage.map(category -> 
                new CategoryDto(category.getId(), category.getName(), category.getDescription(), category.getImageUrl())
            );
            
            return ResponseEntity.ok(new Response<>(
                ReturnCode.SUCCESS.getCode(),
                ReturnCode.SUCCESS.getStatus(),
                "Lấy danh sách danh mục thành công",
                dtoPage
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response<>(
                ReturnCode.ERROR.getCode(),
                ReturnCode.ERROR.getStatus(),
                "Lỗi khi lấy danh sách danh mục: " + e.getMessage(),
                null
            ));
        }
    }
}
