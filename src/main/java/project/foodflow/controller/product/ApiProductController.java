package project.foodflow.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import project.foodflow.constant.ReturnCode;
import project.foodflow.dto.OrderRequest;
import project.foodflow.dto.ProductDto;
import project.foodflow.dto.Response;
import project.foodflow.entity.Product;
import project.foodflow.entity.User;
import project.foodflow.repository.OrderItemRepository;
import project.foodflow.repository.OrderRepository;
import project.foodflow.repository.ProductRepository;
import project.foodflow.repository.ReviewRepository;
import project.foodflow.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ApiProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductService productService;


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

    @GetMapping("/top-purchased")
    public ResponseEntity<Response<java.util.List<ProductDto>>> getTop10PurchasedProducts() {
        java.util.List<ProductDto> dtos = productRepository.findTop10ByOrderByPurchaseCountDesc().stream().map(product -> {
            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setImageUrl(product.getImageUrl());
            dto.setPrice(product.getPrice());
            dto.setStatus(product.getStatus());
            dto.setPurchaseCount(product.getPurchaseCount());
            dto.setReviewCount(product.getReviewCount());
            return dto;
        }).toList();
        return ResponseEntity.ok(new Response<>(project.foodflow.constant.ReturnCode.SUCCESS.getCode(), project.foodflow.constant.ReturnCode.SUCCESS.getStatus(), "Top 10 sản phẩm mua nhiều nhất", dtos));
    }

    @GetMapping("/top-reviewed")
    public ResponseEntity<Response<java.util.List<ProductDto>>> getTop10ReviewedProducts() {
        java.util.List<ProductDto> dtos = productRepository.findTop10ByOrderByReviewCountDesc().stream().map(product -> {
            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setImageUrl(product.getImageUrl());
            dto.setPrice(product.getPrice());
            dto.setStatus(product.getStatus());
            dto.setPurchaseCount(product.getPurchaseCount());
            dto.setReviewCount(product.getReviewCount());
            return dto;
        }).toList();
        return ResponseEntity.ok(new Response<>(project.foodflow.constant.ReturnCode.SUCCESS.getCode(), project.foodflow.constant.ReturnCode.SUCCESS.getStatus(), "Top 10 sản phẩm được đánh giá nhiều nhất", dtos));
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<Response<java.util.Map<String, Object>>> getReviewsByProduct(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) Integer rating,
            @RequestParam(defaultValue = "desc") String sort
    ) {
        org.springframework.data.domain.Sort sortObj = sort.equalsIgnoreCase("asc") ? org.springframework.data.domain.Sort.by("createdDate").ascending() : org.springframework.data.domain.Sort.by("createdDate").descending();
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, sortObj);
        org.springframework.data.domain.Page<project.foodflow.entity.Review> reviewPage;
        if (rating != null) {
            reviewPage = reviewRepository.findByProductIdAndRating(id, rating, pageable);
        } else {
            reviewPage = reviewRepository.findByProductId(id, pageable);
        }
        java.util.List<project.foodflow.dto.ReviewDto> dtos = reviewPage.getContent().stream().map(review -> {
            var dto = new project.foodflow.dto.ReviewDto();
            dto.setId(review.getId());
            dto.setUserName(review.getUser().getName());
            dto.setRating(review.getRating());
            dto.setComment(review.getComment());
            dto.setCreatedDate(review.getCreatedDate());
            return dto;
        }).toList();
        java.util.Map<String, Object> result = new java.util.HashMap<>();
        result.put("total", reviewPage.getTotalElements());
        result.put("reviews", dtos);
        return ResponseEntity.ok(new Response<>(project.foodflow.constant.ReturnCode.SUCCESS.getCode(), project.foodflow.constant.ReturnCode.SUCCESS.getStatus(), "Lấy danh sách đánh giá của sản phẩm thành công", result));
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<Response<project.foodflow.dto.ReviewDto>> createReviewProduct(@PathVariable Long id, @RequestBody project.foodflow.dto.ReviewRequest request, @AuthenticationPrincipal User user) {
        if (request.getRating() == null || request.getRating() < 1 || request.getRating() > 5) {
            return ResponseEntity.badRequest().body(new Response<>(400, "BAD_REQUEST", "Số sao đánh giá không hợp lệ", null));
        }
        project.foodflow.entity.Review review = new project.foodflow.entity.Review();
        review.setProduct(new project.foodflow.entity.Product());
        review.getProduct().setId(id);
        review.setUser(user);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedDate(java.time.LocalDateTime.now());
        reviewRepository.save(review);
        project.foodflow.dto.ReviewDto dto = new project.foodflow.dto.ReviewDto();
        dto.setId(review.getId());
        dto.setUserName(user.getName());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedDate(review.getCreatedDate());
        return ResponseEntity.ok(new Response<>(project.foodflow.constant.ReturnCode.SUCCESS.getCode(), project.foodflow.constant.ReturnCode.SUCCESS.getStatus(), "Đánh giá thành công", dto));
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Response<project.foodflow.dto.ReviewDto>> updateReviewProduct(@PathVariable Long reviewId, @RequestBody project.foodflow.dto.ReviewRequest request, @AuthenticationPrincipal User user) {
        var reviewOpt = reviewRepository.findById(reviewId);
        if (reviewOpt.isEmpty()) {
            return ResponseEntity.status(404).body(new Response<>(404, "NOT_FOUND", "Không tìm thấy đánh giá", null));
        }
        var review = reviewOpt.get();
        if (!review.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).body(new Response<>(403, "FORBIDDEN", "Bạn không có quyền sửa đánh giá này", null));
        }
        if (request.getRating() != null) review.setRating(request.getRating());
        if (request.getComment() != null) review.setComment(request.getComment());
        reviewRepository.save(review);
        project.foodflow.dto.ReviewDto dto = new project.foodflow.dto.ReviewDto();
        dto.setId(review.getId());
        dto.setUserName(user.getName());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedDate(review.getCreatedDate());
        return ResponseEntity.ok(new Response<>(200, "SUCCESS", "Cập nhật đánh giá thành công", dto));
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Response<Void>> deleteReviewProduct(@PathVariable Long reviewId, @AuthenticationPrincipal User user) {
        var reviewOpt = reviewRepository.findById(reviewId);
        if (reviewOpt.isEmpty()) {
            return ResponseEntity.status(404).body(new Response<>(404, "NOT_FOUND", "Không tìm thấy đánh giá", null));
        }
        var review = reviewOpt.get();
        if (!review.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).body(new Response<>(403, "FORBIDDEN", "Bạn không có quyền xóa đánh giá này", null));
        }
        reviewRepository.deleteById(reviewId);
        return ResponseEntity.ok(new Response<>(200, "SUCCESS", "Xóa đánh giá thành công", null));
    }

    @PostMapping("/order")
    public ResponseEntity<Response<java.util.Map<String, Object>>> orderProducts(@RequestBody OrderRequest request, @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(new Response<>(200, "SUCCESS", "Đặt hàng thành công", null));
    }

}
