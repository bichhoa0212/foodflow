package project.foodflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.foodflow.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants")
@Tag(name = "Restaurant Management", description = "APIs for managing restaurants")
public class RestaurantController {

    private final AtomicLong idCounter = new AtomicLong();
    private final List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantController() {
        // Initialize with some sample data
        restaurants.add(new Restaurant(idCounter.incrementAndGet(), "Pizza Palace", "Best pizza in town", "123 Main St, City, State", "+1234567890", 4.5, "Italian"));
        restaurants.add(new Restaurant(idCounter.incrementAndGet(), "Sushi Master", "Fresh sushi and sashimi", "456 Oak Ave, City, State", "+0987654321", 4.8, "Japanese"));
        restaurants.add(new Restaurant(idCounter.incrementAndGet(), "Burger House", "Juicy burgers and fries", "789 Pine Rd, City, State", "+1122334455", 4.2, "American"));
    }

    @GetMapping
    @Operation(summary = "Get all restaurants", description = "Retrieve a list of all restaurants in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved restaurants",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Restaurant.class)))
    })
    public ResponseEntity<List<Restaurant>> getAllRestaurants(
            @Parameter(description = "Filter by cuisine type", example = "Italian")
            @RequestParam(required = false) String cuisine,
            @Parameter(description = "Filter by minimum rating", example = "4.0")
            @RequestParam(required = false) Double minRating) {
        
        List<Restaurant> filteredRestaurants = restaurants.stream()
                .filter(restaurant -> cuisine == null || restaurant.getCuisine().equalsIgnoreCase(cuisine))
                .filter(restaurant -> minRating == null || restaurant.getRating() >= minRating)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(filteredRestaurants);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant by ID", description = "Retrieve a specific restaurant by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Restaurant found",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Restaurant.class))),
        @ApiResponse(responseCode = "404", description = "Restaurant not found",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"message\": \"Restaurant not found\"}")))
    })
    public ResponseEntity<Restaurant> getRestaurantById(
            @Parameter(description = "ID of the restaurant to retrieve", example = "1")
            @PathVariable Long id) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new restaurant", description = "Create a new restaurant in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Restaurant created successfully",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Restaurant.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"message\": \"Invalid input data\"}")))
    })
    public ResponseEntity<Restaurant> createRestaurant(
            @Parameter(description = "Restaurant object to create", required = true)
            @RequestBody Restaurant restaurant) {
        restaurant.setId(idCounter.incrementAndGet());
        restaurants.add(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update restaurant", description = "Update an existing restaurant's information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Restaurant updated successfully",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Restaurant.class))),
        @ApiResponse(responseCode = "404", description = "Restaurant not found",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"message\": \"Restaurant not found\"}")))
    })
    public ResponseEntity<Restaurant> updateRestaurant(
            @Parameter(description = "ID of the restaurant to update", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated restaurant object", required = true)
            @RequestBody Restaurant restaurant) {
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getId().equals(id)) {
                restaurant.setId(id);
                restaurants.set(i, restaurant);
                return ResponseEntity.ok(restaurant);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete restaurant", description = "Delete a restaurant from the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Restaurant deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Restaurant not found",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"message\": \"Restaurant not found\"}")))
    })
    public ResponseEntity<Void> deleteRestaurant(
            @Parameter(description = "ID of the restaurant to delete", example = "1")
            @PathVariable Long id) {
        boolean removed = restaurants.removeIf(restaurant -> restaurant.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search restaurants", description = "Search restaurants by name or description")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search results found",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Restaurant.class)))
    })
    public ResponseEntity<List<Restaurant>> searchRestaurants(
            @Parameter(description = "Search term for restaurant name or description", example = "pizza")
            @RequestParam String query) {
        
        List<Restaurant> searchResults = restaurants.stream()
                .filter(restaurant -> restaurant.getName().toLowerCase().contains(query.toLowerCase()) ||
                        restaurant.getDescription().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(searchResults);
    }
} 