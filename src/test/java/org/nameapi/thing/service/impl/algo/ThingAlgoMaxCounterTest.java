package org.nameapi.thing.service.impl.algo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nameapi.thing.service.ThingAlgorithm;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ThingAlgoMaxCounterTest {

    private ThingAlgorithm maxCounter;

    @BeforeEach
    void setUp() {
        maxCounter = new ThingAlgoMaxCounter();
    }

    @Test
    void beforeAddNumber() {
        assertNull(maxCounter.getInfo());
    }

    @Test
    void addNull() {
        try {
            maxCounter.addNumber(null);
            assertTrue(false, "No exception on bad arg");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void addSimpleNumber() {
        maxCounter.addNumber(BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(1), maxCounter.getInfo());
    }

    @Test
    void addSomeIntNumbersAsc() {
        maxCounter.addNumber(BigDecimal.valueOf(1));
        maxCounter.addNumber(BigDecimal.valueOf(2));
        maxCounter.addNumber(BigDecimal.valueOf(3));
        assertEquals(BigDecimal.valueOf(3), maxCounter.getInfo());
    }

    @Test
    void addSomeIntNumbersDesc() {
        maxCounter.addNumber(BigDecimal.valueOf(3));
        maxCounter.addNumber(BigDecimal.valueOf(2));
        maxCounter.addNumber(BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(3), maxCounter.getInfo());
    }

    @Test
    void addSomeIntNumbersRand() {
        maxCounter.addNumber(BigDecimal.valueOf(3));
        maxCounter.addNumber(BigDecimal.valueOf(2));
        maxCounter.addNumber(BigDecimal.valueOf(1));
        maxCounter.addNumber(BigDecimal.valueOf(5));
        maxCounter.addNumber(BigDecimal.valueOf(4));
        assertEquals(BigDecimal.valueOf(5), maxCounter.getInfo());
    }

    @Test
    void addSomeDecNumbersRand() {
        maxCounter.addNumber(BigDecimal.valueOf(3.1));
        maxCounter.addNumber(BigDecimal.valueOf(2.5));
        maxCounter.addNumber(BigDecimal.valueOf(1.9));
        maxCounter.addNumber(BigDecimal.valueOf(5.7));
        maxCounter.addNumber(BigDecimal.valueOf(4.3));
        assertEquals(BigDecimal.valueOf(5.7), maxCounter.getInfo());
    }

    @Test
    void addSomeNegDecNumbersRand() {
        maxCounter.addNumber(BigDecimal.valueOf(-3.1));
        maxCounter.addNumber(BigDecimal.valueOf(2.5));
        maxCounter.addNumber(BigDecimal.valueOf(1.9));
        maxCounter.addNumber(BigDecimal.valueOf(4.3));
        maxCounter.addNumber(BigDecimal.valueOf(-5.7));
        assertEquals(BigDecimal.valueOf(4.3), maxCounter.getInfo());
    }

}