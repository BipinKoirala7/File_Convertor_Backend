package com.project.FileConvertor.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.FileConvertor.DTOs.Response.RestApiSuccessResponse;
import com.project.FileConvertor.Services.FileServices;
import com.project.FileConvertor.Services.LibreOfficeConvertor;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileServices fileServices;
    private final LibreOfficeConvertor libreOfficeConvertor;

    @PostConstruct
    public void init() {
        if (!libreOfficeConvertor.isLibreOfficeAvailable()) {
            System.err.println("WARNING: LibreOffice is not available! Conversions will fail.");
            System.err.println("Please install LibreOffice and configure the path in application.properties");
        } else {
            System.out.println("LibreOffice detected and ready for conversions");
        }
    }

    @PostMapping("/convert")
    public ResponseEntity<RestApiSuccessResponse<byte[]>> convertFile(@RequestParam MultipartFile file){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                RestApiSuccessResponse
                .<byte[]>builder()
                .statusCode(HttpStatus.OK.value())
                .message("File successfully converted")
                .data(fileServices.convertFile(file))
                .build());
    }
}
