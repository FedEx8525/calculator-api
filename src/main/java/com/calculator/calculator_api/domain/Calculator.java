package com.calculator.calculator_api.domain;

import com.calculator.calculator_api.domain.exception.DivisionByZeroException;
import com.calculator.calculator_api.domain.exception.NegativeSquareRootException;

public class Calculator {


    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {

        if (b == 0) {
            throw new DivisionByZeroException();
        }
        return a / b;
    }

    public double modulo(double a, double b) {
        if (b == 0) {
            throw new DivisionByZeroException();
        }
        return a % b;
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public double squareRoot(double value) {
        if (value < 0) {
            throw new NegativeSquareRootException();
        }

        return Math.sqrt(value);
    }

    public double pi() {
        return Math.PI;
    }

    public double euler() {
        return Math.E;
    }
}
