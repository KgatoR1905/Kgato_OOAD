import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomerService {
    private Customer currentCustomer;
    private List<Customer> customers = new ArrayList<>();
    private final Path storePath = Paths.get("customers.txt");

    public CustomerService() {
        loadFromFile();
        if (customers.isEmpty()) {
            // create a demo user if none exist
            Customer demo = new Customer("user", "pass", "Test User");
            customers.add(demo);
            saveToFile();
        }
    }

    public synchronized void login(String username, String password) {
        String hashed = sha256(password);
        for (Customer c : customers) {
            if (c.getUsername().equals(username) && c.getPassword().equals(hashed)) {
                currentCustomer = c;
                return;
            }
        }
        throw new IllegalArgumentException("Invalid username or password.");
    }

    public synchronized void logout() {
        currentCustomer = null;
    }

    public synchronized boolean isLoggedIn() {
        return currentCustomer != null;
    }

    public synchronized Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public synchronized void registerCustomer(Customer customer) {
        // prevent duplicate usernames
        for (Customer c : customers) {
            if (c.getUsername().equals(customer.getUsername())) {
                throw new IllegalArgumentException("Username already exists.");
            }
        }

        String supplied = customer.getPassword();
        String storedPassword = isLikelyHashed(supplied) ? supplied : sha256(supplied);

        Customer stored = new Customer(customer.getUsername(), storedPassword, customer.getFullName());
        // copy any account numbers
        for (Integer a : customer.getAccountNumbers()) stored.addAccountNumber(a);

        customers.add(stored);
        saveToFile();
    }

    public synchronized void addAccountToCustomer(String username, int accountNumber) {
        for (Customer c : customers) {
            if (c.getUsername().equals(username)) {
                c.addAccountNumber(accountNumber);
                saveToFile();
                return;
            }
        }
    }

    private void loadFromFile() {
        File f = storePath.toFile();
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                // format: username,password,fullName,acc1;acc2;acc3
                String[] parts = line.split(",", 4);
                if (parts.length >= 3) {
                    String username = parts[0];
                    String password = parts[1];
                    String fullName = parts[2];
                    String storedPassword = isLikelyHashed(password) ? password : sha256(password);
                    Customer c = new Customer(username, storedPassword, fullName);
                    if (parts.length == 4 && parts[3] != null && !parts[3].isEmpty()) {
                        String[] accs = parts[3].split(";");
                        for (String a : accs) {
                            try {
                                c.addAccountNumber(Integer.parseInt(a));
                            } catch (NumberFormatException ex) {
                                // ignore malformed account numbers
                            }
                        }
                    }
                    customers.add(c);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load customers from file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        File f = storePath.toFile();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            for (Customer c : customers) {
                StringBuilder sb = new StringBuilder();
                sb.append(c.getUsername()).append(",").append(c.getPassword()).append(",").append(c.getFullName()).append(",");
                List<Integer> accs = c.getAccountNumbers();
                for (int i = 0; i < accs.size(); i++) {
                    if (i > 0) sb.append(";");
                    sb.append(accs.get(i));
                }
                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to save customers to file: " + e.getMessage());
        }
    }

    private static String sha256(String input) {
        if (input == null) return "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte x : b) sb.append(String.format("%02x", x & 0xff));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }

    private static boolean isLikelyHashed(String s) {
        if (s == null) return false;
        // SHA-256 hex length 64
        if (s.length() != 64) return false;
        return s.matches("[0-9a-fA-F]{64}");
    }
}
