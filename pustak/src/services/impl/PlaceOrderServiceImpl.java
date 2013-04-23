package services.impl;

import model.Book;
import model.DataBase;
import services.PlaceOrderService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    private DataBase dataBase;

    @Override
    public void bindDB(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Book getBook(String isbn) {
        String query = "select isbn,title,author,price,newbookquantity,usedbookquantity from books where isbn like '%" + isbn + "%'";
        dataBase.connectTo("pustak.db");
        ResultSet resultSet = dataBase.selectQuery(query);
        try {
            Book book = Book.createBook(resultSet.getInt(1), resultSet.getString(2).replace("+", " "), resultSet.getString(3).replace("+", " "), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6));
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
