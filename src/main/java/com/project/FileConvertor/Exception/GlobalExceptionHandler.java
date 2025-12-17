package com.project.FileConvertor.Exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.FileConvertor.DTOs.Response.RestApiErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(FileSizeTooLargeException.class)
    public ResponseEntity<RestApiErrorResponse> handleFileSizeTooLargeException(FileSizeTooLargeException e){
        return ResponseEntity
        .status(HttpStatus.NOT_ACCEPTABLE.value())
        .body(
            RestApiErrorResponse
            .builder()
            .statusCode(HttpStatus.NOT_ACCEPTABLE.value())
            .message(e.getMessage())
            .build()
        );
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<RestApiErrorResponse> handleFileNotFoundException(FileNotFoundException e){
        return ResponseEntity
        .status(HttpStatus.BAD_REQUEST.value())
        .body(
            RestApiErrorResponse
            .builder()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .message(e.getMessage())
            .build()
        );
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<RestApiErrorResponse> handleIOExpection(IOException e){
        return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .body(
            RestApiErrorResponse
            .builder()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(e.getMessage())
            .build()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RestApiErrorResponse> handleGeneralException(RuntimeException e){
        return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .body(
            RestApiErrorResponse
            .builder()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(e.getMessage())
            .build()
        );
    }
    
}
