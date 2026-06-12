package com.calculator.calculator_api.domain.exception;

public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException() {
        super("Cannot divided by zero");
    }
}
