package com.mathops.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrigonometricOperationsTest {

    private TrigonometricOperations trigOps;

    @BeforeEach
    void setUp() {
        trigOps = new TrigonometricOperations();
    }

    @Test
    void testSin() {
        assertEquals(0.0, trigOps.sin(0.0), 0.001);
        assertEquals(1.0, trigOps.sin(Math.PI / 2), 0.001);
        assertEquals(0.0, trigOps.sin(Math.PI), 0.001);
    }

    @Test
    void testCos() {
        assertEquals(1.0, trigOps.cos(0.0), 0.001);
        assertEquals(0.0, trigOps.cos(Math.PI / 2), 0.001);
        assertEquals(-1.0, trigOps.cos(Math.PI), 0.001);
    }

    @Test
    void testTan() {
        assertEquals(0.0, trigOps.tan(0.0), 0.001);
        assertEquals(1.0, trigOps.tan(Math.PI / 4), 0.001);
    }

    @Test
    void testAsin() {
        assertEquals(0.0, trigOps.asin(0.0), 0.001);
        assertEquals(Math.PI / 2, trigOps.asin(1.0), 0.001);
        assertEquals(-Math.PI / 2, trigOps.asin(-1.0), 0.001);
    }

    @Test
    void testAsinInvalid() {
        assertThrows(IllegalArgumentException.class, () -> trigOps.asin(2.0));
        assertThrows(IllegalArgumentException.class, () -> trigOps.asin(-2.0));
    }

    @Test
    void testAcos() {
        assertEquals(Math.PI / 2, trigOps.acos(0.0), 0.001);
        assertEquals(0.0, trigOps.acos(1.0), 0.001);
        assertEquals(Math.PI, trigOps.acos(-1.0), 0.001);
    }

    @Test
    void testAcosInvalid() {
        assertThrows(IllegalArgumentException.class, () -> trigOps.acos(2.0));
        assertThrows(IllegalArgumentException.class, () -> trigOps.acos(-2.0));
    }

    @Test
    void testAtan() {
        assertEquals(0.0, trigOps.atan(0.0), 0.001);
        assertEquals(Math.PI / 4, trigOps.atan(1.0), 0.001);
        assertEquals(-Math.PI / 4, trigOps.atan(-1.0), 0.001);
    }

    @Test
    void testDegreesToRadians() {
        assertEquals(0.0, trigOps.degreesToRadians(0.0), 0.001);
        assertEquals(Math.PI / 2, trigOps.degreesToRadians(90.0), 0.001);
        assertEquals(Math.PI, trigOps.degreesToRadians(180.0), 0.001);
    }

    @Test
    void testRadiansToDegrees() {
        assertEquals(0.0, trigOps.radiansToDegrees(0.0), 0.001);
        assertEquals(90.0, trigOps.radiansToDegrees(Math.PI / 2), 0.001);
        assertEquals(180.0, trigOps.radiansToDegrees(Math.PI), 0.001);
    }
}