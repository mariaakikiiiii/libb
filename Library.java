import java.time.LocalDateTime;

public class Library {
    private String name;
    private String phoneNumber;
    private LocalDateTime openHours;
    private Item item;
    private User user;

    public Library(String name, String phoneNumber, LocalDateTime openHours) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.openHours = openHours;
    }

    public Library() {
        this("maliks", "71364432", LocalDateTime.now());
    }

    public Library(Library library) {
        this(library.name, library.phoneNumber, library.openHours);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getOpenHours() {
        return openHours;
    }

    public void setOpenHours(LocalDateTime openHours) {
        this.openHours = openHours;
    }
    public String toString(){
        return "name: "+getName()+"\nOur phone num: "+getPhoneNumber()+"\nour open hours: "+getOpenHours();
    }
}
