package com.project.FileConvertor.Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Represents a conversion of file from one type to another.
 * This class holds info for converting a file.
 * 
 * @author Bipin Koirala
 * @version 1.1
 * @since 2025-12-15
 */
@Data
@ConfigurationProperties("app.file.conversion")
public class Conversion {

    /**
     * You might want to add `userId` for reference to the user.
     * It might be useful when deploying for multiple users to access at the same time.
     * 
     * Also storing file's hash code should also be given consideration.
     */
    
    // Unique identifier for each conversion
    @NotNull(message = "Conversion Id cannot be null")
    private UUID id;

    // Source file id that is to be converted.
    @NotNull(message = "Source file Id cannot be null")
    private UUID sourceFileId;

    // File id of converted file.
    private UUID outputFileId;

    // Target file type for output file.
    @NotNull(message = "Target file type should be declared")
    private String targetFileType;

    // Time taken to convert the file
    private Duration duration;

    // Local Date and time of file conversion
    @NotNull(message = "Time when the conversion started cannot be null")
    private LocalDateTime localDateTime;

    // File converstion status.
    @NotNull(message = "File conversion status cannot be null")
    private FileConversionStatus status;
}
