# Math Operations Application

A comprehensive Java application providing various mathematical operations including basic arithmetic, advanced mathematical functions, and trigonometric calculations.

## Features

- **Basic Operations**: Addition, subtraction, multiplication, division, modulo
- **Advanced Operations**: Power, square root, cube root, factorial, logarithms, absolute value
- **Trigonometric Operations**: Sin, cos, tan, arc functions, degree/radian conversion
- **Interactive Console Interface**: User-friendly menu-driven interface
- **Comprehensive Testing**: Unit tests for all mathematical operations
- **Logging**: Structured logging with file and console output
- **Production Ready**: Configured for deployment on EC2 instances

## Project Structure

```
math-operations/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/mathops/
│   │   │       ├── operations/          # Mathematical operation classes
│   │   │       ├── service/             # Service layer
│   │   │       └── MathOperationsApp.java # Main application
│   │   └── resources/
│   │       └── logback.xml              # Logging configuration
│   └── test/
│       └── java/
│           └── com/mathops/operations/  # Unit tests
├── target/                              # Build output (generated)
├── logs/                               # Application logs (generated)
├── pom.xml                             # Maven configuration
├── build.sh                           # Build script
├── deploy-ec2.sh                       # EC2 deployment script
└── README.md                           # This file
```

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- For EC2 deployment: Amazon Linux 2 or compatible Linux distribution

## Building the Application

### On Local Machine

1. Clone or download the project
2. Navigate to the project directory
3. Run the build script:

```bash
chmod +x build.sh
./build.sh
```

Or manually with Maven:
```bash
mvn clean compile test package
```

### On Windows

```cmd
mvn clean compile test package
```

## Running the Application

### Locally

After building, run the application:

```bash
java -jar target/math-operations-1.0.0.jar
```

### Testing

Run unit tests:

```bash
mvn test
```

## Deployment on EC2

### Prerequisites for EC2

1. EC2 instance running Amazon Linux 2
2. SSH access to the instance
3. Built JAR file

### Deployment Steps

1. **Prepare the JAR file**: Build the application locally or on the EC2 instance
2. **Upload files to EC2**:
   ```bash
   scp -i your-key.pem target/math-operations-1.0.0.jar ec2-user@your-ec2-ip:~/
   scp -i your-key.pem deploy-ec2.sh ec2-user@your-ec2-ip:~/
   ```

3. **Connect to EC2 and deploy**:
   ```bash
   ssh -i your-key.pem ec2-user@your-ec2-ip
   chmod +x deploy-ec2.sh
   sudo ./deploy-ec2.sh
   ```

### Service Management on EC2

After deployment, the application runs as a systemd service:

```bash
# Start the service
sudo systemctl start math-operations

# Stop the service
sudo systemctl stop math-operations

# Restart the service
sudo systemctl restart math-operations

# Check service status
sudo systemctl status math-operations

# View real-time logs
sudo journalctl -u math-operations -f

# View application logs
tail -f /opt/math-operations/logs/math-operations.log
```

## Application Configuration

### JVM Settings

The deployment script configures the following JVM options:
- Initial heap size: 256MB (-Xms256m)
- Maximum heap size: 512MB (-Xmx512m)
- Garbage collector: G1GC (-XX:+UseG1GC)

### Logging

- Console output: Configured for development
- File output: `/opt/math-operations/logs/math-operations.log`
- Log rotation: Daily, max 30 days, max 1GB total
- Log level: INFO

## API Usage

The application provides an interactive console interface with the following operations:

### Basic Operations
- Addition
- Subtraction
- Multiplication
- Division
- Modulo

### Advanced Operations
- Power (base^exponent)
- Square root
- Cube root
- Absolute value
- Factorial
- Natural logarithm
- Logarithm base 10

### Trigonometric Operations
- Sine, cosine, tangent
- Arc sine, arc cosine, arc tangent
- Degree to radian conversion
- Radian to degree conversion

## Example Usage

```
=== Math Operations Calculator ===
Welcome to the Math Operations Application!

=== Main Menu ===
1. Basic Operations (Add, Subtract, Multiply, Divide)
2. Advanced Operations (Power, Square Root, Factorial, etc.)
3. Trigonometric Operations (Sin, Cos, Tan, etc.)
4. Run Demo Calculations
0. Exit

Enter your choice: 1

=== Basic Operations ===
Enter first number: 10
Enter second number: 5

Results:
Addition: 10.00 + 5.00 = 15.00
Subtraction: 10.00 - 5.00 = 5.00
Multiplication: 10.00 * 5.00 = 50.00
Division: 10.00 / 5.00 = 2.00
Modulo: 10.00 % 5.00 = 0.00
```

## Security Considerations

- Application runs with limited user privileges (ec2-user)
- No network ports exposed (console application)
- Input validation and error handling implemented
- Structured logging for audit trails

## Monitoring

Monitor the application using:

1. **System logs**: `sudo journalctl -u math-operations`
2. **Application logs**: `/opt/math-operations/logs/math-operations.log`
3. **System resources**: `top`, `htop`, or CloudWatch
4. **Service status**: `systemctl status math-operations`

## Troubleshooting

### Common Issues

1. **Java not found**: Ensure Java 11+ is installed
2. **Permission denied**: Check file permissions and user ownership
3. **Service won't start**: Check logs with `journalctl -u math-operations`
4. **Out of memory**: Adjust JVM heap settings in systemd service file

### Log Locations

- System logs: `journalctl -u math-operations`
- Application logs: `/opt/math-operations/logs/`
- Maven build logs: `target/surefire-reports/`

## Contributing

1. Fork the repository
2. Create a feature branch
3. Add tests for new functionality
4. Ensure all tests pass
5. Submit a pull request

## License

This project is open source and available under the MIT License.