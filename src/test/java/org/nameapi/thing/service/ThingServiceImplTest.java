package org.nameapi.thing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nameapi.thing.service.impl.ThingServiceImpl;
import org.nameapi.thing.service.impl.DecimalUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class ThingServiceImplTest {

    private static int PRECISION = 10;
    private ThingService service;

    @BeforeEach
    void setUp() {
        service = new ThingServiceImpl(PRECISION);
    }

    @Test
    void getInstance() {
        assertNotNull(service, "Service from singleton must not be null");
    }

    @Test
    void emptySequence() {
        assertNull(service.getThingInfo(ThingInfoType.MIN), "Min inital value must be null");
        assertNull(service.getThingInfo(ThingInfoType.MAX), "Max inital value must be null");
        assertEquals(BigDecimal.valueOf(0).setScale(PRECISION, RoundingMode.HALF_UP), service.getThingInfo(ThingInfoType.AVERAGE),  "Avg inital value must be 0");
    }

    @Test
    void addNull() {
        try {
            service.addNumber((BigDecimal) null);
            assertTrue(false, "No exception on null input");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void addNullString() {
        try {
            service.addNumber((String) null);
            assertTrue(false, "No exception on null input");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void getNullResult() {
        try {
            service.getThingInfo(null);
            assertTrue(false, "No exception on null input");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void addBadStrings() {
        try {
            service.addNumber("asdfgh");
            assertTrue(false, "No exception on bad string");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            service.addNumber("123a5");
            assertTrue(false, "No exception on bad string");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            service.addNumber("1,5");
            assertTrue(false, "No exception on bad string");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            service.addNumber("1.5,6");
            assertTrue(false, "No exception on bad string");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            service.addNumber("-3,5");
            assertTrue(false, "No exception on bad string");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            service.addNumber("-2.1,5");
            assertTrue(false, "No exception on bad string");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void addNumbers() {
        service.addNumber(BigDecimal.valueOf(1));
        assertEquals(BigDecimal.valueOf(1), service.getThingInfo(ThingInfoType.MIN), "Min value incorrect");
        assertEquals(BigDecimal.valueOf(1), service.getThingInfo(ThingInfoType.MAX), "Max value incorrect");
        assertEquals(BigDecimal.valueOf(1).setScale(PRECISION, RoundingMode.HALF_UP), service.getThingInfo(ThingInfoType.AVERAGE), "AVG value incorrect");
        service.addNumber(BigDecimal.valueOf(2));
        service.addNumber(BigDecimal.valueOf(3));
        service.addNumber(BigDecimal.valueOf(4));
        assertEquals(BigDecimal.valueOf(1), service.getThingInfo(ThingInfoType.MIN), "Min value incorrect");
        assertEquals(BigDecimal.valueOf(4), service.getThingInfo(ThingInfoType.MAX), "Max value incorrect");
        assertEquals(BigDecimal.valueOf(2.5).setScale(PRECISION, RoundingMode.HALF_UP), service.getThingInfo(ThingInfoType.AVERAGE), "AVG value incorrect");
    }

    @Test
    void addStrings() {
        service.addNumber("1.5");
        service.addNumber("10.5");
        service.addNumber("2.5");
        service.addNumber("-2.5e1");
        assertEquals(DecimalUtils.makeScaledBigDecimal(-25.0, PRECISION), service.getThingInfo(ThingInfoType.MIN), "Min value incorrect");
        assertEquals(DecimalUtils.makeScaledBigDecimal(10.5, PRECISION), service.getThingInfo(ThingInfoType.MAX), "Max value incorrect");
        assertEquals(DecimalUtils.makeScaledBigDecimal(-2.625, PRECISION), service.getThingInfo(ThingInfoType.AVERAGE), "AVG value incorrect");
    }

}