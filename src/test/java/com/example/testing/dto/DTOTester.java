package com.example.testing.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meanbean.test.BeanTester;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DTOTester {

    @Test
    @DisplayName("Tests all DTO'S getters setters")
    void testAllDTOs(){
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(CalculatorDto.class);
        beanTester.testBean(PropertyDto.class);
    }
}
