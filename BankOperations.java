package com.oakridge.financial;

public interface BankOperations {
    
    // Account Management
    Account createAccount(String customerName, AccountType type, double initialDeposit);
    Account findAccountByNumber(int accountNumber);
    void listAllAccounts();
    
    // Fund Movement (Already partially defined)
    void deposit(int accountNumber, double amount);
    void withdraw(int accountNumber, double amount);
    void transfer(int fromAcc, int toAcc, double amount);
}