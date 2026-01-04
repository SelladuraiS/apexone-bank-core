package com.apexonebank.core.controller;

import com.apexonebank.core.domain.entity.Transaction;
import com.apexonebank.core.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
   
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/credit")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction credit(@RequestParam Long accountId,
                              @RequestParam double amount) {

        return transactionService.credit(accountId, amount);
    }

    @PostMapping("/debit")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction debit(@RequestParam Long accountId,
                             @RequestParam double amount) {

        return transactionService.debit(accountId, amount);
    }
}
