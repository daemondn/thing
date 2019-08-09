package org.nameapi.thing.service.impl.algo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nameapi.thing.service.ThingAlgorithm;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ThingAlgoMinCounterTest {

    private ThingAlgorithm minCounter;

    @BeforeEach
    void setUp() {
        minCounter = new ThingAlgoMinCounter();
    }

    @Test
    void beforeAddNumber() {
        assertNull(minCounter.getInfo());
    }

    @Test
    void addNull() {
        try {
            minCounter.addNumber(null);
            assertTrue(false, "No exception on bad arg");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void addSimpleNumber() {
        minCounter.addNumber(BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(1), minCounter.getInfo());
    }

    @Test
    void addSomeIntNumbersAsc() {
        minCounter.addNumber(BigDecimal.valueOf(1));
        minCounter.addNumber(BigDecimal.valueOf(2));
        minCounter.addNumber(BigDecimal.valueOf(3));
        assertEquals(BigDecimal.valueOf(1), minCounter.getInfo());
    }

    @Test
    void addSomeIntNumbersDesc() {
        minCounter.addNumber(BigDecimal.valueOf(3));
        minCounter.addNumber(BigDecimal.valueOf(2));
        minCounter.addNumber(BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(1), minCounter.getInfo());
    }

    @Test
    void addSomeIntNumbersRand() {
        minCounter.addNumber(BigDecimal.valueOf(3));
        minCounter.addNumber(BigDecimal.valueOf(2));
        minCounter.addNumber(BigDecimal.valueOf(1));
        minCounter.addNumber(BigDecimal.valueOf(5));
        assertEquals(BigDecimal.valueOf(1), minCounter.getInfo());
    }

    @Test
    void addSomeDecNumbersRand() {
        minCounter.addNumber(BigDecimal.valueOf(3.1));
        minCounter.addNumber(BigDecimal.valueOf(2.5));
        minCounter.addNumber(BigDecimal.valueOf(1.9));
        minCounter.addNumber(BigDecimal.valueOf(5.7));
        assertEquals(BigDecimal.valueOf(1.9), minCounter.getInfo());
    }

    @Test
    void addSomeNegDecNumbersRand() {
        minCounter.addNumber(BigDecimal.valueOf(-3.1));
        minCounter.addNumber(BigDecimal.valueOf(2.5));
        minCounter.addNumber(BigDecimal.valueOf(1.9));
        minCounter.addNumber(BigDecimal.valueOf(-5.7));
        assertEquals(BigDecimal.valueOf(-5.7), minCounter.getInfo());
    }

    @Test
    void getInfo() {
    }
}