package model;

public class Book {
    private int ISBN;
    private String  title;
    private String author;
    private int quantity_New;
    private int quantity_used;
    private int price;

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (ISBN != book.ISBN) return false;
        if (price != book.price) return false;
        if (quantity_New != book.quantity_New) return false;
        if (quantity_used != book.quantity_used) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return  ISBN +
                "," + title +
                "," + author +
                "," + quantity_New +
                "," + quantity_used +
                "," + price;
    }

    @Override
    public int hashCode() {
        int result = ISBN;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + quantity_New;
        result = 31 * result + quantity_used;
        result = 31 * result + price;
        return result;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity_New() {
        return quantity_New;
    }

    public void setQuantity_new(int quantity_New) {
        this.quantity_New = quantity_New;
    }

    public int getQuantity_used() {
        return quantity_used;
    }

    public void setQuantity_used(int quantity_used) {
        this.quantity_used = quantity_used;
    }

    public int getPrice() {

        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
