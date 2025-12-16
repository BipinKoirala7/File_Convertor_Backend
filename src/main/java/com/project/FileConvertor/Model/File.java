package com.project.FileConvertor.Model;

import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Represents a file send by User for conversion.
 * This contains all the attributes an uploaded file would have.
 * 
 * @author Bipin Koirala
 * @version 1.1
 * @since 2025-12-15
 */
@Data
public class File {

    // Unique Identifier of a file.
    @NotNull(message = "Conversion Id cannot be null")
    private UUID id;

    // User's Unique Identifier assigned to each file.
    @NotNull(message = "User Id cannot be null")
    private UUID userdId;

    // Original file name of file at the time of upload.
    @NotNull(message = "Original File Name cannot be null")
    private String originalFileName;

    // Extension type of uploaded file (docx,pptx, etc.)
    @NotNull(message = "Extension Type cannot be null")
    private String extensionType;

    // These two properties might not be needed as we can access the static File Storage properties anywhere.

    // // Size of file in bytes. (Max <= 50MB)
    // @Max(value = 52428800, message = "File cannot be larger than 50MB")
    // private Long size;

    // // Reference to FileStorageProperties which holds all file storage properties.
    // @NotNull(message = "File storage location is very essential")
    // FileStorageProperties storageProperties;
}
