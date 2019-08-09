package org.nameapi.thing.service.impl;

import org.apache.commons.lang3.math.NumberUtils;
import org.nameapi.thing.service.ThingAlgorithm;
import org.nameapi.thing.service.ThingInfoType;
import org.nameapi.thing.service.ThingService;
import org.nameapi.thing.service.impl.algo.ThingAlgoAvgCounter;
import org.nameapi.thing.service.impl.algo.ThingAlgoMaxCounter;
import org.nameapi.thing.service.impl.algo.ThingAlgoMinCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Thing service implementation.
 */
public class ThingServiceImpl implements ThingService {

    /**
     * Logger.
     */
    private static final Logger LOG =
            LoggerFactory.getLogger(ThingServiceImpl.class);

    /**
     * Holder for algorithms.
     */
    private final Map<ThingInfoType, ThingAlgorithm> algorithmMap =
            new HashMap<ThingInfoType, ThingAlgorithm>();

    /**
     * Work precision.
     */
    private int precision;

    /**
     * Hide constructor to disable class creation without params.
     */
    private ThingServiceImpl() {

    }

    /**
     * Constructor.
     * @param aprecision - calculations precision
     */
    public ThingServiceImpl(final int aprecision) {
        this.precision = aprecision;
        initAllAlgos();
    }

    /**
     * Add number to sequence and recalculate all algorithm values.
     * Method is synchonized to prevent errors in concurent usage.
     * @param numberStr - string representation of number to add to sequence
     * @throws IllegalArgumentException in case of null argument or argument
     * that cannot be parsed
     */
    @Override
    public final synchronized void addNumber(final String numberStr) {
        LOG.debug("addNumber called, numberStr: {}", numberStr);
        if (numberStr != null) {
            try {
                BigDecimal number = NumberUtils.toScaledBigDecimal(numberStr,
                        precision, RoundingMode.HALF_UP);
                LOG.debug("Parsed number: {}", number);
                addNumber(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Cannot parse input string");
            }
        } else {
            throw
                 new IllegalArgumentException("Nulls in input are not allowed");
        }
    }

    /**
     * Add number to sequence and recalculate all algorithm values.
     * Method is synchonized to prevent errors in concurent usage.
     * @param number - number to add to sequence
     * @throws IllegalArgumentException in case of null argument
     */
    @Override
    public final synchronized void addNumber(final BigDecimal number) {
        LOG.debug("addNumber called, number: {}", number);
        if (number != null) {
            Stream.of(ThingInfoType.values())
                    .forEach(a -> getAlgo(a).addNumber(number));
        } else {
            throw
                 new IllegalArgumentException("Nulls in input are not allowed");
        }
    }

    /**
     * Get current algorithm state.
     * Method is synchonized to prevent errors in concurent usage.
     * @param infoType - algorithm type
     * @return current algorithm state
     * @throws IllegalArgumentException in case of null argument
     */
    @Override
    public final synchronized
            BigDecimal getThingInfo(final ThingInfoType infoType) {
        LOG.debug("getThingInfo for ThingInfoType: {}", infoType);
        BigDecimal result = getAlgo(infoType).getInfo();
        LOG.debug("getThingInfo result: {}", result);
        return result;
    }

    /**
     * Get algorithm implementation class by type.
     * @param infoType - ThingInfoType
     * @return algorithm implementation class
     * @throws IllegalArgumentException in case of null argument
     */
    private ThingAlgorithm getAlgo(final ThingInfoType infoType) {
        LOG.debug("getAlgo for ThingInfoType: {}", infoType);
        if (infoType != null) {
            ThingAlgorithm result = algorithmMap.get(infoType);
            LOG.debug("getAlgo result: {}", result);
            return result;
        } else {
            throw new IllegalArgumentException("Nulls in infoType input are "
                    + "not allowed");
        }
    }

    /**
     * Algorithms initialization.
     */
    private void initAllAlgos() {
        LOG.debug("initAllAlgos start");
        algorithmMap.put(ThingInfoType.MIN, new ThingAlgoMinCounter());
        algorithmMap.put(ThingInfoType.MAX, new ThingAlgoMaxCounter());
        algorithmMap.put(ThingInfoType.AVERAGE,
                new ThingAlgoAvgCounter(precision));
    }
}
