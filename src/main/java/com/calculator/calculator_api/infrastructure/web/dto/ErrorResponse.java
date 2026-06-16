package com.calculator.calculator_api.infrastructure.web.dto;

import java.time.Instant;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String path,
        Instant timestamp
) {
}
