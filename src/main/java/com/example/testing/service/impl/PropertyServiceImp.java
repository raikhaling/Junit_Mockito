package com.example.testing.service.impl;

import com.example.testing.dto.PropertyDto;
import com.example.testing.entity.Property;
import com.example.testing.exception.BusinessException;
import com.example.testing.mapper.PropertyMapper;
import com.example.testing.repository.PropertyRepository;
import com.example.testing.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyServiceImp implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper mapper;

    @Override
    public PropertyDto saveProperty(PropertyDto propertyDTO) {
        try{
            Property property = mapper.convertDTOtoEntity(propertyDTO);
            propertyRepository.save(property);
            return mapper.convertEntityToDTO(property);
        }catch (Exception e){
            log.error("Failed to save property: "+e.getMessage());
            throw new BusinessException("Failed to save property: "+e.getMessage());
        }
    }

    @Override
    public List<PropertyDto> getAllProperties() {
        try {
            List<Property> propertyList = propertyRepository.findAll();
            return propertyList.stream()
                    .map(property -> mapper.convertEntityToDTO(property))
                    .toList();
        }catch (Exception e){
            log.error("Failed to get all the Property: "+e.getMessage());
            throw new BusinessException("Failed to get all the properties: "+e.getMessage());
        }
    }

    @Override
    public PropertyDto updateProperty(PropertyDto propertyDTO, Long propertyId) {
        try {
            Property property = propertyRepository.findById(propertyId)
                    .orElseThrow(()-> new BusinessException("Property not found with id: "+propertyId));
            property.setTitle(propertyDTO.getTitle());
            property.setAddress(propertyDTO.getAddress());
            property.setOwnerEmail(propertyDTO.getOwnerEmail());
            property.setOwnerName(propertyDTO.getOwnerName());
            property.setPrice(propertyDTO.getPrice());
            property.setDescription(propertyDTO.getDescription());
            propertyRepository.save(property);

            return mapper.convertEntityToDTO(property);

        }catch (Exception e){
            log.error("Failed to update a property: "+e.getMessage());
            throw new BusinessException("Failed to update a property: "+e.getMessage());
        }
    }

    @Override
    public PropertyDto updatePropertyDescription(PropertyDto propertyDTO, Long propertyId) throws BusinessException {
        try {
            Property property = propertyRepository.findById(propertyId)
                    .orElseThrow(()-> new BusinessException("Property not found with id: "+propertyId));
            property.setDescription(propertyDTO.getDescription());
            propertyRepository.save(property);

            return mapper.convertEntityToDTO(property);

        }catch (Exception e){
            log.error("Failed to update description of a property: "+e.getMessage());
            throw new BusinessException("Failed to update a description of a property: "+e.getMessage());
        }
    }

    @Override
    public PropertyDto updatePropertyPrice(PropertyDto propertyDTO, Long propertyId) {
        try {
            Property property = propertyRepository.findById(propertyId)
                    .orElseThrow(()-> new BusinessException("Property not found with id: "+propertyId));
            property.setPrice(propertyDTO.getPrice());
            propertyRepository.save(property);

            return mapper.convertEntityToDTO(property);

        }catch (Exception e){
            log.error("Failed to update description of a property: "+e.getMessage());
            throw new BusinessException("Failed to update a description pf a property: "+e.getMessage());
        }
    }

    @Override
    public void deleteProperty(Long propertyId) {
        try {
            Property property = propertyRepository.findById(propertyId)
                    .orElseThrow(()-> new BusinessException("Property not found with id: "+propertyId));
            propertyRepository.delete(property);

        }catch (Exception e){

            log.error("Failed to delete property: "+e.getMessage());
            throw new BusinessException("Failed to delete a property: "+e.getMessage());
        }

    }
}
