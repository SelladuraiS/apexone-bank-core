package com.apexonebank.core.controller;

import com.apexonebank.core.domain.entity.Account;
import com.apexonebank.core.service.AccountService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@Validated
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/open")
    public Account openAccount(
            @RequestParam
            @NotNull(message = "Product ID must be provided")
            @Positive(message = "Product ID must be positive")
            Long productId) {

        return accountService.openAccount(productId);
    }
}
