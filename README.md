# Messaging Queue Application

## Overview

The **Messaging Queue Application** is a robust, Java-based message queue system built using **Spring Boot**. It follows the **Producer-Consumer** model, where the **Producer** creates messages and the **Consumer** processes them, all while interacting with an in-memory message queue. This application is built for developers to explore the fundamentals of message handling in a controlled, simple environment.

### Key Features

- **Message Production**: Simulates creating messages and adding them to the queue.
- **Message Consumption**: Retrieves messages from the queue and processes them, handling both successful and erroneous messages.
- **Unit Testing**: Includes tests with near 100% coverage using **JUnit 5** and **Mockito**.
- **Spring Boot Architecture**: Utilizes Spring Boot for its simplicity and scalability in building standalone applications.

---

## Table of Contents

- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Message Flow](#message-flow)
- [Testing](#testing)
---

## Architecture

The architecture is designed around the **Producer-Consumer** pattern:

- **Producer**: Responsible for creating `Message` objects and placing them in the `MessageQueue`.
- **Consumer**: Retrieves and processes `Message` objects from the queue.
- **MessageQueue**: A FIFO queue that holds messages for the consumer to process.

### Architectural Diagram

```plaintext
+----------+       +--------------+       +---------+
| Producer | ----> | MessageQueue  | ----> | Consumer|
+----------+       +--------------+       +---------+
```
### Technologies Used
- **Java 17**: Language for building the core application.
- **Spring Boot 3.x**: Used for setting up the application framework.
- **JUnit 5**: For unit testing.
- **Mockito**: For mocking dependencies in tests.
- **Maven**: Project management and build tool.

### Installation
#### Prerequisites
- Java 17 or later
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, etc.)

### Steps
- Clone the repository:

```
git clone https:https://github.com/ShubhamNesnatkar/SimpleMessaging.git
```
- Build the project:
```
mvn clean install
Run the application:
mvn spring-boot:run
```
### Message Flow
#### Producer:
Creates a new Message object with content.
Calls MessageQueue.enqueue() to add the message to the queue.
#### Consumer:
Fetches the message using MessageQueue.dequeue().
Increments success or error counters based on whether message processing is successful.

### Test Coverage
- ProducerTest: Verifies message creation and proper enqueueing behavior.
- ConsumerTest: Tests message consumption and error handling.
- MessageQueueApplicationTests: Tests the overall message flow from producer to consumer.

### Note
```
We can use Kafka, RabbitMQ, or ActiveMQ for a more robust messaging queue system.
Its just a simple implementation of a messaging queue system with Java.
```
### Contributor
[Shubham Nesnatkar]
