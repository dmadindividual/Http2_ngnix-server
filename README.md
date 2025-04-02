# HTTP/2 Testing with Spring Boot and Nginx (Dockerized)

## Overview
This project demonstrates how to test HTTP/1.1 and HTTP/2 using **Spring Boot**, **Nginx**, and **Docker**. It includes setting up an Nginx reverse proxy with SSL to handle HTTP/2 requests while the Spring Boot application serves as the backend API.

## Features
- Supports **HTTP/1.1** and **HTTP/2**
- Uses **Spring Boot** as the backend
- Uses **Nginx** as a reverse proxy
- Configured with **SSL** for secure connections
- **Dockerized** for easy deployment and testing

## Prerequisites
Ensure you have the following installed:
- **Java 17+**
- **Maven**
- **Docker & Docker Compose**
- **Postman or cURL** for testing

## Project Structure
```
├── src/main/java/com/example/httpdemo
│   ├── HttpDemoApplication.java  # Main Spring Boot application
│   ├── controller
│   │   ├── FileController.java  # API Endpoints
│   ├── config
│   │   ├── WebConfig.java  # Configures HTTP
├── nginx
│   ├── nginx.conf  # Nginx configuration file
├── docker-compose.yml  # Docker setup for Nginx and Spring Boot
├── Dockerfile  # Docker build for Spring Boot
├── README.md  # Project Documentation
```

## Setup & Installation
### 1. Clone the Repository
```bash
git clone https://github.com/your-repo/http2-nginx-springboot.git
cd http2-nginx-springboot
```

### 2. Build the Spring Boot Application
```bash
mvn clean package
```

### 3. Run the Project with Docker
```bash
docker-compose up --build
```

## Configuration Details
### **Spring Boot API Endpoints**
| Method | Endpoint               | Description          |
|--------|------------------------|----------------------|
| GET    | `/api/file1`           | Returns File 1      |
| GET    | `/api/file2`           | Returns File 2      |

### **Nginx Configuration (nginx.conf)**
- Listens on **port 443** for HTTPS
- Uses **HTTP/2** for better multiplexing
- Proxies requests to **Spring Boot** running on **port 8080**

### **Testing HTTP/1.1 and HTTP/2**
#### Using cURL
- Test HTTP/1.1:
  ```bash
  curl -I --http1.1 http://localhost:8080/api/file1
  ```
- Test HTTP/2:
  ```bash
  curl -I --http2 https://localhost/api/file1
  ```

#### Using Postman
- Open **Postman**
- Go to **Settings > General > Enable HTTP/2**
- Send a request to `https://localhost/api/file1`
- Check **response headers** for `HTTP/2`

## Troubleshooting
- If **HTTP/2** is not working, check if **cURL supports HTTP/2**:
  ```bash
  curl --version
  ```
- Ensure **Nginx** is running properly:
  ```bash
  docker logs nginx-container
  ```

## License
This project is licensed under the MIT License.

## Author
**Falade Ayomide**

