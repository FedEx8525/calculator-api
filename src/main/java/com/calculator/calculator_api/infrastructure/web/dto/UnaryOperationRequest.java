package com.calculator.calculator_api.infrastructure.web.dto;

import jakarta.validation.constraints.NotNull;

public record UnaryOperationRequest(

        @NotNull (message = "The value is required")
        Double value
) {
}
