package com.project.FileConvertor.Model;

import java.time.Duration;
import java.time.LocalDateTime;
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

    @NotNull(message = "Source file Id cannot be null")
    private UUID sourceFileId;

    private UUID outputFileId;

    @NotNull(message = "Target file type should be declared")
    private String targetFileType;

    private Duration duration;

    @NotNull(message = "Time when the conversion started cannot be null")
    private LocalDateTime localDateTime;

    @NotNull(message = "File conversion status cannot be null")
    private FileConversionStatus status;
}
