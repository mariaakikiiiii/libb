import java.util.regex.*;

abstract class User {
    protected String username;
    protected String password;
    protected String phone;

    public User(String username, String password, String phone) throws LibraryException {
        if (!isValidLebanesePhone(phone)) {
            throw new LibraryException("Invalid Lebanese phone number!");
        }
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public String getUsername() { 
        return username; }
    public String getPassword() { 
        return password; }



    private boolean isValidLebanesePhone(String phone) {
        String regex = "^(?:\\+961|961)?(1|0?3[0-9]?|[4-6]|70|71|76|78|79|7|81?|9)\\d{6}$";
        return Pattern.matches(regex, phone);
    }
}