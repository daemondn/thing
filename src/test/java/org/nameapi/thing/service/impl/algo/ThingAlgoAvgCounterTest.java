package org.nameapi.thing.service.impl.algo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nameapi.thing.service.ThingAlgorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ThingAlgoAvgCounterTest {

    private ThingAlgorithm avgCounter;
    private static int PRECISION = 10;

    @BeforeEach
    void setUp() {
        avgCounter = new ThingAlgoAvgCounter(PRECISION);
    }

    @Test
    void beforeAddNumber() {
        assertEquals(BigDecimal.valueOf(0).setScale(PRECISION, RoundingMode.HALF_UP), avgCounter.getInfo());
    }

    @Test
    void addNull() {
        try {
            avgCounter.addNumber(null);
            assertTrue(false, "No exception on bad arg");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void addSimpleNumber() {
        avgCounter.addNumber(BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(1).setScale(PRECISION, RoundingMode.HALF_UP), avgCounter.getInfo());
    }

    @Test
    void addSomeIntNumbersAsc() {
        avgCounter.addNumber(BigDecimal.valueOf(1));
        avgCounter.addNumber(BigDecimal.valueOf(2));
        avgCounter.addNumber(BigDecimal.valueOf(3));
        assertEquals(BigDecimal.valueOf(2).setScale(PRECISION, RoundingMode.HALF_UP), avgCounter.getInfo());
    }

    @Test
    void addSomeIntNumbersDesc() {
        avgCounter.addNumber(BigDecimal.valueOf(3));
        avgCounter.addNumber(BigDecimal.valueOf(2));
        avgCounter.addNumber(BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(2).setScale(PRECISION, RoundingMode.HALF_UP), avgCounter.getInfo());
    }

    @Test
    void addSomeIntNumbersRand() {
        avgCounter.addNumber(BigDecimal.valueOf(3));
        avgCounter.addNumber(BigDecimal.valueOf(2));
        avgCounter.addNumber(BigDecimal.valueOf(1));
        avgCounter.addNumber(BigDecimal.valueOf(5));
        avgCounter.addNumber(BigDecimal.valueOf(4));
        assertEquals(BigDecimal.valueOf(3).setScale(PRECISION, RoundingMode.HALF_UP), avgCounter.getInfo());
    }

    @Test
    void addSomeDecNumbersRand() {
        avgCounter.addNumber(BigDecimal.valueOf(3.1));
        avgCounter.addNumber(BigDecimal.valueOf(2.5));
        avgCounter.addNumber(BigDecimal.valueOf(1.9));
        avgCounter.addNumber(BigDecimal.valueOf(5.7));
        avgCounter.addNumber(BigDecimal.valueOf(4.3));
        assertEquals(BigDecimal.valueOf(3.5).setScale(PRECISION, RoundingMode.HALF_UP), avgCounter.getInfo());
    }

    @Test
    void addSomeNegDecNumbersRand() {
        avgCounter.addNumber(BigDecimal.valueOf(-3.1));
        avgCounter.addNumber(BigDecimal.valueOf(2.5));
        avgCounter.addNumber(BigDecimal.valueOf(1.9));
        avgCounter.addNumber(BigDecimal.valueOf(4.3));
        avgCounter.addNumber(BigDecimal.valueOf(-5.7));
        assertEquals(BigDecimal.valueOf(-0.02).setScale(PRECISION, RoundingMode.HALF_UP), avgCounter.getInfo());
    }

}