package com.calculator.calculator_api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorMemoryTest {

    private CalculatorMemory memory;

    @BeforeEach
    void setUp() {
        memory = new CalculatorMemory();
    }

    @Test
    void shouldSaveValueInTheMemory() {

        memory.save(25.0);

        assertEquals(25.0, memory.read());
    }

    @Test
    void shouldReturnZeroWhenMemoryIsEmpty() {

        assertEquals(0.0, memory.read());
    }

    @Test
    void shouldCleanMemory() {

        memory.save(50.0);

        memory.clear();

        assertEquals(0.0, memory.read());
    }
}
