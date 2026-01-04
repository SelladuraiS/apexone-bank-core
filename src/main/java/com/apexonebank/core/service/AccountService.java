package com.apexonebank.core.service;

import com.apexonebank.core.domain.entity.Account;
import com.apexonebank.core.domain.entity.Product;
import com.apexonebank.core.domain.enums.AccountStatus;
import com.apexonebank.core.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account openAccount(Product product) {

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
