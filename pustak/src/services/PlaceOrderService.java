package services;

import model.Book;
import model.DataBase;

public interface PlaceOrderService {
    void bindDB(DataBase dataBase);

    Book getBook(String isbn);
}
