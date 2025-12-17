package com.project.FileConvertor.DTOs.Response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RestApiSuccessResponse<T> {
    private int statusCode;
    private String message;
    private boolean success;
    private T data;
}
