package com.example.testing.controller;

import com.example.testing.dto.PropertyDto;
import com.example.testing.service.PropertyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class PropertyContollerTest {
    @InjectMocks
    private PropertyContoller propertyContoller;//Mockito is going to create a proxy object of PropertyContoller and inject it to PropertyContollerTest
    @Mock
    private PropertyService propertyService; ///Mockito will give memory to PropertyService and it will inject this dummy/proxy PropertyService object inside the proxy/dummy object of PropertyController

    @Test
    @DisplayName("Test Hello Success Scenario")
    void sayHello_Success() {
        log.info("Running the 'sayHello_Success' test.");
        //given
        String result = propertyContoller.sayHello();
        //then
        Assertions.assertEquals(result, "Hello");
        log.info("'sayHello' method returned: '{}'", result);
    }

    @Test
    @DisplayName("Test Success Scenario for Saving new Property")
    void testSaveProperty_Success() {
        log.info("Running the testSaveProperty test");
        //given
        PropertyDto dto = new PropertyDto();
        dto.setTitle("Dummy Property");

        PropertyDto savedProperty = new PropertyDto();
        savedProperty.setId(1L);
        savedProperty.setTitle(dto.getTitle());

        ////Do not make the actual call for propertyService.saveProperty(dto) inside controller rather return dummy object savedProperty Mockito.when(propertyService.saveProperty(dto)).thenReturn(savedProperty);
        Mockito.when(propertyService.saveProperty(dto))
                .thenReturn(savedProperty);

        ResponseEntity<PropertyDto> responseEntity = propertyContoller.saveProperty(dto);

        //then
        Assertions.assertNotNull(responseEntity.getBody().getId());
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        log.info("Test testSaveProperty passed. Property ID: {}", responseEntity.getBody().getId());
    }

    @Test
    @DisplayName("Test Success for GettingAllProperties")
    void getAllProperties_Success() {
        log.info("Running getAllProperties test.");
        //given
        PropertyDto dto1 = new PropertyDto();
        dto1.setId(1L);
        dto1.setTitle("Dummy dto one");

        PropertyDto dto2 = new PropertyDto();
        dto1.setId(2L);
        dto1.setTitle("Dummy dto two");

        List<PropertyDto> savedDtos = List.of(dto1, dto2);

        //when
        Mockito.when(propertyService.getAllProperties())
                .thenReturn(savedDtos);

        //then
        ResponseEntity<List<PropertyDto>> listResponseEntity = propertyContoller.getAllProperties();

        Assertions.assertEquals(2, listResponseEntity.getBody().size());
        Assertions.assertEquals(HttpStatus.OK.value(), listResponseEntity.getStatusCodeValue());
        log.info("Test getAllProperties passed. ");
    }

    @Test
    @DisplayName("Test Success for update Property Description")
    void updatePropertyDescription() {
        log.info("Running updatePropertyDescription started. ");
        //given
        PropertyDto newDto = new PropertyDto();
        newDto.setDescription("new description");

        //when
        Mockito.when(propertyService.updatePropertyDescription(Mockito.any(), Mockito.anyLong()))
                .thenReturn(newDto);
        ResponseEntity<PropertyDto> responseEntity = propertyContoller
                .updatePropertyDescription(newDto, 1L);

        //then
        Assertions.assertEquals("new description", newDto.getDescription());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        log.info("Test updating Description successfully passed.");

    }

    @Test
    @DisplayName("Test Success for update Property Price")
    void updatePropertyPrice() {
        log.info("Running updatePropertyPrice started. ");
        //given
        PropertyDto dto = new PropertyDto();
        dto.setPrice(1000.00);

        //when
        Mockito.when(propertyService.updatePropertyPrice(Mockito.any(), Mockito.anyLong()))
                .thenReturn(dto);
        ResponseEntity<PropertyDto> responseEntity = propertyContoller.updatePropertyPrice(dto, 1L);

        //then
        Assertions.assertEquals(1000.00, responseEntity.getBody().getPrice());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        log.info("Test updatePropertyPrice passed successfully");

    }

    @Test
    @Disabled
    @DisplayName("Test Success for delete Property Price")
    void deleteProperty() {
        log.info("Running deleteProperty test.");
        PropertyDto dto = new PropertyDto();

        //when


        log.info("Test deleteProperty passed successfully.");

    }
}
