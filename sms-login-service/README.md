# SMS Login Service

A Spring Boot-based login service for the School Management System (SMS). This service validates user credentials using a relational database.

## Features

- RESTful login endpoint
- User credential validation with JDBC
- Input validation and error handling
- Logging for traceability
- Eureka client for service discovery

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Web
- Spring JDBC
- MySQL / Oracle (JDBC support)
- Eureka Client
- Lombok

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- MySQL or Oracle database
- Eureka server (optional, for service discovery)

### Setup

1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   cd sms-login-service