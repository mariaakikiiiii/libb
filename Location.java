public class Location {
    private String country;
    private String state;
    private String block;
    private String rue;

    public Location(String state, String rue, String block, String country) {
        this.state = state;
        this.rue = rue;
        this.block = block;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Country: " + country + ", State: " + state + ", Block: " + block + ", Rue: " + rue;
    }

    public String getFullLocation(Location loc) {
        return loc.toString();
    }

    public String getCountry() { 
        return country; }
    public void setCountry(String country) { 
        this.country = country; }
    public String getState() { 
        return state; }
    public void setState(String state) { 
        this.state = state; }
    public String getBlock() { 
        return block; }
    public void setBlock(String block) { 
        this.block = block; }
    public String getRue() { 
        return rue; }
    public void setRue(String rue) { 
        this.rue = rue; }
}

