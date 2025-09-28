package com.alameendev.weatherApi.exceptions;


import com.alameendev.weatherApi.dtos.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.SocketException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = {})
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
        String message;
        HttpStatus status;
        if (ex instanceof InvalidDateRangeException) {
            message = ex.getMessage();
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof InvalidCityNameException) {
            message = ex.getMessage();
            status = HttpStatus.NOT_ACCEPTABLE;
        } else if (ex instanceof SocketException) {
            message = ex.getMessage();
            status = HttpStatus.SERVICE_UNAVAILABLE;
        } else {
            message = "something went wrong";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ErrorResponseDTO response = new ErrorResponseDTO(message, status.value());
        return ResponseEntity.status(status).body(response);
    }
}
