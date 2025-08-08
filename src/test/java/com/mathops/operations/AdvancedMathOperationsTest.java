package com.mathops.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdvancedMathOperationsTest {

    private AdvancedMathOperations advancedOps;

    @BeforeEach
    void setUp() {
        advancedOps = new AdvancedMathOperations();
    }

    @Test
    void testPower() {
        assertEquals(8.0, advancedOps.power(2.0, 3.0), 0.001);
        assertEquals(1.0, advancedOps.power(5.0, 0.0), 0.001);
        assertEquals(0.25, advancedOps.power(2.0, -2.0), 0.001);
    }

    @Test
    void testSquareRoot() {
        assertEquals(3.0, advancedOps.squareRoot(9.0), 0.001);
        assertEquals(0.0, advancedOps.squareRoot(0.0), 0.001);
        assertEquals(2.0, advancedOps.squareRoot(4.0), 0.001);
    }

    @Test
    void testSquareRootNegative() {
        assertThrows(IllegalArgumentException.class, () -> advancedOps.squareRoot(-1.0));
    }

    @Test
    void testCubeRoot() {
        assertEquals(2.0, advancedOps.cubeRoot(8.0), 0.001);
        assertEquals(-2.0, advancedOps.cubeRoot(-8.0), 0.001);
        assertEquals(0.0, advancedOps.cubeRoot(0.0), 0.001);
    }

    @Test
    void testAbsolute() {
        assertEquals(5.0, advancedOps.absolute(-5.0), 0.001);
        assertEquals(5.0, advancedOps.absolute(5.0), 0.001);
        assertEquals(0.0, advancedOps.absolute(0.0), 0.001);
    }

    @Test
    void testFactorial() {
        assertEquals(1, advancedOps.factorial(0));
        assertEquals(1, advancedOps.factorial(1));
        assertEquals(120, advancedOps.factorial(5));
        assertEquals(24, advancedOps.factorial(4));
    }

    @Test
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> advancedOps.factorial(-1));
    }

    @Test
    void testNaturalLog() {
        assertEquals(0.0, advancedOps.naturalLog(1.0), 0.001);
        assertEquals(1.0, advancedOps.naturalLog(Math.E), 0.001);
    }

    @Test
    void testNaturalLogInvalid() {
        assertThrows(IllegalArgumentException.class, () -> advancedOps.naturalLog(0.0));
        assertThrows(IllegalArgumentException.class, () -> advancedOps.naturalLog(-1.0));
    }

    @Test
    void testLog10() {
        assertEquals(0.0, advancedOps.log10(1.0), 0.001);
        assertEquals(1.0, advancedOps.log10(10.0), 0.001);
        assertEquals(2.0, advancedOps.log10(100.0), 0.001);
    }

    @Test
    void testLog10Invalid() {
        assertThrows(IllegalArgumentException.class, () -> advancedOps.log10(0.0));
        assertThrows(IllegalArgumentException.class, () -> advancedOps.log10(-1.0));
    }
}