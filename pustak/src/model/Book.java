package model;

public class Book {
    private String ISBN;
    private String title;
    private final String author1;
    private final String author2;
    private int newQuantity;
    private int usedQuantity;
    private int price;

    public Book(String isbn, String title, String author1, String author2, int price, int newQuantity, int usedQuantity) {
        this.ISBN = isbn;
        this.title = title;
        this.author1 = author1;
        this.author2 = author2;
        this.newQuantity = newQuantity;
        this.usedQuantity = usedQuantity;
        this.price = price;
    }

    public String getAuthor1() {
        return author1;
    }

    public String getAuthor2() {
        return author2;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public int getNewQuantity() {
        return newQuantity;
    }

    public int getUsedQuantity() {
        return usedQuantity;
    }

    public int getPrice() {
        return price;
    }
}
