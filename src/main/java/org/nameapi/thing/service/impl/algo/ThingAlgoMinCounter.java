package org.nameapi.thing.service.impl.algo;

import org.nameapi.thing.service.ThingAlgorithm;
import java.math.BigDecimal;

/**
 * Minimum counter algorithm.
 */
public class ThingAlgoMinCounter implements ThingAlgorithm {

    /**
     * Current minimum value.
     */
    private BigDecimal minValue = null;

    /**
     * Constructor.
     */
    public ThingAlgoMinCounter() {

    }

    /**
     * Add number to sequence and recalculate minimum value.
     * @param number - number to add to sequence
     */
    public final void addNumber(final BigDecimal number) {
        if (number != null) {
            if (minValue != null) {
                minValue = minValue.min(number);
            } else {
                minValue = number;
            }
        } else {
            throw new IllegalArgumentException("Input number cannot be null");
        }
    }

    /**
     * Get current algorithm state.
     * @return current minimum value
     */
    public final BigDecimal getInfo() {
        return minValue;
    }
}
