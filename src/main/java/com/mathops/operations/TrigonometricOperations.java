package com.mathops.operations;

/**
 * Trigonometric operations utility class
 */
public class TrigonometricOperations {
    
    /**
     * Calculates sine of an angle
     * @param angleInRadians angle in radians
     * @return sine of the angle
     */
    public double sin(double angleInRadians) {
        return Math.sin(angleInRadians);
    }
    
    /**
     * Calculates cosine of an angle
     * @param angleInRadians angle in radians
     * @return cosine of the angle
     */
    public double cos(double angleInRadians) {
        return Math.cos(angleInRadians);
    }
    
    /**
     * Calculates tangent of an angle
     * @param angleInRadians angle in radians
     * @return tangent of the angle
     */
    public double tan(double angleInRadians) {
        return Math.tan(angleInRadians);
    }
    
    /**
     * Calculates arc sine (inverse sine)
     * @param value input value (-1 to 1)
     * @return arc sine in radians
     * @throws IllegalArgumentException if value is outside [-1, 1] range
     */
    public double asin(double value) {
        if (value < -1 || value > 1) {
            throw new IllegalArgumentException("Arc sine is defined only for values between -1 and 1");
        }
        return Math.asin(value);
    }
    
    /**
     * Calculates arc cosine (inverse cosine)
     * @param value input value (-1 to 1)
     * @return arc cosine in radians
     * @throws IllegalArgumentException if value is outside [-1, 1] range
     */
    public double acos(double value) {
        if (value < -1 || value > 1) {
            throw new IllegalArgumentException("Arc cosine is defined only for values between -1 and 1");
        }
        return Math.acos(value);
    }
    
    /**
     * Calculates arc tangent (inverse tangent)
     * @param value input value
     * @return arc tangent in radians
     */
    public double atan(double value) {
        return Math.atan(value);
    }
    
    /**
     * Converts degrees to radians
     * @param degrees angle in degrees
     * @return angle in radians
     */
    public double degreesToRadians(double degrees) {
        return Math.toRadians(degrees);
    }
    
    /**
     * Converts radians to degrees
     * @param radians angle in radians
     * @return angle in degrees
     */
    public double radiansToDegrees(double radians) {
        return Math.toDegrees(radians);
    }
}