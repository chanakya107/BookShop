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
        Boolean isInserted = dataBase.insertQuery(insertQuery);
        dataBase.closeConnection();
        return isInserted;
    }



    @Override
    public Book searchBookByIsbn(String isbn) {
        dataBase.connectTo("pustak.db");
        String searchQuery = "Select * from books where isbn like '%" + isbn + "%'";
        Book book = makeBook(dataBase.selectQuery(searchQuery));
        dataBase.closeConnection();
        return book;
    }

    @Override
    public void updateStock(int additionalCopies, String isbn, String type) {
        dataBase.connectTo("pustak.db");
        String updateQuery;
        if(type.equals("New")){
            updateQuery = "Update books set newbookquantity = newbookquantity +" + additionalCopies + " where isbn like '%" + isbn + "%'";
        }

        else{
            updateQuery = "Update books set usedbookquantity = usedbookquantity +" + additionalCopies + " where isbn like '%" + isbn + "%'";
        }

        dataBase.updateQuery(updateQuery);
        dataBase.closeConnection();
    }

    private Book makeBook(ResultSet resultSet) {
        Book foundBook = null;
        try {
            foundBook = new Book(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundBook;
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
