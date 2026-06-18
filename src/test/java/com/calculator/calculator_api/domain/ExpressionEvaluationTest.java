package com.calculator.calculator_api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionEvaluationTest {

    private ExpressionEvaluator expressionEvaluation;

    @BeforeEach
    void setUp() {
        Calculator calculator = new Calculator();
        expressionEvaluation = new ExpressionEvaluator(calculator);
    }

    @Test
    void shouldEvaluateExpressionRespectingOperatorPrecedence() {
        double result = expressionEvaluation.evaluate(
                "13 + 25 +36 + 27 - 32 / 2"
        );

        assertEquals(85.0, result);
    }

    @Test
    void shouldEvaluateParenthesesBeforeOtherOperations() {
        double result = expressionEvaluation.evaluate(
                "(2 + 3) * 4"
        );

        assertEquals(20.0, result);
    }

    @Test
    void shouldEvaluateNestedParentheses() {
        double result = expressionEvaluation.evaluate(
                "2 * (3 + (4 * 5))");
        assertEquals(46.0, result);
    }

    @Test
    void shouldEvaluateModuloWithCorrectPrecedence() {
        double result = expressionEvaluation.evaluate(
                "10 + 7 % 4"
        );

        assertEquals(13.0, result);
    }
}
