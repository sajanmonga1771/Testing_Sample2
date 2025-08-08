package com.mathops;

import com.mathops.service.MathService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Main application class for Math Operations
 */
public class MathOperationsApp {
    
    private static final Logger logger = LoggerFactory.getLogger(MathOperationsApp.class);
    private static final MathService mathService = new MathService();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        logger.info("Starting Math Operations Application");
        
        System.out.println("=== Math Operations Calculator ===");
        System.out.println("Welcome to the Math Operations Application!");
        
        boolean running = true;
        
        while (running) {
            showMenu();
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1:
                        performBasicOperations();
                        break;
                    case 2:
                        performAdvancedOperations();
                        break;
                    case 3:
                        performTrigonometricOperations();
                        break;
                    case 4:
                        runDemoCalculations();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Thank you for using Math Operations Calculator!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                logger.error("Error during calculation: {}", e.getMessage());
                System.out.println("Error: " + e.getMessage());
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
        logger.info("Math Operations Application terminated");
    }
    
    private static void showMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Basic Operations (Add, Subtract, Multiply, Divide)");
        System.out.println("2. Advanced Operations (Power, Square Root, Factorial, etc.)");
        System.out.println("3. Trigonometric Operations (Sin, Cos, Tan, etc.)");
        System.out.println("4. Run Demo Calculations");
        System.out.println("0. Exit");
    }
    
    private static void performBasicOperations() {
        System.out.println("\n=== Basic Operations ===");
        double a = getDoubleInput("Enter first number: ");
        double b = getDoubleInput("Enter second number: ");
        
        System.out.println("\nResults:");
        System.out.printf("Addition: %.2f + %.2f = %.2f%n", a, b, mathService.add(a, b));
        System.out.printf("Subtraction: %.2f - %.2f = %.2f%n", a, b, mathService.subtract(a, b));
        System.out.printf("Multiplication: %.2f * %.2f = %.2f%n", a, b, mathService.multiply(a, b));
        
        try {
            System.out.printf("Division: %.2f / %.2f = %.2f%n", a, b, mathService.divide(a, b));
            System.out.printf("Modulo: %.2f %% %.2f = %.2f%n", a, b, mathService.modulo(a, b));
        } catch (IllegalArgumentException e) {
            System.out.println("Division/Modulo: " + e.getMessage());
        }
    }
    
    private static void performAdvancedOperations() {
        System.out.println("\n=== Advanced Operations ===");
        double number = getDoubleInput("Enter a number: ");
        
        System.out.println("\nResults:");
        System.out.printf("Square (%.2f^2): %.2f%n", number, mathService.power(number, 2));
        System.out.printf("Cube (%.2f^3): %.2f%n", number, mathService.power(number, 3));
        
        try {
            System.out.printf("Square root: %.2f%n", mathService.squareRoot(number));
        } catch (IllegalArgumentException e) {
            System.out.println("Square root: " + e.getMessage());
        }
        
        System.out.printf("Cube root: %.2f%n", mathService.cubeRoot(number));
        System.out.printf("Absolute value: %.2f%n", mathService.absolute(number));
        
        try {
            System.out.printf("Natural log: %.2f%n", mathService.naturalLog(number));
            System.out.printf("Log base 10: %.2f%n", mathService.log10(number));
        } catch (IllegalArgumentException e) {
            System.out.println("Logarithm: " + e.getMessage());
        }
        
        if (number >= 0 && number <= 20 && number == (int) number) {
            System.out.printf("Factorial: %d%n", mathService.factorial((int) number));
        }
    }
    
    private static void performTrigonometricOperations() {
        System.out.println("\n=== Trigonometric Operations ===");
        double angle = getDoubleInput("Enter angle in degrees: ");
        double radians = mathService.degreesToRadians(angle);
        
        System.out.println("\nResults:");
        System.out.printf("Angle in radians: %.4f%n", radians);
        System.out.printf("Sin(%.2f°): %.4f%n", angle, mathService.sin(radians));
        System.out.printf("Cos(%.2f°): %.4f%n", angle, mathService.cos(radians));
        System.out.printf("Tan(%.2f°): %.4f%n", angle, mathService.tan(radians));
    }
    
    private static void runDemoCalculations() {
        System.out.println("\n=== Demo Calculations ===");
        
        // Basic operations demo
        System.out.println("Basic Operations Demo:");
        System.out.printf("10 + 5 = %.2f%n", mathService.add(10, 5));
        System.out.printf("10 - 5 = %.2f%n", mathService.subtract(10, 5));
        System.out.printf("10 * 5 = %.2f%n", mathService.multiply(10, 5));
        System.out.printf("10 / 5 = %.2f%n", mathService.divide(10, 5));
        
        // Advanced operations demo
        System.out.println("\nAdvanced Operations Demo:");
        System.out.printf("2^8 = %.2f%n", mathService.power(2, 8));
        System.out.printf("√16 = %.2f%n", mathService.squareRoot(16));
        System.out.printf("5! = %d%n", mathService.factorial(5));
        System.out.printf("ln(e) = %.4f%n", mathService.naturalLog(Math.E));
        
        // Trigonometric operations demo
        System.out.println("\nTrigonometric Operations Demo:");
        double angle30 = mathService.degreesToRadians(30);
        System.out.printf("Sin(30°) = %.4f%n", mathService.sin(angle30));
        System.out.printf("Cos(60°) = %.4f%n", mathService.cos(mathService.degreesToRadians(60)));
        System.out.printf("Tan(45°) = %.4f%n", mathService.tan(mathService.degreesToRadians(45)));
    }
    
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid integer: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }
    
    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return value;
    }
}