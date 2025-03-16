import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        try {
            library.addUser(new Manager("admin", "admin123", "030123456", 5000));

            while (true) {
                System.out.println("\nLibrary System Menu");
                System.out.println("1 Login");
                System.out.println("2️ Exit");
                System.out.print("Enter choice: ");

                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice == 2) break;

                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    currentUser = library.authenticateUser(username, password);

                    if (currentUser == null) {
                        System.out.println("Invalid credentials! Try again.");
                        continue;
                    }

                    System.out.println("Login successful!");

                    // **Manager Menu**
                    if (currentUser instanceof Manager) {
                        Manager manager = (Manager) currentUser;
                        while (true) {
                            System.out.println("\nManager Menu (Includes Librarian Functions)");
                            System.out.println("1 Create Librarian");
                            if (manager.getUsername().equals("admin")) {
                                System.out.println("2 Create Manager (Only Admin)");
                                System.out.println("3 Delete Manager (Only Admin)");
                                System.out.println("4 View All Salaries (Only Admin)");
                            }
                            System.out.println("5 Set Librarian Salary");
                            System.out.println("6 View All Librarians");
                            System.out.println("7 Delete Librarian");

                            // **Librarian Functions**
                            System.out.println("8 Add Customer");
                            System.out.println("9 View Customer Information");
                            System.out.println("10 Process Transaction (Buy/Borrow)");
                            System.out.println("11 Modify Customer Balance");
                            System.out.println("12 Add Item to Library");
                            System.out.println("13 View Available Items & Modify");
                            System.out.println("14 View Salary");
                            System.out.println("15 Logout");

                            System.out.print("Enter choice: ");

                            try {
                                int managerChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (managerChoice == 15) break;

                                if (managerChoice == 1) {
                                    System.out.print("Username for Librarian: ");
                                    String librarianUsername = scanner.nextLine();
                                    System.out.print("Password: ");
                                    String librarianPassword = scanner.nextLine();
                                    System.out.print("Phone: ");
                                    String librarianPhone = scanner.nextLine();
                                    System.out.print("Enter Salary: ");
                                    double salary = scanner.nextDouble();
                                    scanner.nextLine();
                                    manager.createLibrarian(library, librarianUsername, librarianPassword, librarianPhone, salary);
                                } else if (managerChoice == 2 && manager.getUsername().equals("admin")) {
                                    System.out.print("Username for Manager: ");
                                    String managerUsername = scanner.nextLine();
                                    System.out.print("Password: ");
                                    String managerPassword = scanner.nextLine();
                                    System.out.print("Phone: ");
                                    String managerPhone = scanner.nextLine();
                                    System.out.print("Enter Salary: ");
                                    double salary = scanner.nextDouble();
                                    scanner.nextLine();
                                    manager.createManager(library, "admin", managerUsername, managerPassword, managerPhone, salary);
                                } else if (managerChoice == 3 && manager.getUsername().equals("admin")) {
                                    System.out.print("Enter Manager Username to Delete: ");
                                    String managerUsername = scanner.nextLine();
                                    manager.deleteManager(library, "admin", managerUsername);
                                } else if (managerChoice == 4 && manager.getUsername().equals("admin")) {
                                    manager.viewAllSalaries(library, "admin");
                                } else if (managerChoice == 5) {
                                    System.out.print("Username of Librarian: ");
                                    String librarianUsername = scanner.nextLine();
                                    System.out.print("Enter Salary: ");
                                    double salary = scanner.nextDouble();
                                    scanner.nextLine();
                                    manager.setSalary(librarianUsername, salary, library);
                                } else if (managerChoice == 6) {
                                    manager.viewAllLibrarians(library);
                                } else if (managerChoice == 7) {
                                    System.out.print("Enter Librarian Username to Delete: ");
                                    String librarianUsername = scanner.nextLine();
                                    manager.deleteLibrarian(library, librarianUsername);
                                } else if (managerChoice == 8) {
                                    System.out.print("Customer Username: ");
                                    String customerUsername = scanner.nextLine();
                                    System.out.print("Password: ");
                                    String customerPassword = scanner.nextLine();
                                    System.out.print("Phone: ");
                                    String customerPhone = scanner.nextLine();
                                    manager.addCustomer(library, customerUsername, customerPassword, customerPhone);
                                } else if (managerChoice == 9) {
                                    System.out.print("Customer Username: ");
                                    String viewCustomerUsername = scanner.nextLine();
                                    manager.viewCustomerInformation(library, viewCustomerUsername);
                                } else if (managerChoice == 10) {
                                    System.out.print("Customer Username: ");
                                    String transactionCustomerUsername = scanner.nextLine();
                                    System.out.print("Item ID: ");
                                    String itemId = scanner.nextLine();
                                    System.out.print("1 Buy or 2 Borrow: ");
                                    int transactionChoice = scanner.nextInt();
                                    System.out.print("Enter Quantity: ");
                                    int quantity = scanner.nextInt();
                                    int borrowDuration = 0;
                                    if (transactionChoice == 2) {
                                        System.out.print("Enter Borrow Duration (days): ");
                                        borrowDuration = scanner.nextInt();
                                    }
                                    scanner.nextLine();
                                    manager.processTransaction(library, transactionCustomerUsername, itemId, transactionChoice == 1, borrowDuration, quantity);
                                } else if (managerChoice == 11) {
                                    System.out.print("Customer Username: ");
                                    String modifyCustomerUsername = scanner.nextLine();
                                    System.out.print("Enter Amount to Add: ");
                                    double amount = scanner.nextDouble();
                                    scanner.nextLine();
                                    manager.modifyCustomerBalance(library, modifyCustomerUsername, amount);
                                } else if (managerChoice == 12) {
                                    System.out.print("Enter Category (Book/DVD/Newspaper): ");
                                    String category = scanner.nextLine();
                                    System.out.print("Enter Item Name: ");
                                    String name = scanner.nextLine();
                                    System.out.print("Enter Purchase Price: ");
                                    double purchasePrice = scanner.nextDouble();
                                    System.out.print("Enter Borrow Price per Day: ");
                                    double borrowPrice = scanner.nextDouble();
                                    System.out.print("Enter Stock Quantity: ");
                                    int stock = scanner.nextInt();
                                    scanner.nextLine();
                                    manager.addItemToLibrary(library, category, name, purchasePrice, borrowPrice, stock);
                                } else if (managerChoice == 13) {
                                    manager.viewAvailableItems(library, scanner);
                                } else if (managerChoice == 14) {
                                    manager.viewSalary();
                                }
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                                scanner.nextLine();
                            }
                        }
                    }

                    // **Librarian Menu**
                    else if (currentUser instanceof Librarian) {
                        Librarian librarian = (Librarian) currentUser;
                        while (true) {
                            System.out.println("\nLibrarian Menu");
                            System.out.println("1 Add Customer");
                            System.out.println("2 View Customer Information");
                            System.out.println("3 Process Transaction (Buy/Borrow)");
                            System.out.println("4 Modify Customer Balance");
                            System.out.println("5 Add Item to Library");
                            System.out.println("6 View Available Items & Modify");
                            System.out.println("7 View Salary");
                            System.out.println("8 Logout");
                            System.out.print("Enter choice: ");

                            try {
                                int librarianChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (librarianChoice == 8) break;

                                if (librarianChoice == 1) {
                                    try {
                                        System.out.print("Customer Username: ");
                                        String customerUsername = scanner.nextLine();
                                        System.out.print("Password: ");
                                        String customerPassword = scanner.nextLine();
                                        System.out.print("Phone: ");
                                        String customerPhone = scanner.nextLine();
                                        librarian.addCustomer(library, customerUsername, customerPassword, customerPhone);
                                    }catch (LibraryException e) {
                                    System.out.println(e.getMessage());
                                    }
                                } else if (librarianChoice == 2) {
                                    System.out.print("Customer Username: ");
                                    String customerUsername = scanner.nextLine();
                                    librarian.viewCustomerInformation(library, customerUsername);
                                } else if (librarianChoice == 3) {
                                    System.out.print("Customer Username: ");
                                    String customerUsername = scanner.nextLine();
                                    System.out.print("Item ID: ");
                                    String itemId = scanner.nextLine();
                                    System.out.print("1 Buy or 2 Borrow: ");
                                    int transactionChoice = scanner.nextInt();
                                    System.out.print("Enter Quantity: ");
                                    int quantity = scanner.nextInt();
                                    int borrowDuration = 0;
                                    if (transactionChoice == 2) {
                                        System.out.print("Enter Borrow Duration (days): ");
                                        borrowDuration = scanner.nextInt();
                                    }
                                    scanner.nextLine();
                                    librarian.processTransaction(library, customerUsername, itemId, transactionChoice == 1, borrowDuration, quantity);
                                } else if (librarianChoice == 4) {
                                    System.out.print("Customer Username: ");
                                    String customerUsername = scanner.nextLine();
                                    System.out.print("Enter Amount to Add: ");
                                    double amount = scanner.nextDouble();
                                    scanner.nextLine();
                                    librarian.modifyCustomerBalance(library, customerUsername, amount);
                                } else if (librarianChoice == 5) {
                                    System.out.print("Enter Category (Book/DVD/Newspaper): ");
                                    String category = scanner.nextLine();
                                    System.out.print("Enter Item Name: ");
                                    String name = scanner.nextLine();
                                    System.out.print("Enter Purchase Price: ");
                                    double purchasePrice = scanner.nextDouble();
                                    System.out.print("Enter Borrow Price per Day: ");
                                    double borrowPrice = scanner.nextDouble();
                                    System.out.print("Enter Stock Quantity: ");
                                    int stock = scanner.nextInt();
                                    scanner.nextLine();
                                    librarian.addItemToLibrary(library, category, name, purchasePrice, borrowPrice, stock);
                                } else if (librarianChoice == 6) {
                                    librarian.viewAvailableItems(library, scanner);
                                } else if (librarianChoice == 7) {
                                    librarian.viewSalary();
                                }
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                                scanner.nextLine();
                            }
                        }
                    }

                    // **Customer Menu**
                    else if (currentUser instanceof Customer) {
                        Customer customer = (Customer) currentUser;
                        while (true) {
                            System.out.println("\nCustomer Menu");
                            System.out.println("1 View Personal Information & Transactions");
                            System.out.println("2 View Balance");
                            System.out.println("3 Logout");
                            System.out.print("Enter choice: ");

                            try {
                                int customerChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (customerChoice == 3) break;

                                if (customerChoice == 1) {
                                    customer.viewInformation();
                                } else if (customerChoice == 2) {
                                    System.out.println("Balance: $" + customer.getBalance());
                                }
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                                scanner.nextLine();
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    scanner.nextLine();
                }
            }
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }
}