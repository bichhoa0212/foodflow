// package project.foodflow.controller;

// import lombok.RequiredArgsConstructor;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import project.foodflow.service.UserAuditService;

// @RestController
// @RequestMapping("/api/user-audit")
// @RequiredArgsConstructor
// public class UserAuditController {

//     private final UserAuditService userAuditService;

//     /**
//      * Lấy thông tin user hiện tại - không cần Authentication parameter
//      */
//     @GetMapping("/current-user")
//     public ResponseEntity<String> getCurrentUser() {
//         String userInfo = userAuditService.getCurrentUserInfo();
//         return ResponseEntity.ok(userInfo);
//     }

//     /**
//      * Tạo audit log - không cần Authentication parameter
//      */
//     @PostMapping("/audit-log")
//     public ResponseEntity<String> createAuditLog(@RequestParam String action, @RequestParam String details) {
//         userAuditService.createAuditLog(action, details);
//         return ResponseEntity.ok("Audit log created successfully");
//     }

//     /**
//      * Kiểm tra quyền thực hiện action - không cần Authentication parameter
//      */
//     @GetMapping("/can-perform")
//     public ResponseEntity<String> checkPermission(@RequestParam String action) {
//         boolean canPerform = userAuditService.canPerformAction(action);
//         return ResponseEntity.ok("Can perform action '" + action + "': " + canPerform);
//     }

//     /**
//      * Lấy userId hiện tại - không cần Authentication parameter
//      */
//     @GetMapping("/user-id")
//     public ResponseEntity<String> getCurrentUserId() {
//         String userId = userAuditService.getCurrentUserId();
//         return ResponseEntity.ok("Current User ID: " + userId);
//     }

//     /**
//      * Lấy email hiện tại - không cần Authentication parameter
//      */
//     @GetMapping("/email")
//     public ResponseEntity<String> getCurrentEmail() {
//         String email = userAuditService.getCurrentEmail();
//         return ResponseEntity.ok("Current Email: " + email);
//     }

//     /**
//      * Lấy username hiện tại - không cần Authentication parameter
//      */
//     @GetMapping("/username")
//     public ResponseEntity<String> getCurrentUsername() {
//         String username = userAuditService.getCurrentUsername();
//         return ResponseEntity.ok("Current Username: " + username);
//     }

//     /**
//      * Kiểm tra trạng thái đăng nhập - không cần Authentication parameter
//      */
//     @GetMapping("/login-status")
//     public ResponseEntity<String> getLoginStatus() {
//         boolean isLoggedIn = userAuditService.isUserLoggedIn();
//         return ResponseEntity.ok("User logged in: " + isLoggedIn);
//     }

//     /**
//      * Thực hiện action với audit log tự động - không cần Authentication parameter
//      */
//     @PostMapping("/perform-action")
//     public ResponseEntity<String> performAction(@RequestParam String action, @RequestParam String details) {
//         // Kiểm tra quyền
//         if (!userAuditService.canPerformAction(action)) {
//             return ResponseEntity.badRequest().body("Permission denied for action: " + action);
//         }

//         // Tạo audit log trước khi thực hiện action
//         userAuditService.createAuditLog("BEFORE_" + action, "Starting action: " + details);

//         // Thực hiện action (giả lập)
//         String result = "Action '" + action + "' completed successfully";

//         // Tạo audit log sau khi thực hiện action
//         userAuditService.createAuditLog("AFTER_" + action, "Completed action: " + details);

//         return ResponseEntity.ok(result);
//     }
// } 