package com.calculator.calculator_api.infrastructure.web.exception;

import com.calculator.calculator_api.domain.exception.DivisionByZeroException;
import com.calculator.calculator_api.domain.exception.NegativeSquareRootException;
import com.calculator.calculator_api.infrastructure.web.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DivisionByZeroException.class)
    public ResponseEntity<ErrorResponse> handelDivisionByZero(
            DivisionByZeroException exception,
            HttpServletRequest request
    ) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(NegativeSquareRootException.class)
    public ResponseEntity<ErrorResponse> handelNegativeSquareRoot(
            NegativeSquareRootException exception,
            HttpServletRequest request
    ) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                message,
                request.getRequestURI()
        );
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus status,
            String message,
            String path
    ) {
        ErrorResponse response = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                path,
                Instant.now()
        );

        return ResponseEntity.status(status).body(response);
    }
}
