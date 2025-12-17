package com.project.FileConvertor.Exception;

public class FileSizeTooLargeException extends RuntimeException {
    public FileSizeTooLargeException(){
        super("Given file size is too large");
    }
}
