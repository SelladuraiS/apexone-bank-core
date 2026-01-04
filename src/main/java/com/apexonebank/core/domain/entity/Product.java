package com.apexonebank.core.domain.entity;

import com.apexonebank.core.domain.enums.ProductType;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ProductType productType;

    @Column(nullable = false)
    private boolean allowCredit;

    @Column(nullable = false)
    private boolean allowDebit;

    @Column(nullable = false)
    private double minimumBalance;

    @Column(nullable = false)
    private boolean interestApplicable;

    @Column(nullable = false)
    private boolean active;

    // ----- Getters and setters -----
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public boolean isAllowCredit() {
        return allowCredit;
    }

    public void setAllowCredit(boolean allowCredit) {
        this.allowCredit = allowCredit;
    }

    public boolean isAllowDebit() {
        return allowDebit;
    }

    public void setAllowDebit(boolean allowDebit) {
        this.allowDebit = allowDebit;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public boolean isInterestApplicable() {
        return interestApplicable;
    }

    public void setInterestApplicable(boolean interestApplicable) {
        this.interestApplicable = interestApplicable;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
