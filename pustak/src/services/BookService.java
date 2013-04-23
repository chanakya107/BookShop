package services;


import model.Book;
import model.DataBase;

public interface BookService {
//    Book[] searchBookByTitle(String searchkey, String category);
    void bindDB(DataBase db);
    boolean addBook(Book book);
}
