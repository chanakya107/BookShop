package services;


import model.Book;

import java.math.BigInteger;

public interface BookService {

    Book[] displayAllBooks(String type, String searchKey);

    boolean addBook(Book book);

    Book searchBookByIsbn(String isbn);

    void updateStock(int additionalCopies, String isbn, String type);
}
