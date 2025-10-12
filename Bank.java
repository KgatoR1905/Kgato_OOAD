import java.util.*;

public class Bank implements BankOperations, TransactionOperations {
    private String name;
    private List<Account> accounts = new ArrayList<>();
    private int nextAccountNumber = 1001;

    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public Account createAccount(String customerName, AccountType type, double initialDeposit) {
        int accNum = nextAccountNumber++;
        Account acc;
        if (type == AccountType.SAVINGS) {
            acc = new SavingsAccount(accNum, customerName, initialDeposit);
        } else {
            acc = new CurrentAccount(accNum, customerName, initialDeposit);
        }
        accounts.add(acc);
        return acc;
    }

    public void deposit(int accountNumber, double amount) {
        Account acc = findAccountByNumber(accountNumber);
        if (acc == null) throw new IllegalArgumentException("Account not found");
        acc.deposit(amount);
    }

    public void withdraw(int accountNumber, double amount) {
        Account acc = findAccountByNumber(accountNumber);
        if (acc == null) throw new IllegalArgumentException("Account not found");
        acc.withdraw(amount);
    }

    public void transfer(int fromAcc, int toAcc, double amount) {
        if (fromAcc == toAcc) throw new IllegalArgumentException("Cannot transfer to same account");
        Account from = findAccountByNumber(fromAcc);
        Account to = findAccountByNumber(toAcc);
        if (from == null || to == null) throw new IllegalArgumentException("One or both accounts not found");
        from.withdraw(amount);
        to.deposit(amount);
        addTransaction(from, "TRANSFER_OUT", amount);
        addTransaction(to, "TRANSFER_IN", amount);
    }

    public Account findAccountByNumber(int accountNumber) {
        for (Account a : accounts) {
            if (a.getAccountNumber() == accountNumber) return a;
        }
        return null;
    }

    public void listAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts yet.");
            return;
        }
        System.out.println("\nAccounts:");
        for (Account a : accounts) {
            System.out.printf("%d - %s - %.2f%n", a.getAccountNumber(), a.getCustomerName(), a.getBalance());
        }
    }

    public void addTransaction(Account account, String type, double amount) {
        if (account != null) {
            account.addTransaction(new Transaction(type, amount, account.getBalance()));
        }
    }
}

