# ApexOne Bank Core

A backend-focused core banking system built with Spring Boot, designed to model real-world banking products, accounts, and ledger-based transactions.

This project demonstrates clean domain modeling, service-layer business rule enforcement, and REST API design without relying on any frontend UI.

---

## 🏦 Banking Domain Overview

ApexOne Bank Core supports:

- Bank products (Savings, Current, Fixed Deposit)
- Customer accounts linked to products
- Ledger-based credit and debit transactions
- Minimum balance and product-level rules

The system enforces business rules at the service layer to ensure transactional consistency and prevent invalid financial operations.

---

## 🧱 Architecture

controller → service → repository → database

- **Controller**: Exposes REST APIs
- **Service**: Enforces banking rules and transaction logic
- **Repository**: JPA-based data access
- **Database**: H2 in-memory database (auto-bootstrapped)

---

## 🗃️ Core Entities

### **Product**
- Product type (Savings / Current / Fixed Deposit)
- Credit and debit permissions
- Minimum balance rules
- Active / inactive status

### **Account**
- Linked to a banking product
- Unique account number
- Account status (ACTIVE, BLOCKED, CLOSED, etc.)

### **Transaction**
- Immutable ledger entries
- Credit / Debit type
- Timestamped
- No updates after creation (ledger-style design)

---

## 🔗 API Endpoints

### Products
- `GET /api/products`

### Accounts
- `POST /api/accounts/open?productId={id}`

### Transactions
- `POST /api/transactions/credit?accountId={id}&amount={amount}`
- `POST /api/transactions/debit?accountId={id}&amount={amount}`
- `GET /api/transactions/account/{accountId}`

---

## 🧪 API Testing

All APIs are tested using **Postman**, including:

- Successful transaction flows
- Negative cases such as:
  - Insufficient balance
  - Invalid debit operations
  - Inactive or restricted products

A Postman collection is saved for reference and validation.

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 In-Memory Database
- Maven

---

## ▶️ Running the Application

```bash
mvn spring-boot:run

Application URL: http://localhost:8080

H2 Console: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:apexone