package services;


import model.Book;

public interface BookService {

    Book[] searchBookByTitle(String searchkey, String type);
    boolean addBook(Book book);
}
