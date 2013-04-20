package model;

public class Order {
    private final int orderId;
    private final String customerName;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final String date;
    private final String isbn;
    private final String title;
    private final String author;
    private final int price;

    public Order(int orderId, String customerName, String email, String phoneNumber, String address, String date, String isbn, String title, String author, int price) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.date = date;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }
}
