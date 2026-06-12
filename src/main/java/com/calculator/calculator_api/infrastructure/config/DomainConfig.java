package com.calculator.calculator_api.infrastructure.config;

import com.calculator.calculator_api.domain.Calculator;
import com.calculator.calculator_api.domain.CalculatorMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public Calculator calculator() {
        return new Calculator();
    }

    @Bean
    public CalculatorMemory calculatorMemory() {
        return new CalculatorMemory();
    }
}
