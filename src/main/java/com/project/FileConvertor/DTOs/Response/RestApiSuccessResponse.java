package com.project.FileConvertor.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class RestApiSuccessResponse<T> {
    private Integer statusCode;
    private String message;
    private boolean success;
    private T data;
}
