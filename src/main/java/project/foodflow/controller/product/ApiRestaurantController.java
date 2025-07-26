//package project.foodflow.controller.product;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import project.foodflow.constant.ReturnCode;
//import project.foodflow.dto.RestaurantDto;
//import project.foodflow.dto.Response;
//import project.foodflow.entity.Restaurant;
//import project.foodflow.repository.RestaurantRepository;
//import java.util.List;
//import project.foodflow.repository.ProductRepository;
//import project.foodflow.repository.ReviewRepository;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import project.foodflow.entity.User;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Page;
//
//@RestController
//@RequestMapping("/api/restaurants")
//public class ApiRestaurantController {
//    @Autowired
//    private RestaurantRepository restaurantRepository;
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @GetMapping("/top-purchased")
//    public ResponseEntity<Response<List<RestaurantDto>>> getTop10PurchasedRestaurants() {
//        List<RestaurantDto> dtos = restaurantRepository.findTop10ByOrderByPurchaseCountDesc().stream().map(restaurant -> {
//            RestaurantDto dto = new RestaurantDto();
//            dto.setId(restaurant.getId());
//            dto.setName(restaurant.getName());
//            dto.setAddress(restaurant.getAddress());
//            dto.setPhoneNumber(restaurant.getPhoneNumber());
//            dto.setLogoUrl(restaurant.getLogoUrl());
//            dto.setCoverImageUrl(restaurant.getCoverImageUrl());
//            dto.setDescription(restaurant.getDescription());
//            dto.setRating(restaurant.getRating());
//            dto.setStatus(restaurant.getStatus());
//            dto.setPurchaseCount(restaurant.getPurchaseCount());
//            dto.setReviewCount(restaurant.getReviewCount());
//            return dto;
//        }).toList();
//        return ResponseEntity.ok(new Response<>(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getStatus(), "Top 10 nhà hàng mua nhiều nhất", dtos));
//    }
//
//    @GetMapping("/top-reviewed")
//    public ResponseEntity<Response<List<RestaurantDto>>> getTop10ReviewedRestaurants() {
//        List<RestaurantDto> dtos = restaurantRepository.findTop10ByOrderByReviewCountDesc().stream().map(restaurant -> {
//            RestaurantDto dto = new RestaurantDto();
//            dto.setId(restaurant.getId());
//            dto.setName(restaurant.getName());
//            dto.setAddress(restaurant.getAddress());
//            dto.setPhoneNumber(restaurant.getPhoneNumber());
//            dto.setLogoUrl(restaurant.getLogoUrl());
//            dto.setCoverImageUrl(restaurant.getCoverImageUrl());
//            dto.setDescription(restaurant.getDescription());
//            dto.setRating(restaurant.getRating());
//            dto.setStatus(restaurant.getStatus());
//            dto.setPurchaseCount(restaurant.getPurchaseCount());
//            dto.setReviewCount(restaurant.getReviewCount());
//            return dto;
//        }).toList();
//        return ResponseEntity.ok(new Response<>(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getStatus(), "Top 10 nhà hàng được đánh giá nhiều nhất", dtos));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Response<RestaurantDto>> getRestaurantDetail(@PathVariable Long id) {
//        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
//        if (restaurant == null) {
//            return ResponseEntity.status(404).body(new Response<>(ReturnCode.NOT_FOUND.getCode(), ReturnCode.NOT_FOUND.getStatus(), "Không tìm thấy nhà hàng", null));
//        }
//        RestaurantDto dto = new RestaurantDto();
//        dto.setId(restaurant.getId());
//        dto.setName(restaurant.getName());
//        dto.setAddress(restaurant.getAddress());
//        dto.setPhoneNumber(restaurant.getPhoneNumber());
//        dto.setLogoUrl(restaurant.getLogoUrl());
//        dto.setCoverImageUrl(restaurant.getCoverImageUrl());
//        dto.setDescription(restaurant.getDescription());
//        dto.setRating(restaurant.getRating());
//        dto.setStatus(restaurant.getStatus());
//        dto.setPurchaseCount(restaurant.getPurchaseCount());
//        dto.setReviewCount(restaurant.getReviewCount());
//        return ResponseEntity.ok(new Response<>(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getStatus(), "Lấy chi tiết nhà hàng thành công", dto));
//    }
//
//    @GetMapping("/{id}/reviews")
//    public ResponseEntity<Response<java.util.Map<String, Object>>> getReviewsByRestaurant(
//            @PathVariable Long id,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size,
//            @RequestParam(required = false) Integer rating,
//            @RequestParam(defaultValue = "desc") String sort
//    ) {
//        org.springframework.data.domain.Sort sortObj = sort.equalsIgnoreCase("asc") ? org.springframework.data.domain.Sort.by("createdDate").ascending() : org.springframework.data.domain.Sort.by("createdDate").descending();
//        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, sortObj);
//        org.springframework.data.domain.Page<project.foodflow.entity.Review> reviewPage;
//        if (rating != null) {
//            reviewPage = reviewRepository.findByRestaurantIdAndRating(id, rating, pageable);
//        } else {
//            reviewPage = reviewRepository.findByRestaurantId(id, pageable);
//        }
//        java.util.List<project.foodflow.dto.ReviewDto> dtos = reviewPage.getContent().stream().map(review -> {
//            var dto = new project.foodflow.dto.ReviewDto();
//            dto.setId(review.getId());
//            dto.setUserName(review.getUser().getName());
//            dto.setRating(review.getRating());
//            dto.setComment(review.getComment());
//            dto.setCreatedDate(review.getCreatedDate());
//            return dto;
//        }).toList();
//        java.util.Map<String, Object> result = new java.util.HashMap<>();
//        result.put("total", reviewPage.getTotalElements());
//        result.put("reviews", dtos);
//        return ResponseEntity.ok(new Response<>(project.foodflow.constant.ReturnCode.SUCCESS.getCode(), project.foodflow.constant.ReturnCode.SUCCESS.getStatus(), "Lấy danh sách đánh giá của nhà hàng thành công", result));
//    }
//
//    @PostMapping("/{id}/reviews")
//    public ResponseEntity<Response<project.foodflow.dto.ReviewDto>> createReview(@PathVariable Long id, @RequestBody project.foodflow.dto.ReviewRequest request, @AuthenticationPrincipal User user) {
//        if (request.getRating() == null || request.getRating() < 1 || request.getRating() > 5) {
//            return ResponseEntity.badRequest().body(new Response<>(400, "BAD_REQUEST", "Số sao đánh giá không hợp lệ", null));
//        }
//        project.foodflow.entity.Review review = new project.foodflow.entity.Review();
//        review.setRestaurant(new project.foodflow.entity.Restaurant());
//        review.getRestaurant().setId(id);
//        review.setUser(user);
//        review.setRating(request.getRating());
//        review.setComment(request.getComment());
//        review.setCreatedDate(java.time.LocalDateTime.now());
//        reviewRepository.save(review);
//        project.foodflow.dto.ReviewDto dto = new project.foodflow.dto.ReviewDto();
//        dto.setId(review.getId());
//        dto.setUserName(user.getName());
//        dto.setRating(review.getRating());
//        dto.setComment(review.getComment());
//        dto.setCreatedDate(review.getCreatedDate());
//        return ResponseEntity.ok(new Response<>(project.foodflow.constant.ReturnCode.SUCCESS.getCode(), project.foodflow.constant.ReturnCode.SUCCESS.getStatus(), "Đánh giá thành công", dto));
//    }
//
//    @PutMapping("/reviews/{reviewId}")
//    public ResponseEntity<Response<project.foodflow.dto.ReviewDto>> updateReview(@PathVariable Long reviewId, @RequestBody project.foodflow.dto.ReviewRequest request, @AuthenticationPrincipal project.foodflow.entity.User user) {
//        var reviewOpt = reviewRepository.findById(reviewId);
//        if (reviewOpt.isEmpty()) {
//            return ResponseEntity.status(404).body(new Response<>(404, "NOT_FOUND", "Không tìm thấy đánh giá", null));
//        }
//        var review = reviewOpt.get();
//        if (!review.getUser().getId().equals(user.getId())) {
//            return ResponseEntity.status(403).body(new Response<>(403, "FORBIDDEN", "Bạn không có quyền sửa đánh giá này", null));
//        }
//        if (request.getRating() != null) review.setRating(request.getRating());
//        if (request.getComment() != null) review.setComment(request.getComment());
//        reviewRepository.save(review);
//        project.foodflow.dto.ReviewDto dto = new project.foodflow.dto.ReviewDto();
//        dto.setId(review.getId());
//        dto.setUserName(user.getName());
//        dto.setRating(review.getRating());
//        dto.setComment(review.getComment());
//        dto.setCreatedDate(review.getCreatedDate());
//        return ResponseEntity.ok(new Response<>(200, "SUCCESS", "Cập nhật đánh giá thành công", dto));
//    }
//
//    @DeleteMapping("/reviews/{reviewId}")
//    public ResponseEntity<Response<Void>> deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal project.foodflow.entity.User user) {
//        var reviewOpt = reviewRepository.findById(reviewId);
//        if (reviewOpt.isEmpty()) {
//            return ResponseEntity.status(404).body(new Response<>(404, "NOT_FOUND", "Không tìm thấy đánh giá", null));
//        }
//        var review = reviewOpt.get();
//        if (!review.getUser().getId().equals(user.getId())) {
//            return ResponseEntity.status(403).body(new Response<>(403, "FORBIDDEN", "Bạn không có quyền xóa đánh giá này", null));
//        }
//        reviewRepository.deleteById(reviewId);
//        return ResponseEntity.ok(new Response<>(200, "SUCCESS", "Xóa đánh giá thành công", null));
//    }
//}