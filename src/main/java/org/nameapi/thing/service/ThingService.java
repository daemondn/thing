package org.nameapi.thing.service;

import java.math.BigDecimal;

/**
 * Thing service interface.
 */
public interface ThingService {
    /**
     * Add number to sequence and recalculate all algorithm values.
     * @param numberStr - string representation of number to add to sequence
     * @throws IllegalArgumentException in case of null argument or argument
     * that cannot be parsed
     */
    void addNumber(String numberStr);

    /**
     * Add number to sequence and recalculate all algorithm values.
     * @param number - number to add to sequence
     * @throws IllegalArgumentException in case of null argument
     */
    void addNumber(BigDecimal number);

    /**
     * Get current algorithm state.
     * @param infoType - algorithm type
     * @return current algorithm state
     * @throws IllegalArgumentException in case of null argument
     */
    BigDecimal getThingInfo(ThingInfoType infoType);
}
