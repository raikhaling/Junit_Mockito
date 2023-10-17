package com.example.testing.service;

import com.example.testing.dto.PropertyDto;
import com.example.testing.entity.Property;
import com.example.testing.exception.BusinessException;
import com.example.testing.mapper.PropertyMapper;
import com.example.testing.repository.PropertyRepository;
import com.example.testing.service.impl.PropertyServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class PropertyServiceTest {
    @InjectMocks
    private PropertyServiceImp propertyServiceImp;
    @Mock
    private PropertyRepository propertyRepository;
    @Mock
    private PropertyMapper propertyMapper;

    @Test
    @DisplayName("Test success case for saving a property")
    void testSaveProperty_Success(){
        log.info("Running test save property case.");
        //given
        PropertyDto dto = new PropertyDto();
        dto.setTitle("dummy");

        Property property = new Property();
        property.setTitle("Dummy");

        Property savedEntity = new Property();
        savedEntity.setTitle("Dummy");
        savedEntity.setId(1L);

        PropertyDto savedDto = new PropertyDto();
        savedDto.setTitle("Dummy");
        savedDto.setId(1L);

        //when
        Mockito.when(propertyMapper.convertDTOtoEntity(Mockito.any()))
                .thenReturn(property);
        Mockito.when(propertyRepository.save(Mockito.any()))
                .thenReturn(savedEntity);
        Mockito.when(propertyMapper.convertEntityToDTO(Mockito.any()))
                .thenReturn(savedDto);
        PropertyDto results = propertyServiceImp.saveProperty(dto);

        //then
        assertEquals(savedDto.getId(), results.getId());
        log.info("Test save property passed.");
    }


    @Test
    @DisplayName("Test success for getting all the properties")
    void getAllProperties_Success() {
        log.info("Running getting all properties test:");

        Property property =Property.builder()
                .id(1L)
                .title("Dummy")
                .build();
        List<Property> properties = List.of(property);

        PropertyDto savedDto = PropertyDto.builder()
                .title("Dummy")
                .id(1L)
                .build();


        //when
        Mockito.when(propertyRepository.findAll()).thenReturn(properties);
        Mockito.when(propertyMapper.convertEntityToDTO(Mockito.any()))
                .thenReturn(savedDto);
        List<PropertyDto> results = propertyServiceImp.getAllProperties();

        assertEquals(1, results.size());

    }
    @Test
    @DisplayName("Test Success case for updating property ")
    void updateProperty_Success(){
        log.info("Running update property success case");

        Property property = Property.builder()
                .id(1L)
                .title("Dummy")
                .build();
        PropertyDto savedDto = PropertyDto.builder()
                .id(1L)
                .title("Dummy")
                .build();


        //when
        Mockito.when(propertyRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(property));
        Mockito.when(propertyMapper.convertEntityToDTO(Mockito.any()))
                .thenReturn(savedDto);
        PropertyDto updated = propertyServiceImp.updateProperty(savedDto, 1L);

        assertEquals(updated.getId(), savedDto.getId());
        assertEquals(updated.getTitle(), savedDto.getTitle());
        log.info("Test update property passed.");
    }
    @Test
    @DisplayName("Test Success case for updating description")
    void updatePropertyDescription_Success(){
        log.info("Running updating description success case:");

        PropertyDto savedDto = PropertyDto.builder()
                .id(1L)
                .description("Dummy")
                .build();

        Property property = Property
                .builder()
                .id(1L)
                .description("Dummy").build();

        //when
        Mockito.when(propertyRepository.findById(Mockito.any()))
                        .thenReturn(Optional.ofNullable(property));
        Mockito.when(propertyMapper.convertEntityToDTO(Mockito.any()))
                        .thenReturn(savedDto);

        PropertyDto updatedProperty = propertyServiceImp.updatePropertyDescription(savedDto, 1L);

        //then
        assertEquals(updatedProperty.getDescription(), savedDto.getDescription());
        log.info("Test update description passed.");
    }
    @Test
    @DisplayName("Test Failure for updating description ")
    void updatePropertyDescription_Failure(){
        log.info("Running Failure case for updating description");
        PropertyDto savedDto = new PropertyDto();
        System.out.println(savedDto);
        log.info(""+savedDto);

        Mockito.when(propertyRepository.findById(Mockito.any()))
                .thenReturn(Optional.empty());
        BusinessException exception = assertThrows(BusinessException.class, ()->{
            PropertyDto updatedProertDto = propertyServiceImp.updatePropertyDescription(savedDto, null);
        });
        assertEquals("Failed to update a description of a property: Property not found with id: null",
                exception.getMessage());
        log.info("Failure case for updating description passed");
    }

}