package org.nameapi.thing.service.impl.algo;

import org.nameapi.thing.service.ThingAlgorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Average counter algorithm.
 */
public class ThingAlgoAvgCounter implements ThingAlgorithm {

    /**
     * Current average value.
     */
    private BigDecimal avgValue;

    /**
     * Number of values in sequence.
     */
    private BigDecimal numValues;

    /**
     * Work precision.
     */
    private int precision;

    /**
     * Hide constructor to disable class creation without params.
     */
    private ThingAlgoAvgCounter() {

    }

    /**
     * Constructor.
     * @param aprecision - calculations precision
     */
    public ThingAlgoAvgCounter(final int aprecision) {
        this.precision = aprecision;
        avgValue = BigDecimal.valueOf(0).setScale(precision,
                RoundingMode.HALF_UP);
        numValues = BigDecimal.valueOf(0).setScale(precision,
                RoundingMode.HALF_UP);
    }

    /**
     * Add number to sequence and recalculate average.
     * @param number - number to add to sequence
     */
    public final void addNumber(final BigDecimal number) {
        if (number != null) {
            BigDecimal numValuesPrev = numValues;
            numValues = numValues.add(BigDecimal.valueOf(1));
            avgValue = avgValue
                    .multiply(numValuesPrev)
                    .add(number)
                    .divide(numValues, precision, BigDecimal.ROUND_HALF_UP);
        } else {
            throw new IllegalArgumentException("Input number cannot be null");
        }
    }

    /**
     * Get current algorithm state.
     * @return current average value
     */
    public final BigDecimal getInfo() {
        return avgValue;
    }
}
