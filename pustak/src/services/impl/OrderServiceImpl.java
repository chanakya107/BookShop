package services.impl;

import model.Book;
import model.DataBase;
import services.OrderService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {

    private DataBase dataBase;

    @Override
    public void storeOrder(String customerName, String email, String phoneNumber, String address, Book ISBN) {
        ResultSet resultSet = dataBase.selectQuery("SELECT * from Orders");
        if (resultSet == null)
            dataBase.createTable("CREATE TABLE Orders (OrderId INTEGER Primary key AUTOINCREMENT, customerName text, email text, phoneNumber text,address text)");
        dataBase.insertQuery("INSERT INTO Orders VALUES(null,'" + customerName + "','" + email + "','" + phoneNumber + "','" + address + "')");
    }

    @Override
    public void bindDB(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Book getBook(String isbn) {
        String query = "select isbn,title,author,price,newbookquantity,usedbookquantity from books where isbn like '%" + isbn + "%'";
        ResultSet resultSet = dataBase.selectQuery(query);
        try {
            return Book.createBook(resultSet.getInt(1), resultSet.getString(2).replace("+", " "), resultSet.getString(3).replace("+", " "), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void reduceCount(Book book) {
        String query = "UPDATE books SET newbookquantity=newbookquantity-1 where isbn like '%" + book.getISBN() + "%'";
        dataBase.updateQuery(query);
    }
}
