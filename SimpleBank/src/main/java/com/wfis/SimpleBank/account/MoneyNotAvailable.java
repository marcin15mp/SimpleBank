package com.wfis.SimpleBank.account;

public class MoneyNotAvailable extends RuntimeException {
    public MoneyNotAvailable(String message) {
        super(message);
    }
}
