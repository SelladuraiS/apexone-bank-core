# ApexOne Bank Core — System Flow

## Product → Account → Transaction Flow

### 1. Product Initialization
- Products are created at application startup
- Each product defines:
  - Credit permission
  - Debit permission
  - Minimum balance
  - Active status

---

### 2. Account Opening Flow
1. Client requests account creation with a product ID
2. System verifies:
   - Product exists
   - Product is active
3. Account is created with:
   - Unique account number
   - ACTIVE status
   - Linked product

---

### 3. Credit Transaction Flow
1. Client requests credit
2. System verifies:
   - Account exists and is ACTIVE
   - Credit amount is positive
   - Product allows credit
3. Transaction is recorded as CREDIT
4. Balance increases

---

### 4. Debit Transaction Flow
1. Client requests debit
2. System verifies:
   - Account exists and is ACTIVE
   - Debit amount is positive
   - Product allows debit
   - Minimum balance is maintained
3. Transaction is recorded as DEBIT
4. Balance decreases

---

### 5. Balance Calculation
- Balance is not stored
- Balance is calculated by summing:
  - All CREDIT transactions
  - Minus all DEBIT transactions

This ensures ledger integrity.
