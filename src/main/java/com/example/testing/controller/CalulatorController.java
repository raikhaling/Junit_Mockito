package com.example.testing.controller;

import com.example.testing.dto.CalculatorDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/calculator")
@Slf4j
@Tag(name = "Calculator")
public class CalulatorController {

    @GetMapping("/add")
    public ResponseEntity<Double> add(@RequestParam("num1") Double num1,
                      @RequestParam("num2") Double num2){
        if(num1 == null || num2 == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(num1+num2);
    }

    @GetMapping("/subtract")
    public ResponseEntity<Double> subtract(@RequestParam("num1") Double num1,
                      @RequestParam("num2") Double num2){
        if(num1 == null || num2 ==null){
            return ResponseEntity.badRequest().build();
        }
        else{
            return ResponseEntity.ok(num1-num2);
        }

    }
    @PostMapping("/multiply")
    public ResponseEntity<Double> multiply(@RequestBody @Valid CalculatorDto calculatorDto){
        Double result = calculatorDto.getNum1() * calculatorDto.getNum2();
        return ResponseEntity.ok(result);
    }


}
