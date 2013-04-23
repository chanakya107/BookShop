package model;

import java.sql.Date;

public class Order {

    private final int orderId;
    private final Date date;
    private final String status;
    private final Customer customer;
    private final Book book;

    public Order(int orderId, Date date, String status, Customer customer, Book book) {

        this.orderId = orderId;
        this.date = date;
        this.status = status;
        this.customer = customer;
        this.book = book;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Book getBook() {
        return book;
    }
}
