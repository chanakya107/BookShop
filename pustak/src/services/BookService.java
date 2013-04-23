package services;


import model.Book;

public interface BookService {

    Book[] searchBookByTitle(String searchkey, String type);

    boolean addBook(Book book);

    Book searchBookByIsbn(String isbn);

    void updateStock(int additionalCopies, String isbn, String type);
}
