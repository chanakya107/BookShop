package model;

public class Order {
    private final int orderId;
    private final String customerName;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final String date;
    private final String isbn;
    private String title;
    private String author;
    private int price;
    private String status;

    public Order(int orderId, String customerName, String email, String phoneNumber, String address, String date, String isbn, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.date = date;
        this.isbn = isbn;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
