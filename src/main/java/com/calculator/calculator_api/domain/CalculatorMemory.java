package com.calculator.calculator_api.domain;

public class CalculatorMemory {
    private double storedValue;

    public void save(double value) {
        this.storedValue = value;
    }

    public double read() {
        return storedValue;
    }

    public void clear() {
        this.storedValue = 0;
    }
}
