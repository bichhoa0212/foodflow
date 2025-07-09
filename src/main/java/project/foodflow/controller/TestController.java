package project.foodflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Test API", description = "Simple test endpoints for API testing")
public class TestController {

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
} 