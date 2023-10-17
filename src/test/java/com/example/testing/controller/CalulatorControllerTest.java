package com.example.testing.controller;

import com.example.testing.dto.CalculatorDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@Slf4j
class CalulatorControllerTest {

    @InjectMocks
    private CalulatorController calulatorController;

    static Double num1;
    static Double num2;

    @BeforeAll
    static void beforeAll(){

        log.info("Called before all methods.");
        num1 = 2.5;
        num2 = 2.5;
    }

    @AfterAll
    static void afterAll(){

        log.info("Called after all methods.");
        num1 = null;
        num2 = null;
    }

    @Test
    @DisplayName("Test Addition Success Scenario")
    void addShouldReturnAddedDouble_Success() {
        log.info("called test success test.");
        //given
        Double result = calulatorController.add(num1,num2).getBody();
        //then
        Assertions.assertEquals(result, 5);
    }
    @Test
    @DisplayName("Test Addition Failure Scenario")
    void addShouldReturnAddedDouble_Failure() {
        log.info("called test failure test.");
        //given
        Double result = calulatorController.add(num1,num2).getBody();
        //then
        Assertions.assertNotEquals(result, 15);
    }
    @Test
    @DisplayName("Test Addition Null Scenario")
    void addShouldReturnAddedDouble_Failure_Null() {
        log.info("called test failure test.");
        //given
        Double result = calulatorController.add(null,null).getBody();
        //then
        Assertions.assertNotEquals(result, 15);
    }

    @Test
    @DisplayName("Test Subtraction Success for valid params Scenario")
    void subtractShouldReturnSubtracted() {
        log.info("Called Subtract test.");
        //given
        Double result = calulatorController.subtract(num1, num2).getBody();
        //then
        Assertions.assertEquals(result, 0.0);
    }
    @Test
    @DisplayName("Test Subtraction Failure for null params Scenario")
    void subtractShouldReturnSubtracted_nullParam() {
        log.info("Called Subtract test.");
        //given
        Double result = calulatorController.subtract(null, null).getBody();
        //then
        Assertions.assertNotEquals(result, 0.0);
    }

    @Test
    @DisplayName("Test Multiplication Success scenario")
    void multiplySuccess() {
        log.info("Test Multiplication Success Scenario");
        //given
        CalculatorDto calculatorDto = new CalculatorDto(num1, num2);

        //when
        ResponseEntity<Double> result = calulatorController.multiply(calculatorDto);

        //then
        Assertions.assertEquals(result.getBody(), 6.25);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue(),
                "Expecting the status as OK.");

    }
    @Test
    @DisplayName("Test Multiplication Failure scenario")
    void multiplyFailure() {
        log.info("Test Multiplication Failure Scenario");
        //given
        CalculatorDto calculatorDto = new CalculatorDto(num1, num2+1);

        //when
        Double result = calulatorController.multiply(calculatorDto).getBody();

        //then
        Assertions.assertNotEquals(result, 6.25);

    }

    //if a developer changes a code without changing test case the bug is recognized

    //    //not appropiate as it runs evertime
//    @BeforeEach
//    void init(){
//        log.info("Called before each methods.");
//        num1 = 2.5;
//        num2 = 2.5;
//    }
//
//    @AfterEach
//    void destroy(){
//        log.info("Called after each methods.");
//        num1 = null;
//        num2 = null;
//    }
}