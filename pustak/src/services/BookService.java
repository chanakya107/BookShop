package services;


import model.Book;

public interface BookService {
    boolean addBook(Book book);

    Book[] searchBookByTitle(String searchKey, String type);
}
