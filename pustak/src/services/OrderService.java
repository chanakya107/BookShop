package services;

import model.Book;
import model.Customer;
import model.Order;

import java.util.List;

public interface OrderService {

    Book fetchBook(String isbn);

    List<Order> getOrders();

    List<Order> getOrdersWithBookDetails(List<Order> orders);

    void connect();

    void disConnect();

    void processOrder(Customer customer, String isbn, String bookType);
}
