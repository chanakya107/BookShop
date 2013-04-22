package services;


import model.Book;
import model.DataBase;

public interface BookService {

    Book[] searchBookByTitle(String searchkey, String category);

    Book createBook(int ISBN, String title, String authorName, int price, int Quantity);

    void bindDB(DataBase db);
}
