class Item {
    private String id;
    private String name;
    private double purchasePrice;
    private double borrowPrice;
    private int stock;

    public Item(String id, String name, double purchasePrice, double borrowPrice, int stock) {
        this.id = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.borrowPrice = borrowPrice;
        this.stock = stock;
    }

    public String getItemId() { return id; }
    public String getName() { return name; }
    public double getPurchasePrice() { return purchasePrice; }
    public double getBorrowPrice() { return borrowPrice; }
    public int getStock() { return stock; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }
    public void setBorrowPrice(double borrowPrice) { this.borrowPrice = borrowPrice; }

    public void increaseStock(int quantity) {
        this.stock += quantity;
    }
    public void addStockToItem(LibrarySystem library, String itemId, int quantity) throws LibraryException {
        Item item = library.getItemById(itemId);
        if (item == null) {
            throw new LibraryException("Item not found!");
        }

        item.increaseStock(quantity);
        System.out.println("Stock updated: " + quantity + " copies added to " + item.getName() + ". New stock: " + item.getStock());
    }

    public void decreaseStock(int quantity) throws LibraryException {
        if (this.stock >= quantity) {
            this.stock -= quantity;
        } else {
            throw new LibraryException("Not enough stock!");
        }
    }
}