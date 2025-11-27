import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String username;
    private String password;
    private String fullName;
    private List<Integer> accountNumbers = new ArrayList<>();

    public Customer(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public List<Integer> getAccountNumbers() {
        return accountNumbers;
    }

    public void addAccountNumber(int accNum) {
        accountNumbers.add(accNum);
    }
}
