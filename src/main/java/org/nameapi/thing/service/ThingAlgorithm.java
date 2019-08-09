package org.nameapi.thing.service;

import java.math.BigDecimal;

/**
 * Algorithm interface.
 */
public interface ThingAlgorithm {

    /**
     * Add number to sequence and recalculate algorithm.
     * @param number - number to add to sequence
     */
    void addNumber(BigDecimal number);

    /**
     * Get current algorithm state.
     * @return current algorithm value
     */
    BigDecimal getInfo();
}
