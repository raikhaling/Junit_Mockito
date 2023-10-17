package com.example.testing.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
