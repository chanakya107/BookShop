package services.impl;

import model.Book;
import model.DataBase;
import services.BookService;

public class BookServiceImpl implements BookService {

    private DataBase dataBase;

    public BookServiceImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public boolean addBook(Book book) {
        dataBase.connectTo("pustak.db");
        String insertQuery = "INSERT INTO books " + "values ('" + book.getISBN() + "','" + book.getTitle() + "','" + book.getAuthor1() + "','" + book.getAuthor2() + "'," + book.getPrice() + "," + book.getNewQuantity() + "," + book.getUsedQuantity() + ")";
        return dataBase.insertQuery(insertQuery);
    }

}
