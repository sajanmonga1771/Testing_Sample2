# GitHub to EC2 Automated Deployment Guide

This guide explains how to set up automated deployment from GitHub to EC2 using GitHub Actions. The repository includes multiple deployment strategies to choose from.

## 🚀 **Available Deployment Methods**

### 1. **Direct SSH Deployment** (Recommended for simple setups)
- **File**: `.github/workflows/deploy-to-ec2.yml`
- **Description**: Direct deployment via SSH with build artifacts
- **Best for**: Small applications, simple deployments

### 2. **AWS CodeDeploy**
- **File**: `.github/workflows/deploy-codedeploy.yml`
- **Description**: Uses AWS CodeDeploy for managed deployments
- **Best for**: Production environments, rollback capabilities

### 3. **Docker-based Deployment**
- **File**: `.github/workflows/deploy-docker-ec2.yml`
- **Description**: Containerized deployment using ECR and Docker
- **Best for**: Microservices, consistent environments

## 📋 **Prerequisites**

### AWS Setup
1. **EC2 Instance** running Amazon Linux 2
2. **IAM User** with appropriate permissions
3. **Security Groups** configured for SSH access
4. **S3 Bucket** (for CodeDeploy method)
5. **ECR Repository** (for Docker method)

### GitHub Secrets Configuration
Add these secrets to your GitHub repository:

```
AWS_ACCESS_KEY_ID=your_aws_access_key
AWS_SECRET_ACCESS_KEY=your_aws_secret_key
EC2_HOST=your_ec2_public_ip_or_domain
EC2_USER=ec2-user
EC2_PRIVATE_KEY=your_private_key_content
```

## 🔧 **Setup Instructions**

### Method 1: Direct SSH Deployment

#### Step 1: Configure GitHub Secrets
```bash
# In GitHub repository settings > Secrets and variables > Actions
AWS_ACCESS_KEY_ID: Your AWS access key
AWS_SECRET_ACCESS_KEY: Your AWS secret key
EC2_HOST: 1.2.3.4 (your EC2 public IP)
EC2_USER: ec2-user
EC2_PRIVATE_KEY: |
  -----BEGIN RSA PRIVATE KEY-----
  your_private_key_content_here
  -----END RSA PRIVATE KEY-----
```

#### Step 2: Prepare EC2 Instance
```bash
# SSH to your EC2 instance
ssh -i your-key.pem ec2-user@your-ec2-ip

# Install prerequisites
sudo yum update -y
sudo yum install -y java-11-amazon-corretto-headless

# Ensure ec2-user can run sudo commands
sudo usermod -aG wheel ec2-user
```

#### Step 3: Trigger Deployment
```bash
# Push to main branch triggers automatic deployment
git push origin main

# Or manually trigger via GitHub Actions web interface
```

### Method 2: AWS CodeDeploy Setup

#### Step 1: Create IAM Roles
```bash
# CodeDeploy Service Role
aws iam create-role --role-name CodeDeployServiceRole \
  --assume-role-policy-document '{
    "Version": "2012-10-17",
    "Statement": [{
      "Effect": "Allow",
      "Principal": {"Service": "codedeploy.amazonaws.com"},
      "Action": "sts:AssumeRole"
    }]
  }'

# Attach policy
aws iam attach-role-policy \
  --role-name CodeDeployServiceRole \
  --policy-arn arn:aws:iam::aws:policy/service-role/AWSCodeDeployRole
```

#### Step 2: Create CodeDeploy Application
```bash
# Create application
aws deploy create-application \
  --application-name math-operations \
  --compute-platform Server

# Create deployment group
aws deploy create-deployment-group \
  --application-name math-operations \
  --deployment-group-name production \
  --service-role-arn arn:aws:iam::YOUR_ACCOUNT:role/CodeDeployServiceRole \
  --ec2-tag-filters Key=Name,Type=KEY_AND_VALUE,Value=math-operations
```

#### Step 3: Setup S3 Bucket
```bash
# Create S3 bucket for deployment artifacts
aws s3 mb s3://math-operations-deployment-bucket
```

### Method 3: Docker Deployment Setup

#### Step 1: Create ECR Repository
```bash
# Create ECR repository
aws ecr create-repository --repository-name math-operations

# Get login command
aws ecr get-login-password --region us-east-1 | \
  docker login --username AWS --password-stdin YOUR_ACCOUNT.dkr.ecr.us-east-1.amazonaws.com
```

