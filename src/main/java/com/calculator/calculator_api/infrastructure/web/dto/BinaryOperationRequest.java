package com.calculator.calculator_api.infrastructure.web.dto;

import jakarta.validation.constraints.NotNull;

public record BinaryOperationRequest(

        @NotNull(message = "The first value is required")
        Double firstValue,

        Double secondValue
) {
}
