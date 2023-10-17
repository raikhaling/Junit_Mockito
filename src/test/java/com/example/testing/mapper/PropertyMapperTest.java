package com.example.testing.mapper;

import com.example.testing.dto.PropertyDto;
import com.example.testing.entity.Property;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class PropertyMapperTest {
    @InjectMocks
    private PropertyMapper propertyMapper;


    @Test
    @DisplayName("Test Success scenario for converting dto to entity")
    void convertDTOtoEntity_Success() {
        log.info("Running dto to entity conversion test.");
        //given
        PropertyDto dto = new PropertyDto();
        dto.setTitle("Dummy");
        dto.setPrice(1000.00);

        //when
        Property property = propertyMapper.convertDTOtoEntity(dto);

        //then
        Assertions.assertEquals(dto.getTitle(), property.getTitle());
        Assertions.assertEquals(dto.getPrice(), property.getPrice());
        log.info("dto to entity conversion test passed.");
    }

    @Test
    @DisplayName("Test Success scenario for converting entity to dto")
    void convertEntityToDTO() {
        log.info("Running entity to dto conversion test.");

        Property property = new Property();
        property.setTitle("Dummy");
        property.setPrice(1000.00);

        PropertyDto propertyDto = propertyMapper.convertEntityToDTO(property);

        Assertions.assertEquals(property.getTitle(), propertyDto.getTitle());
        Assertions.assertEquals(property.getPrice(), propertyDto.getPrice());
        log.info("entity to dto conversion test passed.");
    }
}
