package com.project.FileConvertor.Model;

/**
 * Represents status of a File conversion process.
 * It contains different stages of a file converison.
 */
public enum FileConversionStatus {
    PENDING,
    UPLOADING,
    PROCESSING,
    COMPLETED,
    FAILED
}
