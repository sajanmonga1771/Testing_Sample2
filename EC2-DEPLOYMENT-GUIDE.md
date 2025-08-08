# EC2 Deployment Guide for Math Operations Application

This guide provides step-by-step instructions for deploying the Math Operations Java application on an Amazon EC2 instance.

## Prerequisites

1. **AWS Account** with EC2 access
2. **EC2 Instance** running Amazon Linux 2 or compatible Linux distribution
3. **SSH Key Pair** for EC2 access
4. **Security Group** allowing SSH access (port 22)

## Step 1: Launch EC2 Instance

### Recommended Instance Configuration:
- **AMI**: Amazon Linux 2
- **Instance Type**: t3.micro (free tier) or t3.small
- **Storage**: 8GB GP2 (minimum)
- **Security Group**: Allow SSH (port 22) from your IP

### Launch Commands (AWS CLI):
```bash
# Create security group
aws ec2 create-security-group \
    --group-name math-operations-sg \
    --description "Security group for Math Operations app"

# Allow SSH access
aws ec2 authorize-security-group-ingress \
    --group-name math-operations-sg \
    --protocol tcp \
    --port 22 \
    --cidr 0.0.0.0/0

# Launch instance
aws ec2 run-instances \
    --image-id ami-0abcdef1234567890 \
    --count 1 \
    --instance-type t3.micro \
    --key-name your-key-pair \
    --security-groups math-operations-sg
```

## Step 2: Connect to EC2 Instance

```bash
ssh -i your-key.pem ec2-user@your-ec2-public-ip
```

## Step 3: Install Prerequisites on EC2

```bash
# Update system
sudo yum update -y

# Install Java 11
sudo yum install -y java-11-amazon-corretto-headless

# Install Maven (for building)
sudo yum install -y maven

# Install Git
sudo yum install -y git

# Verify installations
java -version
mvn -version
git --version
```

## Step 4: Clone and Build the Application

```bash
# Clone the repository
git clone https://github.com/sajanmonga1771/Testing_Sample2.git
cd Testing_Sample2

# Make build script executable
chmod +x build.sh

# Build the application
./build.sh
```

## Step 5: Deploy the Application

```bash
# Make deployment script executable
chmod +x deploy-ec2.sh

# Run deployment script
sudo ./deploy-ec2.sh
```

## Step 6: Verify Deployment

```bash
# Check service status
sudo systemctl status math-operations

# View real-time logs
sudo journalctl -u math-operations -f

# Check if JAR file exists
ls -la /opt/math-operations/
```

## Service Management Commands

```bash
# Start the service
sudo systemctl start math-operations

# Stop the service
sudo systemctl stop math-operations

# Restart the service
sudo systemctl restart math-operations

# Enable auto-start on boot
sudo systemctl enable math-operations

# Disable auto-start
sudo systemctl disable math-operations

# View service status
sudo systemctl status math-operations

# View service logs
sudo journalctl -u math-operations

# View real-time logs
sudo journalctl -u math-operations -f

# View application logs
tail -f /opt/math-operations/logs/math-operations.log
```

## Troubleshooting

### Common Issues and Solutions

1. **Java not found**
   ```bash
   sudo yum install -y java-11-amazon-corretto-headless
   java -version
   ```

2. **Maven build fails**
   ```bash
   sudo yum install -y maven
   mvn clean compile
   ```

3. **Permission denied**
   ```bash
   sudo chown -R ec2-user:ec2-user /opt/math-operations
   chmod +x deploy-ec2.sh
   ```

4. **Service fails to start**
   ```bash
   sudo journalctl -u math-operations
   sudo systemctl status math-operations
   ```

5. **Port conflicts**
   ```bash
   sudo netstat -tlnp | grep :8080
   sudo lsof -i :8080
   ```

### Log Locations

- **System logs**: `sudo journalctl -u math-operations`
- **Application logs**: `/opt/math-operations/logs/math-operations.log`
- **Maven build logs**: `target/surefire-reports/`
- **Systemd service logs**: `/var/log/messages`

## Performance Monitoring

### System Resource Monitoring

```bash
# CPU and memory usage
top
htop

# Disk usage
df -h
du -sh /opt/math-operations

# Process monitoring
ps aux | grep java

# Memory usage of Java process
sudo jstat -gc $(pgrep java)
```

### Application Monitoring

```bash
# Check application logs for errors
grep ERROR /opt/math-operations/logs/math-operations.log

# Monitor log file in real-time
tail -f /opt/math-operations/logs/math-operations.log

# Check service uptime
sudo systemctl show math-operations --property=ActiveEnterTimestamp
```

## Security Considerations

1. **Update packages regularly**
   ```bash
   sudo yum update -y
   ```

2. **Configure firewall**
   ```bash
   sudo firewall-cmd --permanent --add-port=22/tcp
   sudo firewall-cmd --reload
   ```

3. **Use non-root user**
   - The application runs as `ec2-user` (non-root)

4. **Secure SSH access**
   - Use key-based authentication
   - Disable password authentication
   - Restrict SSH access to specific IPs

## Backup and Recovery

### Backup Application

```bash
# Create backup directory
sudo mkdir -p /backup/math-operations

# Backup application files
sudo tar -czf /backup/math-operations/app-backup-$(date +%Y%m%d).tar.gz \
    /opt/math-operations

# Backup service configuration
sudo cp /etc/systemd/system/math-operations.service \
    /backup/math-operations/
```

### Restore Application

```bash
# Stop service
sudo systemctl stop math-operations

# Restore from backup
sudo tar -xzf /backup/math-operations/app-backup-YYYYMMDD.tar.gz -C /

# Restore service configuration
sudo cp /backup/math-operations/math-operations.service \
    /etc/systemd/system/

# Reload and start service
sudo systemctl daemon-reload
sudo systemctl start math-operations
```

## Scaling Considerations

### Vertical Scaling
- Upgrade to larger instance type (t3.small → t3.medium)
- Increase memory allocation in systemd service file

### Horizontal Scaling
- Deploy multiple instances behind a load balancer
- Use Auto Scaling Groups
- Consider containerization with ECS or EKS

## Cost Optimization

1. **Use appropriate instance size**
2. **Enable detailed monitoring only when needed**
3. **Use Spot Instances for non-critical environments**
4. **Stop instances when not in use (development/testing)**

## Next Steps

1. **Set up monitoring** with CloudWatch
2. **Configure auto-scaling** based on metrics
3. **Add load balancer** for high availability
4. **Implement CI/CD pipeline** for automated deployments
5. **Add SSL/TLS** for secure communications
6. **Set up backup automation**

For questions or issues, refer to the main repository documentation or create an issue in the GitHub repository.