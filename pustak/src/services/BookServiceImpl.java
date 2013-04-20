package services;

import model.Book;
import model.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    DataBase db = new DataBase();

    public BookServiceImpl() {
        db.connectTo("pustak.db");
    }

    @Override
    public Book[] searchBookByTitle(String searchkey) {
        return buildResultBooks(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books where title like '%" + searchkey + "%'"));
    }

    @Override
    public Book[] getAll() {
        return buildResultBooks(db.selectQuery("select isbn,title,author,price,newbookquantity,usedbookquantity from books"));
    }

    private Book[] buildResultBooks(ResultSet rs) {
        List<Book> books = new ArrayList<Book>();
        try {
            String plusFreeTitle = rs.getString(2).replace("+", " ");
            String plusFreeAuthorName = rs.getString(3).replace("+", " ");
            while (rs.next()) {
                books.add(createBook(rs.getInt(1), plusFreeTitle, plusFreeAuthorName, rs.getInt(4), rs.getInt(5), rs.getInt(6)));
            }
            return books.toArray(new Book[books.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Book createBook(int ISBN, String title, String authorName, int price, int usedQuantity, int newQuantity) {
        Book book = new Book();
        book.setISBN(ISBN);
        book.setTitle(title);
        book.setAuthor(authorName);
        book.setPrice(price);
        book.setQuantity_used(usedQuantity);
        book.setQuantity_new(newQuantity);
        return book;
    }
}
