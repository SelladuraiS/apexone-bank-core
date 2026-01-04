package com.apexonebank.core.controller;

import com.apexonebank.core.domain.entity.Account;
import com.apexonebank.core.domain.entity.Product;
import com.apexonebank.core.repository.ProductRepository;
import com.apexonebank.core.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    
    private final AccountService accountService;
    private final ProductRepository productRepository;

    public AccountController(AccountService accountService,ProductRepository productRepository) {
        this.accountService = accountService;
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account openAccount(@RequestParam Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        return accountService.openAccount(product);
    } 
}
