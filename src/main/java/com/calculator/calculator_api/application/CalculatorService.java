package com.calculator.calculator_api.application;

import com.calculator.calculator_api.domain.Calculator;
import com.calculator.calculator_api.domain.CalculatorMemory;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private final Calculator calculator;
    private final CalculatorMemory memory;

    public CalculatorService(Calculator calculator, CalculatorMemory memory) {
        this.calculator = calculator;
        this.memory = memory;
    }

    public double add(double a, double b) {
        return calculator.add(a, b);
    }

    public double subtract(double a, double b) {
        return calculator.subtract(a, b);
    }

    public double multiply(double a, double b) {
        return calculator.multiply(a, b);
    }

    public double divide(double a, double b) {
        return calculator.divide(a, b);
    }

    public double modulo(double a, double b) {
        return calculator.modulo(a, b);
    }

    public double power(double base, double exponent) {
        return calculator.power(base, exponent);
    }

    public double squareRoot(double value) {
        return calculator.squareRoot(value);
    }

    public double pi() {
        return calculator.pi();
    }

    public double euler() {
        return calculator.euler();
    }

    public void saveInMemory(double value) {
        memory.save(value);
    }

    public double readInMemory() {
        return memory.read();
    }

    public void clearMemory() {
        memory.clear();
    }
}
