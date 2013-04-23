package services;

import model.Book;
import model.Customer;
import model.Order;

import java.util.List;

public interface OrderService {

    Book fetchBook(String isbn);

    void reduceCount(Book isbn, String bookType);

    void storeOrder(Customer customer, Book orderedBook);

    void sendInvoice(Book orderedBook, Customer customer);

    List<Order> getOrders();

    void connect();

    void disConnect();
}
