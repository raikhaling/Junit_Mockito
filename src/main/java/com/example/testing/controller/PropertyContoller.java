package com.example.testing.controller;

import com.example.testing.dto.PropertyDto;
import com.example.testing.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Property")
@Validated
public class PropertyContoller {

    private final PropertyService propertyService;

    @Operation(
            description = "This is a simple hello endpoint",
            summary = "This is a summary for hello endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                      description = "Unauthorized",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @Operation(
            description = "This is a post endpoint for a property",
            summary = "This endpoint lets you post a property",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PostMapping("/properties")
    public ResponseEntity<PropertyDto> saveProperty(@RequestBody @Valid PropertyDto propertyDTO){

        propertyDTO = propertyService.saveProperty(propertyDTO);

        return new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
    }

    @Operation(
            description = "This is a get endpoint for property",
            summary = "This endpoint list all the properties"
    )
    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDto>> getAllProperties(
            @RequestParam(required = false,defaultValue = "1") @Positive int page,
            @RequestParam(required = false, defaultValue = "5") @Min(5) @Max(10) int size
    ){

        List<PropertyDto> propertyList = propertyService.getAllProperties(page, size);

        return new ResponseEntity<>(propertyList,HttpStatus.OK);
    }

    @Operation(
            description = "This is a put endpoint for property",
            summary = "This endpoint updates a property"
    )
    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDto> updateProperty(@RequestBody @Valid PropertyDto propertyDTO,
                                                      @PathVariable @Positive Long propertyId){
        propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);

        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }


    @Operation(
            description = "This is a patch endpoint for property",
            summary = "This endpoint updates description of a property"
    )
    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDto> updatePropertyDescription(
            @RequestBody @Valid PropertyDto propertyDTO,
            @PathVariable @Positive Long propertyId){

        propertyDTO = propertyService.updatePropertyDescription(propertyDTO, propertyId);

        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }


    @Operation(
            description = "This is a patch endpoint for property",
            summary = "This endpoint updates price of a property"
    )
    @PatchMapping("/properties/update-price/{propertyId}")
    public ResponseEntity<PropertyDto> updatePropertyPrice(
            @RequestBody @Valid PropertyDto propertyDTO,
            @PathVariable @Positive Long propertyId){

        propertyDTO = propertyService.updatePropertyPrice(propertyDTO, propertyId);

        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }


    @Operation(
            description = "This is a delete endpoint for property",
            summary = "This endpoint deletes a property"
    )
    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<Void> deleteProperty(
            @PathVariable @Positive(message = "Property id must be greater than 0") Long propertyId){

        propertyService.deleteProperty(propertyId);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


}
