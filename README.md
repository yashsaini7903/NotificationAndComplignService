# AI-Powered Complaint Management System

An enterprise-style complaint management backend built using **Spring Boot**, **Apache Kafka**, **MongoDB**, **JWT Authentication**, **Multithreading**, and **Google Gemini AI**.

The system is designed to handle large-scale complaint requests efficiently using an **event-driven architecture**.

---

#  Features

- JWT-based Authentication
- Complaint Submission API
- Kafka-based asynchronous processing
- Multithreaded AI complaint analysis
- Gemini API integration
- Smart complaint categorization
- Sentiment analysis
- Priority detection
- Automatic low-level issue resolution
- Department-based complaint routing
- MongoDB integration
- Scalable backend architecture

---

# 🏗️ System Architecture

```text
User
 ↓
Spring Boot REST API
 ↓
Kafka Producer
 ↓
Kafka Topic (complain.created)
 ↓
Kafka Consumer
 ↓
Thread Pool Executor
 ↓
Gemini AI Processing
 ↓
Business Logic Engine
 ↓
MongoDB + Notification Service
```

---

# ⚙️ Tech Stack

| Technology | Purpose |
|------------|---------|
| Spring Boot | Backend Framework |
| Apache Kafka | Event Streaming |
| MongoDB | Database |
| JWT | Authentication |
| Gemini API | AI Complaint Analysis |
| ExecutorService | Multithreading |
| REST APIs | Communication |
| Maven | Dependency Management |

---

# 📌 Workflow

## 1️⃣ User Authentication

Users authenticate using JWT-based login.

```text
Login → JWT Token → Authorized APIs
```

Only authenticated users can submit complaints.

---

## 2️⃣ Complaint Submission

### Endpoint

```http
POST /api/complaints
```

### Example Request

```json
{
  "subject": "Money deducted twice",
  "description": "Payment deducted but order not placed"
}
```

Backend Flow:

- Validate request
- Store complaint in MongoDB
- Publish event to Kafka

---

## 3️⃣ Kafka Producer

Complaint event is published to Kafka topic:

```text
complain.created
```

### Producer Example

```java
kafkaTemplate.send(
    "complain.created",
    event.getId(),
    event
);
```

### Why Kafka?

- Handles heavy traffic efficiently
- Prevents backend overload
- Enables asynchronous processing
- Improves scalability
- Provides fault tolerance

---

## 4️⃣ Kafka Consumer

Consumer listens to complaint events.

```java
@KafkaListener(
    topics = "complain.created",
    groupId = "complian-group"
)
public void complainProcessor(CompainCreatedEvent event) {
    aiProcessesService.processComplian(event);
}
```

---

## 5️⃣ Multithreaded AI Processing

AI processing runs asynchronously using thread pools.

```java
private final ExecutorService executor =
        Executors.newFixedThreadPool(10);
```

```java
executor.submit(() -> {
    // Gemini API call
});
```

### Why Multithreading?

Gemini API calls are IO-bound operations.

Benefits:

- Parallel complaint processing
- Better throughput
- Faster response handling
- Production-style concurrency

---

## 6️⃣ Gemini AI Integration

The system sends complaints to Gemini AI for analysis.

### Prompt Example

```text
Analyze the complaint and return JSON only.

Fields:
- sentiment
- type(FINANCE, TECH, OTHER)
- level(HIGH, MEDIUM, LOW)
- solution(if LOW else empty)
```

---

## 7️⃣ AI Response Example

```json
{
  "sentiment": "ANGRY",
  "type": "FINANCE",
  "level": "HIGH",
  "solution": ""
}
```

---

# 🧠 Smart Routing Logic

## LOW Priority

- Auto-generate solution
- Mark complaint as resolved

## HIGH Priority

Complaint routed based on category:

| Type | Department |
|------|-------------|
| FINANCE | Finance Team |
| TECH | Technical Team |
| OTHER | Support Team |

---

# 🗂️ Project Structure

```text
src/main/java/com/example/ComplainServiceApp
│
├── config
├── controllers
├── entity
├── filter
├── repository
├── services
│   ├── AiProcessesService
│   ├── ComplainConsumerService
│   ├── ComplianProducerService
│
├── utility
└── ComplainServiceAppApplication
```

---

# ⚡ Kafka Configuration

```yml
spring:
  kafka:
    bootstrap-servers: YOUR_SERVER

    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer

    consumer:
      group-id: complian-group
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer

      properties:
        spring:
          json:
            trusted:
              packages: "com.example.ComplainServiceApp.model"
```

---

#  MongoDB Configuration

```yml
spring:
  data:
    mongodb:
      uri: YOUR_MONGODB_URI
```

---

# 🔐 JWT Authentication

Features:

- User Registration
- User Login
- Token Validation
- Protected APIs
- Stateless Authentication

---

#  Scalability Features

## Event-Driven Architecture

Kafka decouples services for high scalability.

## Asynchronous Processing

Complaints are processed independently.

## Thread Pooling

Efficient resource utilization.

## Horizontal Scalability

Consumers can scale independently.

---

# 🛡️ Production-Level Concepts Used

- Kafka Event Streaming
- Consumer Groups
- Multithreading
- Async Processing
- AI Integration
- Fault Tolerance
- JWT Security
- Scalable Architecture
- Distributed System Concepts

---

#  Future Improvements

- Email Notification Service
- WebSocket Live Updates
- Admin Dashboard
- Retry Mechanism
- Dead Letter Queue (DLQ)
- Docker Deployment
- Kubernetes Scaling
- Redis Caching
- Monitoring with Prometheus & Grafana

---

# ▶️ Run the Project

## Clone Repository

```bash
git clone https://github.com/your-username/complaint-system.git
```

## Start Application

```bash
mvn spring-boot:run
```

---

#  Author

Yash Kumar
