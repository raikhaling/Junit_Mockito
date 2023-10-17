package com.example.testing.controller;

import com.example.testing.dto.PropertyDto;
import com.example.testing.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PropertyContoller {

    private final PropertyService propertyService;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyDto> saveProperty(@RequestBody PropertyDto propertyDTO){

        propertyDTO = propertyService.saveProperty(propertyDTO);

        return new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDto>> getAllProperties(){

        List<PropertyDto> propertyList = propertyService.getAllProperties();

        return new ResponseEntity<>(propertyList,HttpStatus.OK);
    }
    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDto> updateProperty(@RequestBody PropertyDto propertyDTO,
                                                      @PathVariable Long propertyId){
        propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);

        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }

    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDto> updatePropertyDescription(
            @RequestBody PropertyDto propertyDTO,
            @PathVariable Long propertyId){

        propertyDTO = propertyService.updatePropertyDescription(propertyDTO, propertyId);

        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }
    @PatchMapping("/properties/update-price/{propertyId}")
    public ResponseEntity<PropertyDto> updatePropertyPrice(
            @RequestBody PropertyDto propertyDTO,
            @PathVariable Long propertyId){

        propertyDTO = propertyService.updatePropertyPrice(propertyDTO, propertyId);

        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
    }

    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity deleteProperty(@PathVariable Long propertyId){

        propertyService.deleteProperty(propertyId);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


}
