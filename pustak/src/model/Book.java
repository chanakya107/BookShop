package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Books")
public class Book {
  @Id
  @Column(name = "isbn")
    private String ISBN;
  @Column(name = "title")
    private String title;
  @Column(name = "author1")
    private final String author1;
  @Column(name = "author2")
    private final String author2;
  @Column(name = "newQuantity")
    private int newQuantity;
  @Column(name = "usedQuantity")
    private int usedQuantity;
  @Column(name = "price")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (newQuantity != book.newQuantity) return false;
        if (price != book.price) return false;
        if (usedQuantity != book.usedQuantity) return false;
        if (!ISBN.equals(book.ISBN)) return false;
        if (!author1.equals(book.author1)) return false;
        if (!author2.equals(book.author2)) return false;
        if (!title.equals(book.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ISBN.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + author1.hashCode();
        result = 31 * result + author2.hashCode();
        result = 31 * result + newQuantity;
        result = 31 * result + usedQuantity;
        result = 31 * result + price;
        return result;
    }

}
