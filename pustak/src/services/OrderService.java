package services;

import model.Book;
import model.Customer;
import model.DataBase;

public interface OrderService {

    void bindDB(DataBase db);

    Book fetchBook(String isbn);

    void reduceCount(Book isbn);

    void storeOrder(Customer customer, Book orderedBook);

    void sendInvoice(Book orderedBook, Customer customer);
}
