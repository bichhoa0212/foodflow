package project.foodflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.foodflow.entity.Role;
import project.foodflow.service.UserRoleService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import project.foodflow.entity.UserRole;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Test API", description = "Simple test endpoints for API testing")
public class TestController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Check if the API is running")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "API is healthy",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"status\": \"UP\", \"timestamp\": \"2024-01-01T12:00:00\"}")))
    })
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        response.put("message", "FoodFlow API is running");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/echo/{message}")
    @Operation(summary = "Echo message", description = "Echo back the provided message")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Message echoed successfully",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"message\": \"Hello World\", \"timestamp\": \"2024-01-01T12:00:00\"}")))
    })
    public ResponseEntity<Map<String, Object>> echo(
            @PathVariable String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/data")
    @Operation(summary = "Test POST with data", description = "Test POST endpoint with JSON data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data received successfully",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Map.class)))
    })
    public ResponseEntity<Map<String, Object>> testPost(
            @RequestBody Map<String, Object> data) {
        Map<String, Object> response = new HashMap<>();
        response.put("received", data);
        response.put("timestamp", LocalDateTime.now());
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/error-test")
    @Operation(summary = "Test error response", description = "Test endpoint that returns an error")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request error",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"This is a test error\", \"code\": 400}")))
    })
    public ResponseEntity<Map<String, Object>> testError() {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "This is a test error");
        error.put("code", 400);
        error.put("timestamp", LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }

    @GetMapping("/debug/user-roles/{userId}")
    @Operation(summary = "Debug user roles", description = "Debug endpoint to test user roles functionality")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User roles retrieved successfully",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Map.class)))
    })
    public ResponseEntity<Map<String, Object>> debugUserRoles(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Check if user has any roles
            long roleCount = userRoleService.countUserRolesByUserId(userId);
            response.put("userId", userId);
            response.put("roleCount", roleCount);
            
            if (roleCount > 0) {
                // Test original query
                List<UserRole> userRolesOriginal = userRoleService.findByUserIdWithRole(userId);
                response.put("originalQueryCount", userRolesOriginal.size());
                response.put("originalQueryRoles", userRolesOriginal.stream()
                    .map(ur -> Map.of(
                        "id", ur.getId(),
                        "userId", ur.getUserId(),
                        "roleId", ur.getRoleId(),
                        "role", ur.getRole() != null ? ur.getRole().getName() : "NULL",
                        "status", ur.getStatus()
                    ))
                    .collect(java.util.stream.Collectors.toList()));
                
                // Test alternative query
                List<UserRole> userRolesAlternative = userRoleService.findByUserIdWithRoleAndUser(userId);
                response.put("alternativeQueryCount", userRolesAlternative.size());
                response.put("alternativeQueryRoles", userRolesAlternative.stream()
                    .map(ur -> Map.of(
                        "id", ur.getId(),
                        "userId", ur.getUserId(),
                        "roleId", ur.getRoleId(),
                        "role", ur.getRole() != null ? ur.getRole().getName() : "NULL",
                        "user", ur.getUser() != null ? ur.getUser().getName() : "NULL",
                        "status", ur.getStatus()
                    ))
                    .collect(java.util.stream.Collectors.toList()));
                
                // Test native query
                List<Object[]> userRolesNative = userRoleService.findByUserIdWithRoleNative(userId);
                response.put("nativeQueryCount", userRolesNative.size());
                response.put("nativeQueryResults", userRolesNative.stream()
                    .map(row -> Map.of(
                        "userRoleId", row[0],
                        "userId", row[1],
                        "roleId", row[2],
                        "roleName", row[8] != null ? row[8] : "NULL",
                        "roleDescription", row[9] != null ? row[9] : "NULL"
                    ))
                    .collect(java.util.stream.Collectors.toList()));
                
                // Try to get user roles
                List<Role> roles = userRoleService.getUserRoles(userId);
                response.put("finalRoles", roles.stream()
                    .map(role -> Map.of(
                        "id", role.getId(),
                        "name", role.getName(),
                        "description", role.getDescription()
                    ))
                    .collect(java.util.stream.Collectors.toList()));
                response.put("finalRolesCount", roles.size());
                response.put("status", "success");
            } else {
                response.put("originalQueryCount", 0);
                response.put("alternativeQueryCount", 0);
                response.put("finalRolesCount", 0);
                response.put("status", "no_roles_found");
            }
            
        } catch (Exception e) {
            response.put("error", e.getMessage());
            response.put("status", "error");
            response.put("exception", e.getClass().getSimpleName());
            e.printStackTrace();
        }
        
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
} 