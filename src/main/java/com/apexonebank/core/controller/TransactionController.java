package com.apexonebank.core.controller;

import com.apexonebank.core.domain.entity.Transaction;
import com.apexonebank.core.service.TransactionService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Validated
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/credit")
    public Transaction credit(
            @RequestParam
            @NotNull(message = "Account ID must be provided")
            @Positive(message = "Account ID must be positive")
            Long accountId,

            @RequestParam
            @Positive(message = "Credit amount must be positive")
            double amount) {

        return transactionService.credit(accountId, amount);
    }

    @PostMapping("/debit")
    public Transaction debit(
            @RequestParam
            @NotNull(message = "Account ID must be provided")
            @Positive(message = "Account ID must be positive")
            Long accountId,

            @RequestParam
            @Positive(message = "Debit amount must be positive")
            double amount) {

        return transactionService.debit(accountId, amount);
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getByAccount(
            @PathVariable
            @NotNull(message = "Account ID must be provided")
            @Positive(message = "Account ID must be positive")
            Long accountId) {

        return transactionService.getTransactions(accountId);
    }
}
