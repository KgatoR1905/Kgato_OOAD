package com.oakridge.financial;

import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private final Map<String, Customer> registeredCustomers = new HashMap<>();
    private Customer currentCustomer = null;
    private final Bank bank;

    public CustomerService(Bank bank) {
        this.bank = bank;
        Customer testUser = new Customer("user1", "pass", "Jane Doe");
        registeredCustomers.put(testUser.getUsername(), testUser);
    }

    public Customer login(String username, String password) throws IllegalArgumentException {
        Customer customer = registeredCustomers.get(username);

        if (customer == null) {
            throw new IllegalArgumentException("Username not found.");
        }

        if (!customer.checkPassword(password)) {
            throw new IllegalArgumentException("Invalid password.");
        }

        this.currentCustomer = customer;
        System.out.println(customer.getUsername() + " logged in successfully.");
        return customer;
    }

    public void logout() {
        if (currentCustomer != null) {
            System.out.println(currentCustomer.getUsername() + " logged out.");
        }
        this.currentCustomer = null;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }
    
    public boolean isLoggedIn() {
        return currentCustomer != null;
    }

    public Customer createCustomerAndAccount(String username, String password, String fullName, AccountType type, double initialDeposit) throws IllegalArgumentException {
        
        if (registeredCustomers.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists. Please choose another.");
        }

        Customer newCustomer = new Customer(username, password, fullName);
        Account newAccount = bank.createAccount(fullName, type, initialDeposit);
        newCustomer.addAccountNumber(newAccount.getAccountNumber());
        registeredCustomers.put(username, newCustomer);
        
        System.out.println("New customer created: " + username + " with Account: " + newAccount.getAccountNumber());
        
        return newCustomer;
    }
    
    public void linkAccountToCustomer(String username, int accountNumber) {
        Customer customer = registeredCustomers.get(username);
        if (customer != null && bank.findAccountByNumber(accountNumber) != null) {
             customer.addAccountNumber(accountNumber);
        }
    }
}