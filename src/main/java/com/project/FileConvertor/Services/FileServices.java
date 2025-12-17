package com.project.FileConvertor.Services;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.FileConvertor.Model.FileStorageProperties;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServices {

    private final FileStorageProperties fileStorageProperties;
    
    public void convertFile(MultipartFile file){
        if(file.isEmpty()){
            // throw new Error for file being empty
        }
        if(file.getSize() > fileStorageProperties.getMaxSize()){
            // throw new Error for file being more than allowed maximum size
        }

        // Save the file
        saveFile(file);
        
    }

    public void saveFile(MultipartFile file){
        Path rootPath = Paths.get(URI.create(fileStorageProperties.getBaseDir()));
        try{
            if(!Files.exists(rootPath)){
                Files.createDirectory(rootPath);
            }

            // Copy the file in the temp folder
            Files.copy(file.getInputStream(), rootPath.resolve(file.getOriginalFilename()));
        } catch(IOException e){
            // throw needed error
        }
    }
}
