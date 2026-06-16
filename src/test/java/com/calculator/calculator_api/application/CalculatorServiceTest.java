package com.calculator.calculator_api.application;

import com.calculator.calculator_api.domain.Calculator;
import com.calculator.calculator_api.domain.CalculatorMemory;
import com.calculator.calculator_api.domain.exception.DivisionByZeroException;
import com.calculator.calculator_api.domain.exception.NegativeSquareRootException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        Calculator calculator = new Calculator();
        CalculatorMemory memory = new CalculatorMemory();

        calculatorService = new CalculatorService(calculator, memory);
    }

    @Test
    void shouldAddTwoNumbers() {

        double result = calculatorService.add(3.0, 5.0);

        assertEquals(8.0, result);
    }

    @Test
    void shouldSubtractTwoNumbers() {

        double result = calculatorService.subtract(8.0, 3.0);

        assertEquals(5.0, result);
    }

    @Test
    void shouldMultiplyTwoNumbers() {
        double result = calculatorService.multiply(3.0, 5.0);

        assertEquals(15.0, result);
    }

    @Test
    void shouldDivideTwoNumbers() {
        double result = calculatorService.divide(15.0, 3.0);

        assertEquals(5.0, result);
    }

    @Test
    void shouldThrowExceptionWhenDividingByZero() {
        assertThrows(DivisionByZeroException.class, () ->
                calculatorService.divide(5.0, 0.0));
    }

    @Test
    void shouldModuloTwoNumbers() {
        double result = calculatorService.modulo(10.0, 3.0);

        assertEquals(1.0, result);
    }

    @Test
    void shouldThrowExceptionWhenModuloByZero() {
        assertThrows(DivisionByZeroException.class, () ->
                calculatorService.modulo(10.0, 0.0));
    }

    @Test
    void shouldPowerABaseForAnExponent() {
        double result = calculatorService.power(2.0, 3.0);

        assertEquals(8.0, result);
    }

    @Test
    void shouldSquareRootAValue() {
        double result = calculatorService.squareRoot(25.0);

        assertEquals(5.0, result);
    }

    @Test
    void shouldThrowExceptionWhenSquareRootIsNegative() {
        assertThrows(NegativeSquareRootException.class, () ->
                calculatorService.squareRoot(-25.0));
    }

    @Test
    void shouldReturnPi() {
        double result = calculatorService.pi();

        assertEquals(Math.PI, result);
    }

    @Test
    void shouldReturnEuler() {
        double result = calculatorService.euler();

        assertEquals(Math.E, result);
    }

    @Test
    void shouldSaveANumberInMemory() {
        calculatorService.saveInMemory(25.0);

        assertEquals(25.0, calculatorService.readInMemory());
    }

    @Test
    void shouldClearMemory() {
        calculatorService.saveInMemory(50.0);

        calculatorService.clearMemory();

        assertEquals(0.0, calculatorService.readInMemory());
    }

    @Test
    void shouldReturnZeroWhenMemoryIsEmpty() {
        double result = calculatorService.readInMemory();

        assertEquals(0.0, result);
    }
}

