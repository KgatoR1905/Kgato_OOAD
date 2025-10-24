package com.oakridge.financial;

import java.util.List;
import java.util.ArrayList;

public abstract class Account {
    protected int accountNumber;
    protected String customerName;
    protected double balance;
    protected List<Transaction> transactions = new ArrayList<>();

    public Account(int accNum, String name, double initialDeposit) {
        this.accountNumber = accNum;
        this.customerName = name;
        this.balance = 0.0;
        if (initialDeposit > 0) deposit(initialDeposit);
    }

    public int getAccountNumber() { return accountNumber; }
    public String getCustomerName() { return customerName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
        addTransaction(new Transaction("DEPOSIT", amount, balance));
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
        if (amount > balance) throw new IllegalArgumentException("Insufficient funds");
        balance -= amount;
        addTransaction(new Transaction("WITHDRAW", amount, balance));
    }

    public void addTransaction(Transaction t) { transactions.add(t); }

    public String getFullDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account number: ").append(accountNumber).append('\n');
        sb.append("Customer: ").append(customerName).append('\n');
        sb.append("Type: ").append(getClass().getSimpleName()).append('\n');
        sb.append(String.format("Balance: %.2f%n", balance));
        sb.append("Transactions:\n");
        for (Transaction t : transactions) sb.append("  ").append(t).append('\n');
        return sb.toString();
    }
}