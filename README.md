# 🏋️‍♂️ Fit-AI  
> Your Personal AI-Powered Fitness & Health Companion 💪  

![Fit-AI Banner](https://img.shields.io/badge/Fit--AI-Smart%20Fitness%20Assistant-brightgreen?style=for-the-badge&logo=github)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.0-brightgreen?style=flat&logo=springboot)  
![Java](https://img.shields.io/badge/Java-21-blue?style=flat&logo=java)  
![React](https://img.shields.io/badge/React-18-61DAFB?style=flat&logo=react)  
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-DB-blue?style=flat&logo=postgresql)  

---

## 📌 Overview  
**Fit-AI** is an intelligent fitness and health tracking application that combines **AI-powered recommendations** with **real-time progress monitoring**.  
Whether you want to **track workouts**, **monitor diet**, or get **personalized insights**, Fit-AI is your all-in-one fitness companion.  

✨ **Key Features**:  
- 🔐 Secure **User Registration & Authentication**  
- 📊 **AI-based Workout Recommendations**  
- 🥗 **Diet & Nutrition Suggestions**  
- 🏃 **Progress Tracking & Analytics Dashboard**  
- ⚡ Built with **Spring Boot (Backend)** + **React (Frontend)** + **PostgreSQL (Database)**  

---

## 🚀 Tech Stack  
| Layer | Technology |
|-------|-------------|
| **Backend** | Spring Boot, Java 21, JPA, Hibernate |
| **Frontend** | React, TailwindCSS, Axios |
| **Database** | PostgreSQL + HikariCP |
| **Tools** | Docker, Postman, Maven |
| **Testing** | JUnit 5, Mockito |

---

## 📂 Project Structure  
fit-ai/
│── backend/ (Spring Boot Services)
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   ├── exception/
│   └── dto/
│
│── frontend/ (React App)
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── hooks/
│   │   └── utils/
│
│── docs/ (Documentation + Diagrams)
│── README.md

---



## ⚙️ Installation & Setup  

### 🔹 Backend (Spring Boot)  
```bash
cd backend
mvn spring-boot:run


Step-by-Step Guide: API → Kafka → DB using Docker
Step 1: Docker Setup
Docker install karo (Mac me Homebrew ya official website se).

bash
Copy code
brew install --cask docker
Docker start karo. GUI me whale icon se ya CLI me docker info check karo.

Step 2: Zookeeper Start
Kafka ko run karne ke liye Zookeeper chahiye. Docker me image pull karo aur container start karo:

bash
Copy code
docker pull wurstmeister/zookeeper
docker run -d --name zookeeper -p 2181:2181 wurstmeister/zookeeper
2181 → default port for Zookeeper

-d → detached mode

✅ Zookeeper start hone ke baad, Kafka ko iske upar start karenge.

Step 3: Kafka Start
Kafka Docker image run karo aur Zookeeper connect karo:

bash
Copy code
docker pull wurstmeister/kafka
docker run -d --name kafka -p 9092:9092 \
  -e KAFKA_ZOOKEEPER_CONNECT=host.docker.internal:2181 \
  -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
  wurstmeister/kafka
KAFKA_ZOOKEEPER_CONNECT → Zookeeper ka address

KAFKA_ADVERTISED_LISTENERS → Kafka port for clients

KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 → local testing

✅ Ab Kafka ready hai.

Step 4: Kafka Partition & Topic Creation
Kafka me topic create karna hai jahan aapka data jaayega:

bash
Copy code
docker exec -it kafka kafka-topics.sh --create \
  --topic order-topic \
  --bootstrap-server localhost:9092 \
  --partitions 1 \
  --replication-factor 1
order-topic → jaha API data publish hoga

partitions=1 → simple setup, scalable later

✅ Topic ready hai.

Step 5: API Service (Spring Boot / Node / Python)
Spring Boot app banao jo REST API expose kare:

Endpoint: /place-order

Input: JSON data (order details)

API me Kafka Producer configure karo:

Spring Boot me spring-kafka dependency add karo

Kafka Template use karke data order-topic me bhejo

Example (Spring Boot Kafka Producer):

java
Copy code
@Autowired
private KafkaTemplate<String, String> kafkaTemplate;

public void sendOrder(String orderJson) {
    kafkaTemplate.send("order-topic", orderJson);
}
Step 6: Dockerize Your API
Dockerfile create karo:

dockerfile
Copy code
FROM openjdk:17
COPY target/order-api.jar order-api.jar
ENTRYPOINT ["java","-jar","order-api.jar"]
Docker build & run:

bash
Copy code
docker build -t order-api .
docker run -d --name order-api -p 8080:8080 order-api
✅ API container ready hai.

Step 7: Kafka Consumer → DB
Another service banao ya same API me Kafka Consumer add karo:

java
Copy code
@KafkaListener(topics = "order-topic", groupId = "order-group")
public void consumeOrder(String orderJson) {
    // Parse JSON and save to DB
}
DB container run karo (MySQL example):

bash
Copy code
docker run --name mysql-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=orders -p 3306:3306 -d mysql:8
Kafka consumer connect karke order-topic se data read karke DB me save karo.

Step 8: Test Flow
Postman / CURL se API hit karo:

bash
Copy code
POST http://localhost:8080/place-order
Body: { "orderId": 101, "item": "Laptop", "qty": 1 }
Kafka producer message bhejega → topic me jayega

Kafka consumer message read karega → DB me save ho jayega

✅ End-to-End flow ready: API → Kafka → DB
