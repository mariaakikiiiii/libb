import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class LibrarySystem {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Item> items = new HashMap<>();

    // Add new item
    public void addItem(Item item) {
        items.put(item.getItemId(), item);
    }

    // Get an item by ID
    public Item getItemById(String itemId) {
        return items.get(itemId);
    }

    // Add new user
    public void addUser(User user) throws LibraryException {
        if (users.containsKey(user.getUsername())) {
            throw new LibraryException("Username already taken, please try again.");
        }
        users.put(user.getUsername(), user);
    }

    // Authenticate user
    public User authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    public Collection<Item> getAllItems() {
        return items.values();
    }
    public User getUserByUsername(String username) {
        return users.get(username);
    }
    public Collection<User> getAllUsers() {
        return users.values();
    }
    public void removeItem(String itemId) {
        items.remove(itemId);
    }
    public void removeUser(String username) {
        users.remove(username);
    }

    // Get customer by username
    public Customer getCustomerByUsername(String username) throws LibraryException {
        User user = users.get(username);
        if (user instanceof Customer) {
            return (Customer) user;
        }
        return null;
    }
}