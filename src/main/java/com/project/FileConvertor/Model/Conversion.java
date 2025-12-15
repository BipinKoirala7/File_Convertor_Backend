package com.project.FileConvertor.Model;

import java.util.UUID;
import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@ConfigurationProperties("app.file.conversion")
public class Conversion {

    /**
     * You might want to add `userId` for reference to the user.
     * It might be useful when deploying for multiple users to access at the same time.
     * 
     * Also storing file's hash code should also be given consideration.
     */
    
    @NotNull(message = "Conversion Id cannot be null")
    private UUID id;

    @NotNull(message = "Original File Name cannot be null")
    private String originalFileName;

    @NotNull(message = "Extension Type cannot be null")
    private String extensionType;

    private FileConversionStatus status;
}
