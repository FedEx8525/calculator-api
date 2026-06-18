package com.calculator.calculator_api.domain;

import com.calculator.calculator_api.domain.exception.InvalidExpressionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionEvaluationTest {

    private ExpressionEvaluator expressionEvaluator;

    @BeforeEach
    void setUp() {
        Calculator calculator = new Calculator();
        expressionEvaluator = new ExpressionEvaluator(calculator);
    }

    @Test
    void shouldEvaluateExpressionRespectingOperatorPrecedence() {
        double result = expressionEvaluator.evaluate(
                "13 + 25 +36 + 27 - 32 / 2"
        );

        assertEquals(85.0, result);
    }

    @Test
    void shouldEvaluateParenthesesBeforeOtherOperations() {
        double result = expressionEvaluator.evaluate(
                "(2 + 3) * 4"
        );

        assertEquals(20.0, result);
    }

    @Test
    void shouldEvaluateNestedParentheses() {
        double result = expressionEvaluator.evaluate(
                "2 * (3 + (4 * 5))");

        assertEquals(46.0, result);
    }

    @Test
    void shouldEvaluateModuloWithCorrectPrecedence() {
        double result = expressionEvaluator.evaluate(
                "10 + 7 % 4"
        );

        assertEquals(13.0, result);
    }

    @Test
    void shouldEvaluatePowerBeforeMultiplication() {
        double result = expressionEvaluator.evaluate(
                "2 ^ 3 * 4"
        );

        assertEquals(32.0, result);
    }

    @Test
    void shouldEvaluatePowerFromRightToLeft() {
        double result = expressionEvaluator.evaluate(
                "2 ^ 3 ^ 2"
        );

        assertEquals(512.0, result);
    }

    @Test
    void shouldEvaluateExpressionStartingWithNegativeNumber() {
        double result = expressionEvaluator.evaluate(
                "- 5 + 10"
        );

        assertEquals(5.0, result);
    }

    @Test
    void shouldEvaluateNegativeNumberAfterOperator() {
        double result = expressionEvaluator.evaluate(
                "5 * -2"
        );

        assertEquals(-10.0, result);
    }

    @Test
    void shouldThrowExceptionWhenExpressionIsEmpty() {
        assertThrows(
                InvalidExpressionException.class,
                () -> expressionEvaluation.evaluate("")
        );
    }

    @Test
    void shouldThrowExceptionWhenExpressionIsNull() {
        assertThrows(
                InvalidExpressionException.class,
                () -> expressionEvaluation.evaluate(null)
        );
    }
}
