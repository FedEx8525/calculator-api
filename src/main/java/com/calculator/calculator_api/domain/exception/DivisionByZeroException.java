package com.calculator.calculator_api.domain.exception;

public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException() {
        super("Cannot divide by zero");
    }
}
