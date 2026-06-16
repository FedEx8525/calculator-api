package com.calculator.calculator_api.infrastructure.web;

import com.calculator.calculator_api.application.CalculatorService;
import com.calculator.calculator_api.infrastructure.web.dto.BinaryOperationRequest;
import com.calculator.calculator_api.infrastructure.web.dto.CalculatorResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;


    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/add")
    public ResponseEntity<CalculatorResponse> add(
            @Valid @RequestBody BinaryOperationRequest request
    ) {
        double result = calculatorService.add(
                request.firstValue(),
                request.secondValue()
        );
        return ResponseEntity.ok(new CalculatorResponse(result));
    }

    @PostMapping("/subtract")
    public ResponseEntity<CalculatorResponse> subtract(
            @Valid @RequestBody BinaryOperationRequest request
    ) {
        double result = calculatorService.subtract(
                request.firstValue(),
                request.secondValue()
        );
        return ResponseEntity.ok(new CalculatorResponse(result));
    }

    @PostMapping("/multiply")
    public ResponseEntity<CalculatorResponse> multiply(
            @Valid @RequestBody BinaryOperationRequest request
    ) {
        double result = calculatorService.multiply(
                request.firstValue(),
                request.secondValue()
        );
        return ResponseEntity.ok(new CalculatorResponse(result));
    }

    @PostMapping("/divide")
    public ResponseEntity<CalculatorResponse> divide(
            @Valid @RequestBody BinaryOperationRequest request
    ) {
        double result = calculatorService.divide(
                request.firstValue(),
                request.secondValue()
        );
        return ResponseEntity.ok(new CalculatorResponse(result));
    }

    @PostMapping("/modulo")
    public ResponseEntity<CalculatorResponse> modulo(
            @Valid @RequestBody BinaryOperationRequest request
    ) {
        double result = calculatorService.modulo(
                request.firstValue(),
                request.secondValue()
        );
        return ResponseEntity.ok(new CalculatorResponse(result));
    }



}