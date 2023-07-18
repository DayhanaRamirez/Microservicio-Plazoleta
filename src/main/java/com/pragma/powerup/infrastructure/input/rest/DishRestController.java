package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.DishAvailabilityDto;
import com.pragma.powerup.application.dto.request.DishPaginationRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.response.DishPaginationResponseDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("dish")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;

    @Operation(summary = "Add a new object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Object created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Object already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveDish(@Valid @RequestBody DishRequestDto dishRequestDto, @RequestHeader("Authorization") String authHeader) {
        dishHandler.saveDish(dishRequestDto, authHeader);
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
    public ResponseEntity<List<DishResponseDto>> getAllDishes(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(dishHandler.getAllDishes(authHeader));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponseDto> getDish(@PathVariable("id") long id, @RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(dishHandler.getDish(id, authHeader));
    }

    @PutMapping
    public ResponseEntity<Void> updateDish(@Valid @RequestBody DishUpdateRequestDto dishUpdateRequestDto, @RequestHeader("Authorization") String authHeader){
        dishHandler.updateDish(dishUpdateRequestDto, authHeader);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateDish")
    public ResponseEntity<Void> updateDishAvailability(@Valid @RequestBody DishAvailabilityDto dishAvailabilityDto, @RequestHeader("Authorization") String authHeader){
        dishHandler.updateDishAvailability(dishAvailabilityDto, authHeader);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable("id") Long id, @RequestHeader("Authorization") String authHeader){
        dishHandler.deleteDish(id, authHeader);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pageAndSort")
    public ResponseEntity<List<DishPaginationResponseDto>> getAllDishesByCategory(@Valid @RequestBody DishPaginationRequestDto dishPaginationRequestDto) {
        return ResponseEntity.ok(dishHandler.getAllDishesByCategory(dishPaginationRequestDto));
    }


}
