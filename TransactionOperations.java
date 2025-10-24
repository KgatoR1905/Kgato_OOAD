package com.oakridge.financial;

public interface TransactionOperations {
    
    void addTransaction(Account account, String type, double amount);
}