#!/bin/bash

# Math Operations Application Deployment Script for EC2
# This script sets up and deploys the Java application on EC2

set -e

# Configuration
APP_NAME="math-operations"
APP_VERSION="1.0.0"
APP_USER="ec2-user"
APP_DIR="/opt/${APP_NAME}"
SERVICE_NAME="${APP_NAME}"
JAR_NAME="${APP_NAME}-${APP_VERSION}.jar"

echo "=== Math Operations Deployment Script ==="
echo "Starting deployment of ${APP_NAME} v${APP_VERSION}"

# Update system packages
echo "Updating system packages..."
sudo yum update -y

# Install Java 11 if not already installed
echo "Installing Java 11..."
sudo yum install -y java-11-amazon-corretto-headless

# Verify Java installation
java -version

# Create application directory
echo "Creating application directory..."
sudo mkdir -p ${APP_DIR}
sudo mkdir -p ${APP_DIR}/logs
sudo chown -R ${APP_USER}:${APP_USER} ${APP_DIR}

# Copy JAR file to application directory
echo "Copying application JAR..."
if [ -f "target/${JAR_NAME}" ]; then
    sudo cp target/${JAR_NAME} ${APP_DIR}/
    sudo chown ${APP_USER}:${APP_USER} ${APP_DIR}/${JAR_NAME}
else
    echo "Error: JAR file not found. Please build the application first using 'mvn clean package'"
    exit 1
fi

# Create systemd service file
echo "Creating systemd service..."
sudo tee /etc/systemd/system/${SERVICE_NAME}.service > /dev/null <<EOF
[Unit]
Description=Math Operations Java Application
After=network.target

[Service]
Type=simple
User=${APP_USER}
Group=${APP_USER}
WorkingDirectory=${APP_DIR}
ExecStart=/usr/bin/java -jar ${APP_DIR}/${JAR_NAME}
Restart=always
RestartSec=10
StandardOutput=journal
StandardError=journal
SyslogIdentifier=${SERVICE_NAME}

# JVM Options
Environment=JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseG1GC"

[Install]
WantedBy=multi-user.target
EOF

# Reload systemd and enable service
echo "Configuring systemd service..."
sudo systemctl daemon-reload
sudo systemctl enable ${SERVICE_NAME}

# Start the service
echo "Starting ${SERVICE_NAME} service..."
sudo systemctl start ${SERVICE_NAME}

# Check service status
echo "Checking service status..."
sudo systemctl status ${SERVICE_NAME} --no-pager

echo "=== Deployment Complete ==="
echo "Application deployed successfully!"
echo ""
echo "Useful commands:"
echo "  Start service:   sudo systemctl start ${SERVICE_NAME}"
echo "  Stop service:    sudo systemctl stop ${SERVICE_NAME}"
echo "  Restart service: sudo systemctl restart ${SERVICE_NAME}"
echo "  View status:     sudo systemctl status ${SERVICE_NAME}"
echo "  View logs:       sudo journalctl -u ${SERVICE_NAME} -f"
echo "  Application dir: ${APP_DIR}"
echo ""