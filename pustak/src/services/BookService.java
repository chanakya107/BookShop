package services;


import model.Book;

public interface BookService {
    Book[] displayAllBooks(String type, String searchKey);
    boolean addBook(Book book);

}
