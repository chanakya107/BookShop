package services.impl;

import model.Book;
import model.DataBase;
import services.BookService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private DataBase dataBase;

    public BookServiceImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Book[] searchBookByTitle(String searchKey, String type) {
        Book[] books;
        dataBase.connectTo("pustak.db");
        if (type.equals("New")) {
            if (searchKey == null || searchKey.equals(""))
                books = buildResultBooks(dataBase.selectQuery("select isbn,title,author1,author2,price,newbookquantity from books"));
            else
                books = buildResultBooks(dataBase.selectQuery("select isbn,title,author1,author2,price,newbookquantity from books where title like '%" + searchKey + "%'"));
        } else {
            if (searchKey == null || searchKey.equals(""))
                books = buildResultBooks(dataBase.selectQuery("select isbn,title,author1,author2,price/2,usedbookquantity from books"));
            else
                books = buildResultBooks(dataBase.selectQuery("select isbn,title,author1,author2,price/2,usedbookquantity from books  where title like '%" + searchKey + "%'"));
        }

        dataBase.closeConnection();
        return books;
    }
    public boolean addBook(Book book) {
        dataBase.connectTo("pustak.db");
        String insertQuery = "INSERT INTO books " + "values ('" + book.getISBN() + "','" + book.getTitle() + "','" + book.getAuthor1() + "','" + book.getAuthor2() + "'," + book.getPrice() + "," + book.getNewQuantity() + "," + book.getUsedQuantity() + ")";
        return dataBase.insertQuery(insertQuery);
    }
    private Book[] buildResultBooks(ResultSet rs) {
        List<Book> books = new ArrayList<Book>();
        try {
            while (rs.next()) {
                String title = rs.getString(2).replace("+", " ");
                String firstAuthorName = rs.getString(3).replace("+", " ");
                String secondAuthorName = rs.getString(4).replace("+", " ");
                if (rs.getInt(6) != 0)
                    books.add(new Book(rs.getString(1), title, firstAuthorName,secondAuthorName, rs.getInt(5), rs.getInt(6),0));
            }
            return books.toArray(new Book[books.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
