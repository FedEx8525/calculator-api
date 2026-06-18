package com.calculator.calculator_api.domain.exception;

public class InvalidExpressionException extends RuntimeException {
    public InvalidExpressionException(String message) {
        super("Invalid mathematical expression");
    }
}
