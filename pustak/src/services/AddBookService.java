package services;

import model.DataBase;

public interface AddBookService {
    void addBook(String isbn, String title, String author, int price, int quantity, String type);

    void bindDB(DataBase db);
}
