# Multi-stage build for Java application
FROM maven:3.8.6-openjdk-11-slim AS build

# Set working directory
WORKDIR /app

# Copy Maven files for dependency resolution
COPY pom.xml .

# Download dependencies (cached layer if pom.xml doesn't change)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:11-jre-slim

# Create app user
RUN useradd -r -s /bin/false mathapp

# Set working directory
WORKDIR /app

# Create logs directory
RUN mkdir -p /app/logs && chown mathapp:mathapp /app/logs

# Copy JAR from build stage
COPY --from=build /app/target/math-operations-*.jar app.jar

# Change ownership
RUN chown mathapp:mathapp app.jar

# Switch to non-root user
USER mathapp

# Expose port (if needed for future web interface)
# EXPOSE 8080

# Set JVM options
ENV JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseG1GC"

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
  CMD java -version || exit 1

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]