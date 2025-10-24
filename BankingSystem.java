package com.oakridge.financial;

public class BankingSystem {

    public static final Bank BANK = new Bank("Oakridge Financial");
    public static final CustomerService CUSTOMER_SERVICE = new CustomerService(BANK);

    public static void main(String[] args) {
        
        try {
            Account initialAcc = BANK.createAccount("Jane Doe", AccountType.SAVINGS, 500.00);
            CUSTOMER_SERVICE.linkAccountToCustomer("user1", initialAcc.getAccountNumber());
        } catch (Exception e) {
            System.err.println("Initial data setup failed: " + e.getMessage());
        }

        MainMenuView.showMenu();
    }
}