package services;

import model.Book;
import model.Customer;
import model.Order;

import java.util.List;

public interface OrderService {

    Book fetchBook(String isbn);

    List<Order> getOrders();

    void connect();

    void disConnect();

    List<Order> getTodayOrders();
    void processOrder(Customer customer, String isbn, String bookType);
}
