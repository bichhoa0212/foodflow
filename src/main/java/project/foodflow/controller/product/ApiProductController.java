package project.foodflow.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.foodflow.constant.ReturnCode;
import project.foodflow.dto.ProductDto;
import project.foodflow.dto.Response;
import project.foodflow.entity.Product;
import project.foodflow.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ApiProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Response<ProductDto>> getProductDetail(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.status(404).body(new Response<>(ReturnCode.NOT_FOUND.getCode(), ReturnCode.NOT_FOUND.getStatus(), "Không tìm thấy sản phẩm", null));
        }
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setImageUrl(product.getImageUrl());
        dto.setPrice(product.getPrice());
        dto.setStatus(product.getStatus());
        // Có thể bổ sung thêm trường nếu muốn
        return ResponseEntity.ok(new Response<>(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getStatus(), "Lấy chi tiết sản phẩm thành công", dto));
    }
}
