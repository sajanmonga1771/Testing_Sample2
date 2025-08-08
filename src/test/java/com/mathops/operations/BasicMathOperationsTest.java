package com.mathops.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BasicMathOperationsTest {

    private BasicMathOperations basicOps;

    @BeforeEach
    void setUp() {
        basicOps = new BasicMathOperations();
    }

    @Test
    void testAdd() {
        assertEquals(8.0, basicOps.add(5.0, 3.0), 0.001);
        assertEquals(-2.0, basicOps.add(-5.0, 3.0), 0.001);
        assertEquals(0.0, basicOps.add(-3.0, 3.0), 0.001);
    }

    @Test
    void testSubtract() {
        assertEquals(2.0, basicOps.subtract(5.0, 3.0), 0.001);
        assertEquals(-8.0, basicOps.subtract(-5.0, 3.0), 0.001);
        assertEquals(-6.0, basicOps.subtract(-3.0, 3.0), 0.001);
    }

    @Test
    void testMultiply() {
        assertEquals(15.0, basicOps.multiply(5.0, 3.0), 0.001);
        assertEquals(-15.0, basicOps.multiply(-5.0, 3.0), 0.001);
        assertEquals(0.0, basicOps.multiply(0.0, 3.0), 0.001);
    }

    @Test
    void testDivide() {
        assertEquals(2.0, basicOps.divide(6.0, 3.0), 0.001);
        assertEquals(-2.0, basicOps.divide(-6.0, 3.0), 0.001);
        assertEquals(0.0, basicOps.divide(0.0, 3.0), 0.001);
    }

    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> basicOps.divide(5.0, 0.0));
    }

    @Test
    void testModulo() {
        assertEquals(1.0, basicOps.modulo(7.0, 3.0), 0.001);
        assertEquals(0.0, basicOps.modulo(6.0, 3.0), 0.001);
    }

    @Test
    void testModuloByZero() {
        assertThrows(IllegalArgumentException.class, () -> basicOps.modulo(5.0, 0.0));
    }
}