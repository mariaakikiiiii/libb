import java.util.*;

class Customer extends User {
    private double balance;
    private List<String> transactions;

    public Customer(String username, String password, String phone) throws LibraryException {
        super(username, password, phone);
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }
    public String getPhone() {
        return phone;
    }

    public void addBalance(double amount) {
        balance += amount;
    }

    public void deductBalance(double amount) throws LibraryException {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new LibraryException("Not enough balance!");
        }
    }

    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }

    public void displayTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("📜 No transactions found.");
        } else {
            System.out.println("\nTransaction History:");
            for (String transaction : transactions) {
                System.out.println("- " + transaction);
            }
        }
    }

    // New method to allow customers to view their information
    public void viewInformation() {
        System.out.println("\nCustomer Information:");
        System.out.println("Username: " + getUsername());
        System.out.println("Phone: " + phone);
        System.out.println("Balance: $" + balance);
        displayTransactions();
    }
}
