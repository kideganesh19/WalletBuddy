# 💰 WalletBuddy

A scalable and modular **eWallet system** built using **Spring Boot microservices architecture**, designed to handle user onboarding, wallet transactions, rewards, and secure authentication with modern backend practices.

---

## 🚀 Tech Stack

- **Java**, **Spring Boot**, **Spring MVC**
- **Spring Security** + **JWT**
- **Kafka** (for asynchronous communication)
- **MySQL** (relational database)
- **Email API** (transactional notifications)

---

## 🧩 Microservices Overview

WalletBuddy consists of 4 core microservices:

1. **User Service** – Handles onboarding, authentication, and profile management.
2. **Wallet Service** – Manages wallet balance, credits/debits, and transaction logs.
3. **Reward Service** – Generates and assigns user rewards based on wallet activity.
4. **Notification Service** – Sends transactional emails and User creation emails via integrated mail APIs.

All services communicate via **Kafka topics**, ensuring loose coupling and resilience.

---

## 🔐 Key Features

- ✅ Modular microservices architecture with independent deployment support.
- ✅ User **authentication & authorization** via Spring Security + JWT.
- ✅ Asynchronous communication with **Kafka** for improved scalability.
- ✅ Real-time **email notifications** for transactions.
- ✅ Optimized **MySQL schema** 
- ✅ Clean separation using **Spring MVC** and layered architecture.

---

## ⚙️ Project Setup (Local Development)

### Prerequisites

- Java 17+
- MySQL
- Kafka

### Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/walletbuddy.git
   cd walletbuddy
