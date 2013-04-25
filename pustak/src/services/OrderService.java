package services;

import model.Book;
import model.Customer;
import model.Order;
import summary.Transaction;

import java.util.List;

public interface OrderService {

    Book fetchBook(String isbn);

    List<Order> getOrders();

    void connect();

    void disConnect();


    void processOrder(Customer customer, String isbn, String bookType);


    void changeStatus(int orderId);
    List<Transaction> getTodaySales();

    void sendDispatchMessage(int orderId, String customerName, String eMail, String bookTitle, String address);
}
