package views;


import model.DataBase;
import model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewOrderServiceImpl implements ViewOrderService {
    DataBase db;

    public ViewOrderServiceImpl() {
    }

    @Override
    public List<Order> getOrders() {
        return getOrdersInList(db.selectQuery("select OrderId,customerName,email,phoneNumber,address,date,isbn from Orders"));
    }

    @Override
    public void bindDB(DataBase db) {
        this.db = db;
    }

    private List<Order> getOrdersInList(ResultSet resultSet) {
        db.connectTo("pustak.db");
        List<Order> orders = new ArrayList<Order>();
        try {
            while (resultSet.next()) {
                orders.add(createOrder(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Order createOrder(int orderId, String customerName, String email, String phoneNumber, String address, String date, String isbn) {
        ResultSet resultSet = getBookDetails(isbn);
        try {
            return new Order(orderId, customerName, email, phoneNumber, address, date, isbn, resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ResultSet getBookDetails(String isbn) {
        return db.selectQuery("select title,author,price from books where isbn like '" + isbn + ",");
    }
}
