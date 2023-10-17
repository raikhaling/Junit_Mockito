package com.example.testing.mapper;

import com.example.testing.dto.PropertyDto;
import com.example.testing.entity.Property;

public class PropertyMapper {

    public Property convertDTOtoEntity(PropertyDto propertyDTO){

        Property property = new Property();
        property.setTitle(propertyDTO.getTitle());
        property.setAddress(propertyDTO.getAddress());
        property.setOwnerEmail(propertyDTO.getOwnerEmail());
        property.setOwnerName(propertyDTO.getOwnerName());
        property.setPrice(propertyDTO.getPrice());
        property.setDescription(propertyDTO.getDescription());

        return property;
    }

    public PropertyDto convertEntityToDTO(Property propertyEntity){

        PropertyDto propertyDTO =  new PropertyDto();
        propertyDTO.setId(propertyEntity.getId());
        propertyDTO.setTitle(propertyEntity.getTitle());
        propertyDTO.setAddress(propertyEntity.getAddress());
        propertyDTO.setOwnerEmail(propertyEntity.getOwnerEmail());
        propertyDTO.setOwnerName(propertyEntity.getOwnerName());
        propertyDTO.setPrice(propertyEntity.getPrice());
        propertyDTO.setDescription(propertyEntity.getDescription());

        return propertyDTO;
    }
}

