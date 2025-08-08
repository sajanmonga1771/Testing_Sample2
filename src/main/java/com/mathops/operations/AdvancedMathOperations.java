package com.mathops.operations;

/**
 * Advanced mathematical operations utility class
 */
public class AdvancedMathOperations {
    
    /**
     * Calculates power of a number
     * @param base base number
     * @param exponent exponent
     * @return base raised to the power of exponent
     */
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
    
    /**
     * Calculates square root of a number
     * @param number input number
     * @return square root of the number
     * @throws IllegalArgumentException if number is negative
     */
    public double squareRoot(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Square root of negative number is not defined");
        }
        return Math.sqrt(number);
    }
    
    /**
     * Calculates cube root of a number
     * @param number input number
     * @return cube root of the number
     */
    public double cubeRoot(double number) {
        return Math.cbrt(number);
    }
    
    /**
     * Calculates absolute value of a number
     * @param number input number
     * @return absolute value of the number
     */
    public double absolute(double number) {
        return Math.abs(number);
    }
    
    /**
     * Calculates factorial of a number
     * @param n input number (must be non-negative integer)
     * @return factorial of n
     * @throws IllegalArgumentException if n is negative
     */
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    /**
     * Calculates natural logarithm
     * @param number input number
     * @return natural logarithm of the number
     * @throws IllegalArgumentException if number is not positive
     */
    public double naturalLog(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Logarithm is not defined for non-positive numbers");
        }
        return Math.log(number);
    }
    
    /**
     * Calculates logarithm base 10
     * @param number input number
     * @return logarithm base 10 of the number
     * @throws IllegalArgumentException if number is not positive
     */
    public double log10(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Logarithm is not defined for non-positive numbers");
        }
        return Math.log10(number);
    }
}