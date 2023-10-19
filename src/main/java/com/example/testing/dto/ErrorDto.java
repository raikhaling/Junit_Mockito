package com.example.testing.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ErrorDto {
    private Date timestamp;
    private int status;
    private String path;
    private List<String> errors = new ArrayList<>();

    public void addErrors(String message){
        this.errors.add(message);
    }

}
