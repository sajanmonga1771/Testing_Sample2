#!/bin/bash

# Build script for Math Operations Application
# This script compiles, tests, and packages the Java application

set -e

echo "=== Math Operations Build Script ==="

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "Error: Maven is not installed. Please install Maven first."
    echo "On Amazon Linux 2: sudo yum install -y maven"
    echo "On Ubuntu/Debian: sudo apt-get install -y maven"
    exit 1
fi

# Check Java version
echo "Checking Java version..."
java -version

# Clean previous builds
echo "Cleaning previous builds..."
mvn clean

# Compile and run tests
echo "Compiling and running tests..."
mvn compile test

# Package the application
echo "Packaging application..."
mvn package

# Check if JAR was created
JAR_FILE=$(find target -name "*.jar" -not -name "*-sources.jar" | head -n 1)
if [ -f "$JAR_FILE" ]; then
    echo "Build successful!"
    echo "JAR file created: $JAR_FILE"
    echo "File size: $(du -h "$JAR_FILE" | cut -f1)"
else
    echo "Error: JAR file not found"
    exit 1
fi

echo "=== Build Complete ==="
echo ""
echo "To run the application locally:"
echo "  java -jar $JAR_FILE"
echo ""
echo "To deploy to EC2:"
echo "  1. Copy the JAR file to your EC2 instance"
echo "  2. Run the deploy-ec2.sh script on the EC2 instance"
echo ""