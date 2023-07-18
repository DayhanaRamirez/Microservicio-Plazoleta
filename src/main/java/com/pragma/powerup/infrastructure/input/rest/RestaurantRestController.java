package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RestaurantPaginationRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantUpdateRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantPaginationResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Add a new object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Object created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Object already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto, @RequestHeader("Authorization") String authHeader) {
        restaurantHandler.saveRestaurant(restaurantRequestDto, authHeader);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all objects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All objects returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RestaurantResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(restaurantHandler.getAllRestaurants(authHeader));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> getRestaurant(@PathVariable("id") long id, @RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(restaurantHandler.getRestaurant(id, authHeader));
    }

    @PutMapping
    public ResponseEntity<Void> updateRestaurant(@Valid @RequestBody RestaurantUpdateRequestDto restaurantUpdateRequestDto, @RequestHeader("Authorization") String authHeader){
        restaurantHandler.updateRestaurant(restaurantUpdateRequestDto, authHeader);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") Long id, @RequestHeader("Authorization") String authHeader){
        restaurantHandler.deleteRestaurant(id, authHeader);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pageAndSort")
    public ResponseEntity<List<RestaurantPaginationResponseDto>> getAllRestaurantsByName(@Valid @RequestBody RestaurantPaginationRequestDto restaurantPaginationRequestDto) {
        return ResponseEntity.ok(restaurantHandler.getAllRestaurantsByName(restaurantPaginationRequestDto));
    }


}