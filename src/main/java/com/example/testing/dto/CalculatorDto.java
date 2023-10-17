package com.example.testing.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorDto {

    @NotNull
    @Positive
    private Double num1;

    @NotNull
    @Positive
    private Double num2;
}
