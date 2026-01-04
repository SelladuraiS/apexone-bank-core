package com.apexonebank.core.service;

import com.apexonebank.core.domain.entity.Account;
import com.apexonebank.core.domain.entity.Transaction;
import com.apexonebank.core.domain.enums.AccountStatus;
import com.apexonebank.core.domain.enums.TransactionType;
import com.apexonebank.core.repository.AccountRepository;
import com.apexonebank.core.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {
    
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository,
                              TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction credit(Long accountId, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Credit amount must be positive");
        }

        Account account = getActiveAccount(accountId);

        if (!account.getProduct().isAllowCredit()) {
            throw new IllegalStateException("Credit not allowed for this product");
        }

        Transaction txn = new Transaction();
        txn.setAccount(account);
        txn.setType(TransactionType.CREDIT);
        txn.setAmount(amount);

        return transactionRepository.save(txn);
    }

    @Transactional
    public Transaction debit(Long accountId, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Debit amount must be positive");
        }

        Account account = getActiveAccount(accountId);

        if (!account.getProduct().isAllowDebit()) {
            throw new IllegalStateException("Debit not allowed for this product");
        }

        double balance = calculateBalance(account);

        if (balance - amount < account.getProduct().getMinimumBalance()) {
            throw new IllegalStateException("Insufficient balance considering minimum balance");
        }

        Transaction txn = new Transaction();
        txn.setAccount(account);
        txn.setType(TransactionType.DEBIT);
        txn.setAmount(amount);

        return transactionRepository.save(txn);
    }

    public double calculateBalance(Account account) {

        List<Transaction> transactions =
                transactionRepository.findByAccount(account);

        return transactions.stream()
                .mapToDouble(t ->
                        t.getType() == TransactionType.CREDIT
                                ? t.getAmount()
                                : -t.getAmount())
                .sum();
    }

    public List<Transaction> getTransactions(Long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        return transactionRepository.findByAccount(account);
    }

    private Account getActiveAccount(Long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account is not active");
        }

        return account;
    }
}
