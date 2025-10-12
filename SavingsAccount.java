public class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 0.0;

    public SavingsAccount(int accNum, String name, double init) {
        super(accNum, name, init);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < MIN_BALANCE)
            throw new IllegalArgumentException("Cannot go below minimum balance");
        super.withdraw(amount);
    }
}
