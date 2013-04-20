package services;

public interface AddBookService {
    String addBook(String isbn, String title, String author, int price, int quantity, String type);
}
