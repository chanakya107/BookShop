package services;

import model.Book;
import model.Customer;
import model.DataBase;
import model.Order;

import java.util.List;

public interface OrderService {

    void bindDB(DataBase db);

    Book fetchBook(String isbn);

    void reduceCount(Book isbn);

    void storeOrder(Customer customer, Book orderedBook);

    void sendInvoice(Book orderedBook, Customer customer);

    List<Order> getOrders();

    List<Order> getOrdersWithBookDetails(List<Order> orders);
}
