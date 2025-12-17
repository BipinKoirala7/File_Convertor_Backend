package com.project.FileConvertor.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.FileConvertor.DTOs.Response.RestApiErrorResponse;
import com.project.FileConvertor.Services.FileServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileServices fileServices;

    @PostMapping("/convert")
    public ResponseEntity<RestApiErrorResponse> convertFile(@RequestParam MultipartFile file){
        fileServices.convertFile(file);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                RestApiErrorResponse
                .builder()
                .statusCode(HttpStatus.OK.value())
                .message("File successfully converted")
                .build());
    }
}
