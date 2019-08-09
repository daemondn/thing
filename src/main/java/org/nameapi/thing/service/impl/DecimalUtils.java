package org.nameapi.thing.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Helper methods to work with BigDecimal numbers.
 */
public final class DecimalUtils {

    /**
     * Hide default constructor to prevent instance creation.
     */
    private DecimalUtils() {

    }

    /**
     * Make BigDecimal with specified scale.
     * @param f - input double number
     * @param precision - scale precision
     * @return - BigDecimal with specified scale
     */
    public static BigDecimal makeScaledBigDecimal(final double f,
                                                  final int precision) {
        return BigDecimal.valueOf(f).setScale(precision, RoundingMode.HALF_UP);
    }
}
