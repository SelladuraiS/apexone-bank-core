# ApexOne Bank Core Backend – Architecture (v1.0.0)

## Purpose

This document defines the **frozen architectural design** of the ApexOne Bank Core Backend as released in **v1.0.0**.

The system is designed as a **backend-first core banking platform**, fully independent of any UI or client implementation.  
All business rules, validations, and state transitions are enforced **exclusively by the backend**.

---

## 🧩 High-Level Architecture

The application follows a **strict layered architecture** with clear separation of concerns:

API Clients (Postman / Future UI)
↓
REST Controllers
↓
Service Layer (Core Banking Logic)
↓
Repository Layer (Persistence)
↓
Relational Database


Layer bypassing is not permitted.

---

## 🧱 Layer Responsibilities

### 1️⃣ Client Layer
**Role:** API consumer only

- Examples:
  - Postman (testing)
  - Future Web / Mobile UI
- Responsibilities:
  - Send HTTP requests
  - Receive JSON responses
- Explicitly forbidden:
  - Business logic
  - Validation duplication
  - Direct database access

---

### 2️⃣ Controller Layer
**Role:** API boundary

- Exposes REST endpoints
- Handles request routing
- Delegates logic to the service layer
- Performs only:
  - Request binding
  - Basic structural validation
- Contains **no business rules**

Examples:
- AccountController  
- ProductController  
- TransactionController  

---

### 3️⃣ Service Layer (Core Banking Layer)
**Role:** System authority

This layer represents the **core banking logic**.

Responsibilities:
- Enforce all business rules:
  - Product eligibility
  - Credit / debit permissions
  - Minimum balance constraints
  - Account state validation
- Orchestrate transactions
- Ensure atomicity and consistency
- Determine success or failure outcomes

All financial correctness is enforced here.

---

### 4️⃣ Repository Layer
**Role:** Persistence abstraction

- Uses Spring Data JPA
- Responsible for:
  - CRUD operations
  - Query execution
- Contains **no business logic**
- No cross-entity decision making

---

### 5️⃣ Database Layer
**Role:** Source of record

- Relational database
- H2 used for development
- Schema compatible with MySQL for production
- Referential integrity enforced via constraints

The database enforces **data integrity only**, not business rules.

---

## 🔄 Core System Flow

### Product → Account → Transaction Lifecycle

---

### 1️⃣ Product Initialization
- Products are initialized at application startup
- Each product defines immutable rules:
  - Credit allowed (Y/N)
  - Debit allowed (Y/N)
  - Minimum balance
  - Active status

Products act as **policy definitions**, not transactional entities.

---

### 2️⃣ Account Opening Flow
1. Client submits an account creation request
2. System validates:
   - Product existence
   - Product active status
3. Account is created with:
   - Unique account number
   - ACTIVE state
   - Linked product

Accounts cannot exist without a valid product.

---

### 3️⃣ Credit Transaction Flow
1. Client requests a credit transaction
2. Service validates:
   - Account exists and is ACTIVE
   - Amount is greater than zero
   - Product allows credit
3. CREDIT transaction is recorded
4. Effective balance increases

---

### 4️⃣ Debit Transaction Flow
1. Client requests a debit transaction
2. Service validates:
   - Account exists and is ACTIVE
   - Amount is greater than zero
   - Product allows debit
   - Minimum balance is maintained
3. DEBIT transaction is recorded
4. Effective balance decreases

---

### 5️⃣ Balance Calculation Model
- Account balance is **not stored**
- Balance is derived from the ledger:
  - Sum of all CREDIT transactions
  - Minus sum of all DEBIT transactions

This ensures:
- Auditability
- Immutability
- Ledger integrity

---

## 🎯 Design Principles (Non-Negotiable)

- Backend is the single source of truth
- Ledger-based accounting model
- Stateless REST APIs
- Clear separation of concerns
- UI-independent business logic
- No hidden side effects

---

## 🔒 Architectural Guarantees (v1.0.0)

- Business rules cannot be bypassed
- Transactions are immutable
- Products control account behavior
- UI cannot manipulate balances directly
- All failures are explicit and deterministic

---

## 🚀 Future Extensions (Out of Scope for v1.0.0)

- Authentication and authorization
- External payment integrations
- Reporting and analytics
- UI-driven workflows
- API versioning strategy

These require explicit version upgrades.

---

## Version Control

- Applicable version: **v1.0.0**
- Any architectural change requires:
  - Documentation update
  - Version increment (minor or major)
