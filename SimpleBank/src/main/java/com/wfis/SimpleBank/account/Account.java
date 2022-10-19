package com.wfis.SimpleBank.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    int amount;

    String ownerUserId;

    LocalDateTime createdAt;

    public Account() {
    }

    public Account( int amount, String ownerUserId, LocalDateTime createdAt) {
        this.amount = amount;
        this.ownerUserId = ownerUserId;
        this.createdAt = createdAt;
    }

    public void addAmount(int amount) {
        this.amount = this.amount + amount;
    }

    public void removeAmount(int outgoingAmount) {
        boolean isAmountAvailable = checkIsAmountAvailable(outgoingAmount);
        if(isAmountAvailable) {
            this.amount = this.amount - outgoingAmount; // jeśli kwota jest dostępna na koncie wypłaca kwote po przez przelew
        } else {
            throw new MoneyNotAvailable("Available amount is: " + this.amount + " but outgoing amount is: " + outgoingAmount);
            // w innym przypadku pojawia się powiadomienie ile pieniędzy jest dostępnych a jaką chcemy wypłacić
        }
    }
/// metody
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    private boolean checkIsAmountAvailable(int amount) {

        return this.amount < amount ? false : true;
    }

}
