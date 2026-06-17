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
}
