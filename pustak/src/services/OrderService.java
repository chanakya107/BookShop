package services;

import model.Book;
import model.DataBase;

public interface OrderService {

    void storeOrder(String customerName, String email, String phoneNumber, String address, Book ISBN);

    void bindDB(DataBase db);

    Book getBook(String isbn);

    void reduceCount(Book isbn);

    void sendInvoice(Book orderedBook, String customerName, String email, String address);
}
