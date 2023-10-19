package com.example.testing.exception;

import com.example.testing.dto.ErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //specifies what http response status should to be sent
    @ResponseBody   //specifies that it should send serialized  http response body
    public ErrorDto handleConstraintViolationException(HttpServletRequest request,
                                                       ConstraintViolationException ex){
        ErrorDto error = new ErrorDto();

         error.setTimestamp(new Date());
         error.setStatus(HttpStatus.BAD_REQUEST.value());

         error.addErrors(ex.getMessage());
         error.setPath(request.getServletPath());

         log.error(ex.getMessage(), ex);

         return error;
    }
}
