package com.project.FileConvertor.Model;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("app.file.convertor")
public class FileStorageProperties {

    // We can add other properties as needed in the porject

    private String baseDir;
    private Integer maxSize;
}
