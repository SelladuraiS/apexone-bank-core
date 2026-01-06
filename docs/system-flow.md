
---

## 📄 `SYSTEM_FLOW.md`

```md
# ApexOne Bank Core Backend – System Flow (v1.0.0)

## Overview

This document describes the **end-to-end functional flow** of the ApexOne Bank Core Backend.

It explains how products, accounts, and transactions interact within the system.

---

## 1️⃣ Product Lifecycle Flow

1. Products are initialized or created via API
2. Each product defines:
   - Credit permission
   - Debit permission
   - Minimum balance
   - Active status
3. Products act as **rule carriers**
4. Products cannot be deleted once used by accounts

---

## 2️⃣ Account Opening Flow

1. Client submits account creation request
2. System validates:
   - Product existence
   - Product active status
3. Account is created with:
   - Unique account number
   - ACTIVE status
   - Linked product
4. Initial balance is zero
5. Account becomes immediately eligible for transactions

---

## 3️⃣ Credit Transaction Flow

1. Client submits credit request
2. System validates:
   - Account exists
   - Account is ACTIVE
   - Amount > 0
   - Product allows credit
3. CREDIT transaction is persisted
4. Ledger reflects increased balance
5. Response returned to client

---

## 4️⃣ Debit Transaction Flow

1. Client submits debit request
2. System validates:
   - Account exists
   - Account is ACTIVE
   - Amount > 0
   - Product allows debit
   - Minimum balance is preserved
3. DEBIT transaction is persisted
4. Ledger reflects reduced balance
5. Response returned to client

---

## 5️⃣ Balance Calculation Flow

1. Balance is not stored in account table
2. Balance is derived by:
   - Summing all CREDIT transactions
   - Subtracting all DEBIT transactions
3. Ensures:
   - Ledger integrity
   - Auditability
   - No balance manipulation

---

## 6️⃣ Failure Handling Flow

- Any validation failure:
  - Stops processing immediately
  - Returns FAILURE response
- No partial updates
- No silent corrections
- No implicit retries

---

## 7️⃣ System Guarantees

- Transactions are immutable
- Business rules enforced centrally
- UI cannot override backend decisions
- All state changes are explicit

---

## Scope Control

Applies strictly to:
- ApexOne Bank Core Backend
- Version **v1.0.0**

Any deviation requires version upgrade and documentation update.
