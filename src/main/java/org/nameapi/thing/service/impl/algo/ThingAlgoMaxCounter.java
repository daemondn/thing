package org.nameapi.thing.service.impl.algo;

import org.nameapi.thing.service.ThingAlgorithm;
import java.math.BigDecimal;

/**
 * Maximum counter algorithm.
 */
public class ThingAlgoMaxCounter implements ThingAlgorithm {

    /**
     * Current maximum value.
     */
    private BigDecimal maxValue = null;

    /**
     * Constructor.
     */
    public ThingAlgoMaxCounter() {

    }

    /**
     * Add number to sequence and recalculate maximum value.
     * @param number - number to add to sequence
     */
    public final void addNumber(final BigDecimal number) {
        if (number != null) {
            if (maxValue != null) {
                maxValue = maxValue.max(number);
            } else {
                maxValue = number;
            }
        } else {
            throw new IllegalArgumentException("Input number cannot be null");
        }
    }

    /**
     * Get current algorithm state.
     * @return current maximum value
     */
    public final BigDecimal getInfo() {
        return maxValue;
    }
}
