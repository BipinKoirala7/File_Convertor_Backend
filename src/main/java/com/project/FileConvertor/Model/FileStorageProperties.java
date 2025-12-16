package com.project.FileConvertor.Model;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Represents file properties from yaml.
 * This holds file storage info like base directory, maximum file size, etc.
 */
@Data
@ConfigurationProperties("app.file.convertor")
public class FileStorageProperties {

    // We can add other properties as needed in the porject

    // Base directory of the folder where file is stored.
    private String baseDir;

    // Max Size of a file that can be stored.
    private Long maxSize;
}
