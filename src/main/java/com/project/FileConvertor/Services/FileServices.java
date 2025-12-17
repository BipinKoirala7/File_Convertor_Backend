package com.project.FileConvertor.Services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.FileConvertor.Model.FileStorageProperties;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServices {

    private final LibreOfficeConvertor convertor;
    private final FileStorageProperties fileStorageProperties;
    
    public byte[] convertFile(MultipartFile file){
        if(file.isEmpty()){
            
        }
        if(file.getSize() > fileStorageProperties.getMaxSize()){
            // throw new Error for file being more than allowed maximum size
        }

        try{
            return convertor.convertDocxToPdf(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }

    }

}