#### Step 2: Prepare EC2 for Docker
```bash
# SSH to EC2 and install Docker
sudo yum update -y
sudo yum install -y docker
sudo systemctl start docker
sudo systemctl enable docker
sudo usermod -a -G docker ec2-user

# Install Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" \
  -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Install AWS CLI
sudo yum install -y aws-cli
```

## 🔄 **Deployment Workflow**

### Automatic Triggers
```yaml
# Deployments trigger on:
1. Push to main branch
2. Changes to src/** or pom.xml
3. Manual workflow dispatch
```

### Deployment Steps
1. **Build**: Compile and test Java application
2. **Package**: Create JAR file and deployment artifacts
3. **Deploy**: Transfer and deploy to EC2
4. **Verify**: Check deployment status and health
5. **Notify**: Report deployment results

## 📊 **Monitoring Deployments**

### GitHub Actions
```bash
# View deployment status
# Go to: GitHub Repository > Actions tab
# Monitor real-time deployment progress
```

### EC2 Application Status
```bash
# SSH to EC2 and check service
ssh -i your-key.pem ec2-user@your-ec2-ip
sudo systemctl status math-operations
sudo journalctl -u math-operations -f
```

### Docker Deployment Status
```bash
# Check Docker containers
docker ps
docker logs math-operations-app
```

## 🛠 **Customization Options**

### Environment-Specific Deployments
```yaml
# Modify workflow files for different environments
env:
  AWS_REGION: us-east-1  # Change region
  ENVIRONMENT: production # staging, development
```

### Custom Deployment Scripts
```bash
# Modify deploy-ec2.sh for custom deployment logic
# Add health checks, database migrations, etc.
```

### Blue-Green Deployments
```yaml
# Configure multiple deployment groups
# Switch traffic between versions
```

## 🔒 **Security Best Practices**

### IAM Permissions
```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "ec2:DescribeInstances",
        "s3:GetObject",
        "s3:PutObject",
        "codedeploy:*",
        "ecr:*"
      ],
      "Resource": "*"
    }
  ]
}
```

### Secret Management
```bash
# Rotate secrets regularly
# Use AWS Secrets Manager for production
# Limit secret access to specific workflows
```

### Network Security
```bash
# Restrict SSH access to specific IPs
# Use VPC and private subnets for production
# Enable CloudTrail for audit logging
```

## 🚨 **Troubleshooting**

### Common Issues

#### Deployment Fails - Permission Denied
```bash
# Check EC2 user permissions
sudo usermod -aG wheel ec2-user

# Verify SSH key format in GitHub secrets
# Ensure no extra spaces or characters
```

#### Service Won't Start
```bash
# Check Java installation
java -version

# Check JAR file permissions
ls -la /opt/math-operations/

# Check systemd service
sudo systemctl status math-operations
sudo journalctl -u math-operations
```

#### Docker Deployment Issues
```bash
# Check Docker daemon
sudo systemctl status docker

# Check ECR authentication
aws ecr get-login-password --region us-east-1

# Check container logs
docker logs math-operations-app
```

### Debug Commands
```bash
# GitHub Actions debugging
# Add this to workflow steps:
- name: Debug
  run: |
    echo "Current directory: $(pwd)"
    echo "Files: $(ls -la)"
    echo "Environment: $(env | grep -E 'AWS|EC2')"
```

## 📈 **Advanced Features**

### Health Checks
```bash
# Add health check endpoints
# Monitor application metrics
# Set up CloudWatch alarms
```

### Rollback Capability
```bash
# Keep previous versions
# Implement automatic rollback on failure
# Use CodeDeploy rollback features
```

### Multi-Environment Support
```yaml
# Create separate workflows for:
# - Development
# - Staging
# - Production
```

## 🎯 **Next Steps**

1. **Choose deployment method** based on your requirements
2. **Configure GitHub secrets** with your AWS credentials
3. **Set up EC2 instance** with required software
4. **Test deployment** with a small change
5. **Monitor and optimize** deployment pipeline
6. **Add monitoring and alerting** for production use

## 📚 **Additional Resources**

- [AWS CodeDeploy Documentation](https://docs.aws.amazon.com/codedeploy/)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Docker on EC2 Best Practices](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/docker-basics.html)
- [AWS CLI Reference](https://docs.aws.amazon.com/cli/)

---

**Note**: Replace placeholder values (YOUR_ACCOUNT, your-ec2-ip, etc.) with your actual AWS account details and EC2 instance information.