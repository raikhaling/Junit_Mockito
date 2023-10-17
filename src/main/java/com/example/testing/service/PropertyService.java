package com.example.testing.service;

import com.example.testing.dto.PropertyDto;
import com.example.testing.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PropertyService {
    PropertyDto saveProperty(PropertyDto propertyDTO);
    List<PropertyDto> getAllProperties();
    PropertyDto updateProperty(PropertyDto propertyDTO, Long propertyId);
    PropertyDto updatePropertyDescription(@RequestBody PropertyDto propertyDTO, Long propertyId)
            throws BusinessException;
    PropertyDto updatePropertyPrice(@RequestBody PropertyDto propertyDTO, Long propertyId);
    void deleteProperty(Long propertyId);
}
