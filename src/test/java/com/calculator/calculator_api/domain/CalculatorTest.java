package com.calculator.calculator_api.domain;

import com.calculator.calculator_api.domain.exception.DivisionByZeroException;
import com.calculator.calculator_api.domain.exception.NegativeSquareRootException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }


    @Test
    void shouldAddTwoNumbers() {

        double result = calculator.add(5, 3);

        assertEquals(8, result);
    }

    @Test
    void shouldSubtractTwoNumbers() {

        double result = calculator.subtract(8, 3);

        assertEquals(5, result);
    }

    @Test
    void shouldMultiplyTwoNumbers() {

        double result = calculator.multiply(5, 3);

        assertEquals(15, result);
    }

    @Test
    void shouldDivideTwoNumbers() {

        double result = calculator.divide(15, 3);

        assertEquals(5, result);
    }

    @Test
    void shouldThrowExceptionWhenDividingByZero() {

        assertThrows(DivisionByZeroException.class, () ->
                calculator.divide(5, 0));
    }

    @Test
    void shouldCalculateModulo() {

        double result = calculator.modulo(10, 3);

        assertEquals(1, result);
    }

    @Test
    void shouldThrowExceptionWhenModuloByZero() {

        assertThrows(DivisionByZeroException.class, () ->
                calculator.modulo(10, 0));
    }

    @Test
    void shouldCalculatePower() {

        double result = calculator.power(2 , 3);

        assertEquals(8, result);
    }

    @Test
    void shouldCalculateSquareRoot() {

        double result = calculator.squareRoot(25);

        assertEquals(5, result);
    }

    @Test
    void shouldThrowExceptionWhenSquareRootIsNegative() {

        assertThrows(NegativeSquareRootException.class, () ->
                calculator.squareRoot(-25));
    }

    @Test
    void shouldReturnPiConstant() {

        double result = calculator.pi();

        assertEquals(Math.PI, result);
    }

    @Test
    void shouldReturnEulerConstant() {

        double result = calculator.euler();

        assertEquals(Math.E, result);
    }
}
