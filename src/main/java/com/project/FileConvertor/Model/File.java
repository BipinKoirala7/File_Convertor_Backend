package com.project.FileConvertor.Model;

import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class File {
    @NotNull(message = "Conversion Id cannot be null")
    private UUID id;

    @NotNull(message = "Original File Name cannot be null")
    private String originalFileName;

    @Max(value = 50, message = "File cannot be larger than 50MB")
    private Integer size;

    @NotNull(message = "Extension Type cannot be null")
    private String extensionType;

    @NotNull(message = "File storage location is very essential")
    FileStorageProperties storageProperties;
}
