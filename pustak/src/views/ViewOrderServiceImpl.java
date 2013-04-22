package views;


import model.DataBase;
import model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewOrderServiceImpl implements ViewOrderService {
    DataBase db;

    @Override
    public List<Order> getOrders() {
        db.connectTo("pustak.db");
        return getOrdersInList(db.selectQuery("select orderId,customerName,email,phoneNumber,address,date,isbn,status from orders"));
    }

    @Override
    public void bindDB(DataBase db) {
        this.db = db;
    }

    private List<Order> getOrdersInList(ResultSet resultSet) {

        List<Order> orders = new ArrayList<Order>();
        try {
            while (resultSet.next()) {
//                orders.add(createOrder(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
//
//    private Order createOrder(){
//
//    }

//    private Order createOrder(int orderId, String customerName, String email, String phoneNumber, String address, String date, String isbn, String status) {
//        ResultSet resultSet = getBookDetails(isbn);
//        Order order = null;
//        try {
//            order = new Order(orderId, customerName, email, phoneNumber, address, date, isbn, resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), status);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return order;
//    }

    private ResultSet getBookDetails(String isbn) {
        ResultSet resultSet = db.selectQuery("select title,author,price from books where isbn like '" + isbn + "'");
        return resultSet;
    }
}
