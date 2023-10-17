package com.example.testing.dto;

import lombok.*;

import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorDto {

    @NonNull
    @Positive
    private Double num1;

    @NonNull
    @Positive
    private Double num2;
}
