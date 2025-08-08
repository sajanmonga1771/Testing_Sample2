package com.mathops.service;

import com.mathops.operations.BasicMathOperations;
import com.mathops.operations.AdvancedMathOperations;
import com.mathops.operations.TrigonometricOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Math service that provides all mathematical operations
 */
public class MathService {
    
    private static final Logger logger = LoggerFactory.getLogger(MathService.class);
    
    private final BasicMathOperations basicOps;
    private final AdvancedMathOperations advancedOps;
    private final TrigonometricOperations trigOps;
    
    public MathService() {
        this.basicOps = new BasicMathOperations();
        this.advancedOps = new AdvancedMathOperations();
        this.trigOps = new TrigonometricOperations();
    }
    
    // Basic Operations
    public double add(double a, double b) {
        logger.info("Adding {} and {}", a, b);
        return basicOps.add(a, b);
    }
    
    public double subtract(double a, double b) {
        logger.info("Subtracting {} from {}", b, a);
        return basicOps.subtract(a, b);
    }
    
    public double multiply(double a, double b) {
        logger.info("Multiplying {} and {}", a, b);
        return basicOps.multiply(a, b);
    }
    
    public double divide(double a, double b) {
        logger.info("Dividing {} by {}", a, b);
        return basicOps.divide(a, b);
    }
    
    public double modulo(double a, double b) {
        logger.info("Calculating modulo of {} and {}", a, b);
        return basicOps.modulo(a, b);
    }
    
    // Advanced Operations
    public double power(double base, double exponent) {
        logger.info("Calculating {} to the power of {}", base, exponent);
        return advancedOps.power(base, exponent);
    }
    
    public double squareRoot(double number) {
        logger.info("Calculating square root of {}", number);
        return advancedOps.squareRoot(number);
    }
    
    public double cubeRoot(double number) {
        logger.info("Calculating cube root of {}", number);
        return advancedOps.cubeRoot(number);
    }
    
    public double absolute(double number) {
        logger.info("Calculating absolute value of {}", number);
        return advancedOps.absolute(number);
    }
    
    public long factorial(int n) {
        logger.info("Calculating factorial of {}", n);
        return advancedOps.factorial(n);
    }
    
    public double naturalLog(double number) {
        logger.info("Calculating natural logarithm of {}", number);
        return advancedOps.naturalLog(number);
    }
    
    public double log10(double number) {
        logger.info("Calculating logarithm base 10 of {}", number);
        return advancedOps.log10(number);
    }
    
    // Trigonometric Operations
    public double sin(double angleInRadians) {
        logger.info("Calculating sine of {} radians", angleInRadians);
        return trigOps.sin(angleInRadians);
    }
    
    public double cos(double angleInRadians) {
        logger.info("Calculating cosine of {} radians", angleInRadians);
        return trigOps.cos(angleInRadians);
    }
    
    public double tan(double angleInRadians) {
        logger.info("Calculating tangent of {} radians", angleInRadians);
        return trigOps.tan(angleInRadians);
    }
    
    public double asin(double value) {
        logger.info("Calculating arc sine of {}", value);
        return trigOps.asin(value);
    }
    
    public double acos(double value) {
        logger.info("Calculating arc cosine of {}", value);
        return trigOps.acos(value);
    }
    
    public double atan(double value) {
        logger.info("Calculating arc tangent of {}", value);
        return trigOps.atan(value);
    }
    
    public double degreesToRadians(double degrees) {
        logger.info("Converting {} degrees to radians", degrees);
        return trigOps.degreesToRadians(degrees);
    }
    
    public double radiansToDegrees(double radians) {
        logger.info("Converting {} radians to degrees", radians);
        return trigOps.radiansToDegrees(radians);
    }
}