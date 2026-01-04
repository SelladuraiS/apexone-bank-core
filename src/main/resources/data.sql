INSERT INTO products
(id, product_name, product_type, allow_credit, allow_debit, minimum_balance, interest_applicable, active)
VALUES
(1, 'Savings Account', 'SAVINGS_ACCOUNT', true, true, 1000, true, true),
(2, 'Current Account', 'CURRENT_ACCOUNT', true, true, 0, false, true),
(3, 'Fixed Deposit', 'FIXED_DEPOSIT', true, false, 0, true, true);
