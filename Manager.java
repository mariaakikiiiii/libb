class Manager extends Librarian {

    public Manager(String username, String password, String phone, double salary) throws LibraryException {
        super(username, password, phone);
        this.setSalary(salary);
    }

    public void createLibrarian(LibrarySystem library, String username, String password, String phone, double salary) throws LibraryException {
        Librarian librarian = new Librarian(username, password, phone);
        librarian.setSalary(salary);
        library.addUser(librarian);
        System.out.println("Librarian account created with salary: $" + salary);
    }

    public void createManager(LibrarySystem library, String adminUsername, String username, String password, String phone, double salary) throws LibraryException {
        if (!adminUsername.equals("admin")) {
            throw new LibraryException("Only the default manager (admin) can create a new manager!");
        }
        library.addUser(new Manager(username, password, phone, salary));
        System.out.println("Manager account created!");
    }
    public void deleteManager(LibrarySystem library, String adminUsername, String managerUsername) throws LibraryException {
        if (!adminUsername.equals("admin")) {
            throw new LibraryException("Only the default manager (admin) can delete a manager!");
        }
        User user = library.getUserByUsername(managerUsername);
        if (user instanceof Manager) {
            library.removeUser(managerUsername);
            System.out.println("Manager " + managerUsername + " has been deleted.");
        } else {
            throw new LibraryException("No such manager found!");
        }
    }
    public void deleteLibrarian(LibrarySystem library, String librarianUsername) throws LibraryException {
        User user = library.getUserByUsername(librarianUsername);
        if (user instanceof Librarian && !(user instanceof Manager)) {  // Prevent deleting managers
            library.removeUser(librarianUsername);
            System.out.println("Librarian " + librarianUsername + " has been deleted.");
        } else {
            throw new LibraryException("No such librarian found or unauthorized action!");
        }
    }
    public void setSalary(String librarianUsername, double newSalary, LibrarySystem library) {
        User user = library.getUserByUsername(librarianUsername);
        if (user instanceof Librarian) {
            ((Librarian) user).setSalary(newSalary);
            System.out.println("Salary of " + librarianUsername + " updated to $" + newSalary);
        } else {
            System.out.println("Librarian not found.");
        }
    }
    public void viewAllSalaries(LibrarySystem library, String adminUsername) throws LibraryException {
        if (!adminUsername.equals("admin")) {
            throw new LibraryException("Only the default manager (admin) can view all salaries!");
        }

        System.out.println("\n All Librarians' and Managers' Salaries:");
        boolean found = false;

        for (User user : library.getAllUsers()) {
            if (user instanceof Librarian) {
                Librarian librarian = (Librarian) user;
                System.out.println("Username: " + librarian.getUsername() + " |  Salary: $" + librarian.getSalary());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No librarians or managers found.");
        }
    }

    // New method to view all librarians' information
    public void viewAllLibrarians(LibrarySystem library) {
        System.out.println("\n All Librarians' Information:");
        boolean found = false;
        for (User user : library.getAllUsers()) {
            if (user instanceof Librarian && !(user instanceof Manager)) { // Exclude managers
                Librarian librarian = (Librarian) user;
                System.out.println("Username: " + librarian.getUsername());
                System.out.println("Phone: " + librarian.phone);
                System.out.println("Salary: $" + librarian.getSalary());
                System.out.println("-------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No librarians found.");
        }
    }
}