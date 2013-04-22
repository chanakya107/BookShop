package services;


import model.Book;
import model.DataBase;

public interface BookService {
    Book[] searchBookByTitle(String searchKey);

    Book createBook(int ISBN, String title, String authorName, int price, int usedQuantity, int newQuantity);

    void bindDB(DataBase db);
}
