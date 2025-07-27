package project.foodflow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.foodflow.service.ProductAuditService;

import java.util.Map;

@RestController
@RequestMapping("/api/product-audit")
@RequiredArgsConstructor
public class ProductAuditController {

    private final ProductAuditService productAuditService;

    /**
     * Tạo sản phẩm mới - không cần Authentication parameter
     */
    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestParam String productName, @RequestParam String description) {
        try {
            String productId = productAuditService.createProduct(productName, description);
            return ResponseEntity.ok("Product created successfully with ID: " + productId);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Cập nhật sản phẩm - không cần Authentication parameter
     */
    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestParam String productId, @RequestParam String newName) {
        try {
            boolean success = productAuditService.updateProduct(productId, newName);
            if (success) {
                return ResponseEntity.ok("Product updated successfully");
            } else {
                return ResponseEntity.badRequest().body("Product not found");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Xóa sản phẩm - không cần Authentication parameter
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam String productId) {
        try {
            boolean success = productAuditService.deleteProduct(productId);
            if (success) {
                return ResponseEntity.ok("Product deleted successfully");
            } else {
                return ResponseEntity.badRequest().body("Product not found");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Lấy thông tin sản phẩm - không cần Authentication parameter
     */
    @GetMapping("/get")
    public ResponseEntity<String> getProduct(@RequestParam String productId) {
        String product = productAuditService.getProduct(productId);
        return ResponseEntity.ok("Product: " + product);
    }

    /**
     * Lấy danh sách tất cả sản phẩm - không cần Authentication parameter
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, String>> getAllProducts() {
        Map<String, String> products = productAuditService.getAllProducts();
        return ResponseEntity.ok(products);
    }
} 