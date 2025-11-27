public class TestAccess {
    public static void main(String[] args) {
        if (BankingSystem.CUSTOMER_SERVICE.isLoggedIn()) {
            System.out.println("Logged in");
        } else {
            System.out.println("Not logged in");
        }
    }
}
