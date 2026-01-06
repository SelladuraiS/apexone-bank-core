# ApexOne Bank Core Backend – API Contract (v1.0.0)

## Purpose

This document defines the **public API contract** exposed by the ApexOne Bank Core Backend.

This contract is **binding** for all API consumers, including future UI applications.
Once released, this contract must not be altered without a version change.

---

## General Rules

- All APIs are synchronous
- JSON request / response format
- Backend is the single source of truth
- UI must not:
  - Calculate balances
  - Apply business rules
  - Assume success without response confirmation

---

## Common Response Structure

### Success Response
```json
{
  "status": "SUCCESS",
  "code": "SUCCESS",
  "message": "Operation completed successfully",
  "data": {}
}

Failure Response

{
  "status": "FAILURE",
  "code": "ERROR_CODE",
  "message": "Reason for failure"
}

HTTP Status Usage

200 OK → Request processed (success or business failure)

400 Bad Request → Invalid request structure

404 Not Found → Resource not found

500 Internal Server Error → System error

Business rule failures are returned as 200 with FAILURE status.

Core APIs
1️⃣ Create Product

Endpoint

POST /api/products


Request Body

{
  "productName": "Savings Account",
  "allowCredit": true,
  "allowDebit": true,
  "minimumBalance": 1000,
  "active": true
}


Rules

Product name must be unique

Product rules are immutable after creation

2️⃣ Create Account

Endpoint

POST /api/accounts


Request Body

{
  "customerId": "CIF12345",
  "productId": 1
}


Validations

Product must exist

Product must be active

Customer ID must be present

Behavior

Creates a unique account number

Sets account status to ACTIVE

Links account to product

3️⃣ Credit Transaction

Endpoint

POST /api/transactions/credit


Request Body

{
  "accountNumber": "ACC1000001",
  "amount": 5000
}


Validations

Account exists and is ACTIVE

Amount > 0

Product allows credit

Behavior

Records immutable CREDIT transaction

Balance increases logically

4️⃣ Debit Transaction

Endpoint

POST /api/transactions/debit


Request Body

{
  "accountNumber": "ACC1000001",
  "amount": 2000
}


Validations

Account exists and is ACTIVE

Amount > 0

Product allows debit

Minimum balance maintained

Behavior

Records immutable DEBIT transaction

Balance decreases logically

5️⃣ Get Account Balance

Endpoint

GET /api/accounts/{accountNumber}/balance


Behavior

Balance is calculated from ledger

No stored balance is returned

Error Codes (Sample)
Code	Meaning
PRODUCT_NOT_FOUND	Invalid product ID
PRODUCT_INACTIVE	Product is disabled
ACCOUNT_NOT_FOUND	Invalid account
INSUFFICIENT_BALANCE	Minimum balance violation
CREDIT_NOT_ALLOWED	Credit restricted
DEBIT_NOT_ALLOWED	Debit restricted
INVALID_AMOUNT	Amount ≤ 0