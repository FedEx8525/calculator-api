package com.calculator.calculator_api.infrastructure.web;


import com.calculator.calculator_api.application.CalculatorService;
import com.calculator.calculator_api.domain.exception.DivisionByZeroException;
import com.calculator.calculator_api.infrastructure.web.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorController.class)
@Import(GlobalExceptionHandler.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CalculatorService calculatorService;

    @Test
    void shouldAddTwoNumbers() throws Exception {
        when(calculatorService.add(5.0, 3.0))
                .thenReturn(8.0);

        mockMvc.perform(post("/api/calculator/add")
                .contentType("application/json")
                .content("""
                        {
                          "firstValue": 5.0,
                          "secondValue": 3.0
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.result").value(8.0));
    }

    @Test
    void shouldReturnBadRequestWhenSecondValueIsMissing() throws Exception {
        mockMvc.perform(post("/api/calculator/add")
                        .contentType("application/json")
                        .content("""
                            {
                              "firstValue": 5.0
                            }
                            """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message")
                        .value("secondValue: The second value is required"))
                .andExpect(jsonPath("$.path")
                        .value("/api/calculator/add"));
    }

    @Test
    void shouldReturnBadRequestWhenDividingByZero() throws Exception {
        when(calculatorService.divide(10.0, 0.0))
                .thenThrow(new DivisionByZeroException());

        mockMvc.perform(post("/api/calculator/divide")
                .contentType("application/json")
                .content("""
                        {
                          "firstValue": 10.0,
                          "secondValue": 0.0
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message")
                        .value("Cannot divide by zero"))
                .andExpect(jsonPath("$.path")
                        .value("/api/calculator/divide"));
    }

    @Test
    void shouldSquareRootANumber() throws Exception {
        when(calculatorService.squareRoot(25.0))
                .thenReturn(5.0);

        mockMvc.perform(post("/api/calculator/square-root")
                .contentType("application/json")
                .content("""
                        {
                          "value": 25.0
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.result").value(5.0));
    }

    @Test
    void shouldReturnBadRequestWhenSquareRootValueIsMissing() throws Exception {
        mockMvc.perform(post("/api/calculator/square-root")
                .contentType("application/json")
                .content("""
                        {
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message")
                        .value("value: The value is required"))
                .andExpect(jsonPath("$.path")
                        .value("/api/calculator/square-root"));
    }

    @Test
    void shouldReturnPi() throws Exception {
        when(calculatorService.pi())
                .thenReturn(Math.PI);

        mockMvc.perform(get("/api/calculator/pi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(Math.PI));
    }


}
