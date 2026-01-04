# ApexOne Bank Core – Architecture Documentation

This document describes the high-level architecture of the ApexOne Bank Core backend system.

The system is designed as a **backend-first core banking API**, independent of any UI, following layered architecture principles and clear separation of concerns.

---

## 🧩 High-Level Architecture

The application follows a classic layered architecture:

Client (Postman / Future UI)
↓
REST Controllers
↓
Service Layer (Business Rules)
↓
Repository Layer (JPA)
↓
Database (H2 / MySQL)

---

## 🧱 Layer Responsibilities

### 1. Client Layer
- API consumers such as:
  - Postman (for testing)
  - Future Web or Mobile UI
- Sends HTTP requests and receives JSON responses
- No business logic present

---

### 2. Controller Layer
- Exposes REST endpoints
- Handles request routing
- Delegates all logic to services
- Does not contain business rules

Examples:
- AccountController
- ProductController
- TransactionController

---

### 3. Service Layer
- Core of the banking system
- Enforces all business rules, such as:
  - Product eligibility
  - Credit/debit permissions
  - Minimum balance constraints
- Orchestrates transactions
- Ensures consistency and correctness

This layer represents the **core banking logic**.

---

### 4. Repository Layer
- Handles persistence using Spring Data JPA
- Abstracts database operations
- No business logic
- Provides CRUD and query access

---

### 5. Database Layer
- Uses H2 in-memory database for development
- Automatically bootstrapped at application startup
- Schema generated from JPA entities
- Initial product data loaded via `data.sql`

Designed to be easily replaceable with MySQL or another RDBMS in production.

---

## 🔄 Core Transaction Flow (Example)

1. Client requests account creation for a selected product
2. Controller forwards request to AccountService
3. Service validates product and creates account
4. Client performs credit or debit transaction
5. TransactionService enforces:
   - Product permissions
   - Balance rules
6. Transaction entry is persisted as an immutable ledger record
7. Response returned to client

---

## 🎯 Design Principles

- Backend-first design
- Domain-driven modeling
- Clear separation of concerns
- Stateless REST APIs
- Ledger-based transaction recording
- UI-independent core system

---

## 🚀 Future Extensions

- Replace H2 with MySQL for production
- Add authentication and authorization
- Introduce DTOs for API versioning
- Integrate with a separate UI application
