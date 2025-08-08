package com.mathops.operations;

/**
 * Basic mathematical operations utility class
 */
public class BasicMathOperations {
    
    /**
     * Adds two numbers
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public double add(double a, double b) {
        return a + b;
    }
    
    /**
     * Subtracts second number from first
     * @param a first number
     * @param b second number
     * @return difference of a and b
     */
    public double subtract(double a, double b) {
        return a - b;
    }
    
    /**
     * Multiplies two numbers
     * @param a first number
     * @param b second number
     * @return product of a and b
     */
    public double multiply(double a, double b) {
        return a * b;
    }
    
    /**
     * Divides first number by second
     * @param a dividend
     * @param b divisor
     * @return quotient of a and b
     * @throws IllegalArgumentException if divisor is zero
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        return a / b;
    }
    
    /**
     * Calculates remainder of division
     * @param a dividend
     * @param b divisor
     * @return remainder of a divided by b
     * @throws IllegalArgumentException if divisor is zero
     */
    public double modulo(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Modulo by zero is not allowed");
        }
        return a % b;
    }
}