package com.project.FileConvertor.Exception;

public class FileNotFoundException extends RuntimeException {
    
    public FileNotFoundException(){
        super("File Not found");
    }
}
