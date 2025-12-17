package com.project.FileConvertor.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.FileConvertor.Services.FileServices;
import com.project.FileConvertor.Services.LibreOfficeConvertor;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RestController(value = "files")
@ResponseBody
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

    @PostMapping("convert")
    public ResponseEntity<?> convertFile(@RequestParam MultipartFile file) {

        byte[] pdfBytes = fileServices.convertFile(file);

        String originalFilename = file.getOriginalFilename();
        String pdfFilename = originalFilename.replaceAll("(?i)\\\\.docx$", ".pdf");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", pdfFilename);

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
