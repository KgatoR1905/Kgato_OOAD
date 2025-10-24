package com.oakridge.financial;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String username;
    private final String password;
    private final String fullName;
    private final List<Integer> accountNumbers;

    public Customer(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.accountNumbers = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getFullName() {
        return fullName;
    }

    public List<Integer> getAccountNumbers() {
        return accountNumbers;
    }

    public void addAccountNumber(int accountNumber) {
        if (!accountNumbers.contains(accountNumber)) {
            accountNumbers.add(accountNumber);
        }
    }
}