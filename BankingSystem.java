public class BankingSystem {
    public static final Bank BANK = new Bank("Oakridge Financial");
    public static final CustomerService CUSTOMER_SERVICE = new CustomerService();

    public static void main(String[] args) {
        // Entry point kept minimal. The JavaFX launcher is in `BankingAppGUI` when running the GUI.
        System.out.println("Banking system initialized.");
    }
}
