package com.project.FileConvertor.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @PostMapping("/upload")
    public void uploadFile(MultipartFile file){
        
    }
}
