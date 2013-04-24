package summary;

public class Transaction {
    private final String isbn;
    private final String title;
    private final int price;
    private final int quantity;
    private final int total;

    public Transaction(String isbn, String title, int quantity,int price, int total) {

        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotal() {
        return total;
    }
}
