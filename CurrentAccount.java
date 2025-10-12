public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 0.0;

    public CurrentAccount(int accNum, String name, double init) {
        super(accNum, name, init);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < -OVERDRAFT_LIMIT)
            throw new IllegalArgumentException("Insufficient funds for current account");
        super.withdraw(amount);
    }
}
