# Math Operations Application

A comprehensive Java application providing various mathematical operations including basic arithmetic, advanced mathematical functions, and trigonometric calculations.

## 🚀 **Quick Start with Automated Deployment**

### Option 1: Automated GitHub to EC2 Deployment (Recommended)
1. Fork this repository
2. Set up GitHub secrets (AWS credentials, EC2 details)
3. Push to main branch - automatic deployment to EC2!
4. See [GitHub to EC2 Deployment Guide](GITHUB-TO-EC2-DEPLOYMENT.md)

### Option 2: Manual Deployment
Follow the traditional deployment steps below.

## Features

- **Basic Operations**: Addition, subtraction, multiplication, division, modulo
- **Advanced Operations**: Power, square root, cube root, factorial, logarithms, absolute value
- **Trigonometric Operations**: Sin, cos, tan, arc functions, degree/radian conversion
- **Interactive Console Interface**: User-friendly menu-driven interface
- **Comprehensive Testing**: Unit tests for all mathematical operations
- **Logging**: Structured logging with file and console output
- **Production Ready**: Configured for deployment on EC2 instances
- **🆕 Automated CI/CD**: GitHub Actions workflows for automatic deployment

## 📁 Project Structure

```
math-operations/
├── .github/
│   └── workflows/
│       ├── ci-cd.yml                    # Main CI/CD pipeline
│       ├── deploy-to-ec2.yml           # Direct SSH deployment
│       ├── deploy-codedeploy.yml       # AWS CodeDeploy deployment
│       └── deploy-docker-ec2.yml       # Docker-based deployment
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/mathops/
│   │   │       ├── operations/          # Mathematical operation classes
│   │   │       ├── service/             # Service layer
│   │   │       └── MathOperationsApp.java # Main application
│   │   └── resources/
│   │       ├── logback.xml              # Logging configuration
│   │       └── application.properties   # Application settings
│   └── test/
│       └── java/
│           └── com/mathops/operations/  # Unit tests
├── target/                              # Build output (generated)
├── logs/                               # Application logs (generated)
├── pom.xml                             # Maven configuration
├── build.sh                           # Build script
├── deploy-ec2.sh                       # EC2 deployment script
├── Dockerfile                          # Docker configuration
├── docker-compose.yml                  # Docker Compose setup
├── README.md                           # This file
├── EC2-DEPLOYMENT-GUIDE.md             # Manual deployment guide
└── GITHUB-TO-EC2-DEPLOYMENT.md         # Automated deployment guide
```

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- For EC2 deployment: Amazon Linux 2 or compatible Linux distribution
- For automated deployment: AWS account with appropriate permissions

## 🤖 **Automated Deployment (GitHub Actions)**

### Available Deployment Workflows:

1. **🚀 Direct SSH Deployment** - Simple, direct deployment via SSH
2. **🏗️ AWS CodeDeploy** - Managed deployment with rollback capabilities
3. **🐳 Docker Deployment** - Containerized deployment using ECR

### Quick Setup:

1. **Configure GitHub Secrets:**
   ```
   AWS_ACCESS_KEY_ID=your_aws_access_key
   AWS_SECRET_ACCESS_KEY=your_aws_secret_key
   EC2_HOST=your_ec2_public_ip
   EC2_USER=ec2-user
   EC2_PRIVATE_KEY=your_private_key_content
   ```

2. **Prepare EC2 Instance:**
   ```bash
   # Basic setup
   sudo yum update -y
   sudo yum install -y java-11-amazon-corretto-headless
   ```

3. **Deploy:**
   ```bash
   # Push to main branch triggers automatic deployment
   git push origin main
   ```

**📖 For detailed setup instructions, see [GitHub to EC2 Deployment Guide](GITHUB-TO-EC2-DEPLOYMENT.md)**

## 🔧 **Manual Building and Deployment**

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

## Manual Deployment on EC2

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

**📖 For detailed manual deployment instructions, see [EC2 Deployment Guide](EC2-DEPLOYMENT-GUIDE.md)**

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

## 🐳 **Docker Deployment**

### Build and Run with Docker

```bash
# Build the image
docker build -t math-operations .

# Run with Docker Compose
docker-compose up -d

# View logs
docker logs math-operations-app
```

### Docker on EC2 (via GitHub Actions)

The automated Docker deployment:
1. Builds Docker image
2. Pushes to Amazon ECR
3. Deploys to EC2 using docker-compose
4. Provides zero-downtime deployments

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

## 📊 **CI/CD Pipeline Features**

### Automated Testing
- Unit tests run on every push
- Test reports generated and published
- Build fails if tests don't pass

### Multiple Deployment Strategies
- **Direct SSH**: Simple deployment for small applications
- **AWS CodeDeploy**: Enterprise-grade deployment with rollback
- **Docker**: Containerized deployment for consistency

### Environment Support
- Production deployments on main branch
- Staging deployments on develop branch
- Manual deployment triggers available

### Security
- Secrets management via GitHub Secrets
- IAM role-based AWS access
- Secure SSH key handling

## Security Considerations

- Application runs with limited user privileges (ec2-user)
- No network ports exposed (console application)
- Input validation and error handling implemented
- Structured logging for audit trails
- Automated security scanning in CI/CD pipeline

## Monitoring

Monitor the application using:

1. **System logs**: `sudo journalctl -u math-operations`
2. **Application logs**: `/opt/math-operations/logs/math-operations.log`
3. **System resources**: `top`, `htop`, or CloudWatch
4. **Service status**: `systemctl status math-operations`
5. **GitHub Actions**: Deployment status and history

## Troubleshooting

### Common Issues

1. **Java not found**: Ensure Java 11+ is installed
2. **Permission denied**: Check file permissions and user ownership
3. **Service won't start**: Check logs with `journalctl -u math-operations`
4. **Out of memory**: Adjust JVM heap settings in systemd service file
5. **Deployment fails**: Check GitHub Actions logs and AWS permissions

### Log Locations

- **System logs**: `journalctl -u math-operations`
- **Application logs**: `/opt/math-operations/logs/`
- **Maven build logs**: `target/surefire-reports/`
- **GitHub Actions logs**: Repository > Actions tab
- **AWS CloudWatch**: For advanced monitoring

## Contributing

1. Fork the repository
2. Create a feature branch
3. Add tests for new functionality
4. Ensure all tests pass
5. Submit a pull request
6. GitHub Actions will automatically test your changes

## 📚 **Documentation**

- **[Manual EC2 Deployment Guide](EC2-DEPLOYMENT-GUIDE.md)** - Step-by-step manual deployment
- **[GitHub to EC2 Automation Guide](GITHUB-TO-EC2-DEPLOYMENT.md)** - Automated deployment setup
- **[API Documentation](src/main/java/com/mathops/)** - JavaDoc in source code

## License

This project is open source and available under the MIT License.

---

## 🎯 **Quick Start Summary**

### For Automated Deployment:
1. Configure GitHub secrets with AWS credentials
2. Push to main branch
3. Watch GitHub Actions deploy automatically! 🚀

### For Manual Deployment:
1. Build: `./build.sh`
2. Deploy: `sudo ./deploy-ec2.sh`
3. Monitor: `sudo systemctl status math-operations`

**Choose the approach that best fits your workflow!**