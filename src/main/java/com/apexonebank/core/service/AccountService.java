package com.apexonebank.core.service;

import com.apexonebank.core.domain.entity.Account;
import com.apexonebank.core.domain.entity.Product;
import com.apexonebank.core.domain.enums.AccountStatus;
import com.apexonebank.core.repository.AccountRepository;
import com.apexonebank.core.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    public AccountService(AccountRepository accountRepository,
                          ProductRepository productRepository) {
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
    }

    public Account openAccount(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (!product.isActive()) {
            throw new IllegalStateException("Cannot open account for inactive product");
        }

        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setStatus(AccountStatus.ACTIVE);
        account.setProduct(product);

        return accountRepository.save(account);
    }

    private String generateAccountNumber() {
        return UUID.randomUUID().toString();
    }
}
