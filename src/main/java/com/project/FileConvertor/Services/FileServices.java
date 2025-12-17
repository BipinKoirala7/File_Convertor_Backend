package com.project.FileConvertor.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.FileConvertor.Exception.FileNotFoundException;
import com.project.FileConvertor.Exception.FileSizeTooLargeException;
import com.project.FileConvertor.Model.FileStorageProperties;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServices {

    private final LibreOfficeConvertor convertor;
    private final FileStorageProperties fileStorageProperties;
    
    public byte[] convertFile(MultipartFile file){
        if(file.isEmpty()){
            throw new FileNotFoundException();
        }
        if(file.getSize() > fileStorageProperties.getMaxSize()){
            throw new FileSizeTooLargeException();
        }

        Path baseDir = Paths.get(fileStorageProperties.getBaseDir());

        try{
            if(!Files.exists(baseDir)){
                Files.createDirectories(baseDir);
            }

            return convertor.convertDocxToPdf(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }

    }

}
