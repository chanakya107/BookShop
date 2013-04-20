package services;


import model.Book;

public interface BookService {
    Book[] searchBookByTitle(String searchKey);

    Book createBook(int ISBN, String title, String authorName, int price, int usedQuantity, int newQuantity);
    Book[] getAll();
}
