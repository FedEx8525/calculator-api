package com.calculator.calculator_api.domain;

import java.util.ArrayList;
import java.util.List;

public class ExpressionEvaluator {

    private final Calculator calculator;

    public ExpressionEvaluator(Calculator calculator) {
        this.calculator = calculator;
    }

    public double evaluate(String expression) {
        String normalizedExpression = expression.replaceAll("\\s+", "");

        normalizedExpression = resolveParentheses(normalizedExpression);
        List<String> tokens = tokenize(normalizedExpression);

        resolvePowers(tokens);
        resolveMultiplicationDivisionAndModulo(tokens);

        return resolveAdditionAndSubtraction(tokens);
    }

    private List<String> tokenize(String expression) {

        List<String> tokens = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (int index = 0; index < expression.length(); index++) {
            char character = expression.charAt(index);

            boolean isNegativeSign = character == '-' && (index == 0 || isOperator(expression.charAt(index - 1)));

            if (Character.isDigit(character)
                    || character == '.'
                    || isNegativeSign) {
                number.append(character);
            } else {
                tokens.add(number.toString());
                number.setLength(0);

                tokens.add(String.valueOf(character));
            }
        }
        tokens.add(number.toString());

        return tokens;
    }

    private boolean isOperator(char character) {
        return character == '+'
                || character == '-'
                || character == '*'
                || character == '/'
                || character == '%'
                || character == '^';
    }

    private void resolveMultiplicationDivisionAndModulo(List<String> tokens) {
        int index = 0;

        while (index < tokens.size()) {
            String token = tokens.get(index);

            if (token.equals("*") || token.equals("/") || token.equals("%")) {
                double leftValue = Double.parseDouble(tokens.get(index - 1));
                double rightValue = Double.parseDouble(tokens.get(index + 1));

                double result;

                if (token.equals("*")) {
                    result = calculator.multiply(leftValue, rightValue);
                } else if (token.equals("/")) {
                    result = calculator.divide(leftValue, rightValue);
                } else {
                    result = calculator.modulo(leftValue, rightValue);
                }

                tokens.set(index - 1, String.valueOf(result));
                tokens.remove(index);
                tokens.remove(index);

                index = 0;
            } else {
                index++;
            }
        }
    }

    private double resolveAdditionAndSubtraction(List<String> tokens) {
        double result = Double.parseDouble(tokens.getFirst());

        for (int index = 1; index < tokens.size(); index += 2) {
            String operator = tokens.get(index);
            double value = Double.parseDouble(tokens.get(index + 1));

            if (operator.equals("+")) {
                result = calculator.add(result, value);
            } else if (operator.equals("-")) {
                result = calculator.subtract(result, value);
            }
        }
        return result;
    }

    private String resolveParentheses(String expression) {
        while (expression.contains("(")) {
            int openingIndex = expression.lastIndexOf("(");
            int closingIndex = expression.indexOf(")", openingIndex);

            String innerExpression = expression.substring(
                    openingIndex + 1,
                    closingIndex
            );

            double innerResult = evaluate(innerExpression);

            expression =
                    expression.substring(0, openingIndex)
                            + innerResult
                            + expression.substring(closingIndex + 1);
        }
        return expression;
    }

    private void resolvePowers(List<String> tokens) {
        int index = tokens.size() - 1;

        while (index >= 0) {
            String token = tokens.get(index);

            if (token.equals("^")) {
                double leftValue = Double.parseDouble(tokens.get(index - 1));
                double rightValue = Double.parseDouble(tokens.get(index + 1));

                double result = calculator.power(leftValue, rightValue);

                tokens.set(index - 1, String.valueOf(result));
                tokens.remove(index);
                tokens.remove(index);

                index = tokens.size() - 1;
            } else {
                index--;
            }
        }
    }

}
