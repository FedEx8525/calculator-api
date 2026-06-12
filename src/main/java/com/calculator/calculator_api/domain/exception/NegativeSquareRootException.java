package com.calculator.calculator_api.domain.exception;

public class NegativeSquareRootException extends RuntimeException {
    public NegativeSquareRootException() {
        super("Cannot calculate square root of a negative number");
    }
}
