# Example of a CQRS Implementation

A Spring Boot implementation of CQRS pattern for bank account management using Kafka event streaming with MongoDB event store and MySQL query store.

## 📋 Overview

This project demonstrates **Command Query Responsibility Segregation (CQRS)** pattern with:
- **Command Side**: Handles write operations (account creation/deposit/withdraw) using MongoDB event store
- **Query Side**: Handles read operations (balance checks) using MySQL projection
- **Event Streaming**: Apache Kafka for real-time synchronization
- **Event Replay**: API to rebuild read store from event history

## Prerequisites

- Java 17+
- Maven 4.0+
- Docker Desktop
- MongoDB
- MySQL
- Apache Kafka
- Zookeeper

## Installation
1. Clone repository:
```shell
git clone https://github.com/alilachguer/cqrs-example.git
cd cqrs-bank
```

2. Start infrastructure:
```shell
docker compose up -d
```

3. Build project:
```shell
mvn clean install
```

4. Run applications:
```shell
# Command service (port 5000)
java -jar bank-account/account.cmd/target/account.cmd-1.0.0.jar

# Query service (port 5001) 
java -jar bank-account/account.query/target/account.query-1.0.0.jar
```

## Event Flow Sequence
1. Client sends command to create/deposit/withdraw (via the defined RestControllers)
2. Command service persists event in MongoDB
3. Event published to Kafka `BankAccountEvents` topic
4. Query service consumes event and updates MySQL
5. Read APIs serve data from optimized projection

## References
- [Baeldung CQRS Guide](https://www.baeldung.com/cqrs-event-sourcing-java)
- [Master Spring Boot Microservices with CQRS & Event Sourcing](https://www.udemy.com/course/master-spring-boot-microservices-with-cqrs-event-sourcing/)
