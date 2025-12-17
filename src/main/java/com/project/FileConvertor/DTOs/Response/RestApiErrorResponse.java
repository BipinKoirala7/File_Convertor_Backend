package com.project.FileConvertor.DTOs.Response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RestApiErrorResponse {
    private int statusCode;
    private String message;
}
