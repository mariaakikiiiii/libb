import java.time.*;
import java.util.Scanner;
class Librarian extends User {
    private double salary;
    public Librarian(String username, String password, String phone) throws LibraryException {
        super(username, password, phone);
        this.salary=0;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public double getSalary() {
        return this.salary;
    }
    public void viewSalary() {
        System.out.println("your salary is: $"+salary);
    }

    // Add a new item to the library
    public void addItemToLibrary(LibrarySystem library, String category, String name, double price,double borrowPrice, int stock) throws LibraryException {
        String itemId = category.toLowerCase() + "-" + name.replace(" ", "-").toLowerCase();
        if (library.getItemById(itemId) != null) {
            throw new LibraryException("An item with this name already exists! Use 'Add Stock' instead.");
        }
        Item newItem = null;
        if (category.equalsIgnoreCase("Book")) {
            newItem = new Book(itemId, name, price, borrowPrice, stock);
        } else if (category.equalsIgnoreCase("DVD")) {
            newItem = new DVD(itemId, name, price,borrowPrice,stock);
        } else if (category.equalsIgnoreCase("Newspaper")) {
            newItem = new Newspaper(itemId, name, price,borrowPrice,stock);
        }

        if (newItem == null) {
            throw new LibraryException("Invalid item category!");
        }

        library.addItem(newItem);
        System.out.println("Item added to library: " + newItem.getName());
    }

    // Add customer method
    public void addCustomer(LibrarySystem library, String username, String password, String phone) throws LibraryException {
        library.addUser(new Customer(username, password, phone));
        System.out.println("Customer account created!");
    }
    public void modifyItemPrice(LibrarySystem library, String itemId, double newPurchasePrice, double newBorrowPrice) throws LibraryException {
        Item item = library.getItemById(itemId);
        if (item == null) {
            throw new LibraryException("Item not found!");
        }

        item.setPurchasePrice(newPurchasePrice);
        item.setBorrowPrice(newBorrowPrice);
        System.out.println("Price updated for " + item.getName() + ". New Purchase Price: $" + newPurchasePrice + ", New Borrow Price: $" + newBorrowPrice + "/day");
    }
    public void deleteItem(LibrarySystem library, String itemId) throws LibraryException {
        if (library.getItemById(itemId) == null) {
            throw new LibraryException("Item not found!");
        }

        library.removeItem(itemId);
        System.out.println("Item successfully deleted.");
    }
    public void viewAvailableItems(LibrarySystem library, Scanner scanner) {
        System.out.println("\nAvailable Items in Library:");

        // Display all available items
        for (Item item : library.getAllItems()) {
            System.out.println("ID: " + item.getItemId() + " |  Name: " + item.getName() +
                    " | Stock: " + item.getStock() +
                    " | Purchase Price: $" + item.getPurchasePrice() +
                    " | Borrow Price: $" + item.getBorrowPrice() + "/day");
        }

        System.out.println("\n Select an item to modify (or type 'exit' to go back)");
        System.out.print("Enter Item ID: ");
        String itemId = scanner.nextLine();

        if (itemId.equalsIgnoreCase("exit")) {
            return; // Exit the method if user chooses to go back
        }

        Item selectedItem = library.getItemById(itemId);

        if (selectedItem == null) {
            System.out.println("Item not found! Please try again.");
            return;
        }

        System.out.println("\nWhat would you like to do with this item?");
        System.out.println("1 Modify Price");
        System.out.println("2 Add Stock");
        System.out.println("3 Delete Item");
        System.out.print("Enter choice: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter New Purchase Price: ");
                double newPurchasePrice = scanner.nextDouble();
                System.out.print("Enter New Borrow Price per Day: ");
                double newBorrowPrice = scanner.nextDouble();
                scanner.nextLine();
                modifyItemPrice(library, itemId, newPurchasePrice, newBorrowPrice);
            } else if (choice == 2) {
                System.out.print("Enter Quantity to Add: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                addStockToItem(library, itemId, quantity);
            } else if (choice == 3) {
                deleteItem(library, itemId);
            } else {
                System.out.println("Invalid choice! Returning to menu.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input, returning to menu.");
            scanner.nextLine();
        }
    }
    //Add Stock to Existing Items**
    public void addStockToItem(LibrarySystem library, String itemId, int quantity) throws LibraryException {
        Item item = library.getItemById(itemId);
        if (item == null) {
            throw new LibraryException("Item not found!");
        }

        item.increaseStock(quantity);
        System.out.println("Stock updated: " + quantity + " copies added to " + item.getName() + ". New stock: " + item.getStock());
    }

    // View customer information
    public void viewCustomerInformation(LibrarySystem library, String username) throws LibraryException {
        Customer customer = library.getCustomerByUsername(username);
        if (customer != null) {
            System.out.println("\n Customer Information:");
            System.out.println("Username: " + customer.getUsername());
            System.out.println("Phone: " + customer.getPhone());
            System.out.println("Balance: $" + customer.getBalance());
            System.out.println("Transaction History:");
            customer.displayTransactions();
        } else {
            throw new LibraryException("Customer not found!");
        }
    }

    // Process transactions for customers (Borrow or Buy)
    public void processTransaction(LibrarySystem library, String customerUsername, String itemId, boolean isPurchase, int borrowDuration, int quantity) throws LibraryException {
        Customer customer = library.getCustomerByUsername(customerUsername);
        if (customer == null) {
            throw new LibraryException("Customer not found!");
        }

        Item item = library.getItemById(itemId);
        if (item == null) {
            throw new LibraryException("Item not found!");
        }

        if (isPurchase) {
            double totalPrice = item.getPurchasePrice() * quantity;
            if (customer.getBalance() >= totalPrice) {
                customer.deductBalance(totalPrice);
                item.decreaseStock(quantity);
                customer.addTransaction("Purchased: " + quantity + "x " + item.getName());
                System.out.println("Purchase successful. " + quantity + " copies bought.");
            } else {
                throw new LibraryException("Insufficient funds!");
            }
        } else {
            double borrowCost = item.getBorrowPrice() * borrowDuration * quantity;
            if (customer.getBalance() >= borrowCost) {
                customer.deductBalance(borrowCost);
                item.decreaseStock(quantity);
                customer.addTransaction("Borrowed: " + quantity + "x " + item.getName() + " for " + borrowDuration + " days. return before: "+ LocalDate.now().plusDays(borrowDuration));
                System.out.println("Borrowing successful. " + quantity + " copies borrowed for " + borrowDuration + " days. borrowed date: "+ LocalDate.now()+" to date: "+LocalDate.now().plusDays(borrowDuration));
            } else {
                throw new LibraryException("Insufficient funds for borrowing!");
            }
        }
    }

    // Modify customer's balance
    public void modifyCustomerBalance(LibrarySystem library, String customerUsername, double amount) throws LibraryException {
        Customer customer = library.getCustomerByUsername(customerUsername);
        if (customer == null) {
            throw new LibraryException("Customer not found!");
        }

        customer.addBalance(amount);
        System.out.println("Customer's balance updated. New balance: $" + customer.getBalance());
    }
}