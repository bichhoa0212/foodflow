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
@RequestMapping("/api/public/categories")
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
}
