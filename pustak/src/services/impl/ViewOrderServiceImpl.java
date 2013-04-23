package services.impl;


import model.DataBase;
import model.Order;
import services.ViewOrderService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewOrderServiceImpl implements ViewOrderService {
    DataBase db;

    @Override
    public List<Order> getOrders() {
        db.connectTo("pustak.db");
        List<Order> ordersInList = getOrdersInList(db.selectQuery("select orderId,customerName,email,phoneNumber,address,date,isbn,status from orders"));
        db.closeConnection();
        return ordersInList;
    }

    @Override
    public void bindDB(DataBase db) {
        this.db = db;
    }

    @Override
    public List<Order> getOrdersWithBookDetails(List<Order> orders) {
        ResultSet resultSet1;
        db.connectTo("pustak.db");
        for (Order order : orders) {
            resultSet1 = db.selectQuery("select title,author,price from books where isbn like '%" + order.getIsbn() + "%'");
            try {
                order.setTitle(resultSet1.getString(1).replace("+", " "));
                order.setAuthor(resultSet1.getString(2).replace("+", " "));
                order.setPrice(resultSet1.getInt(3));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        db.closeConnection();
        return orders;
    }

    private List<Order> getOrdersInList(ResultSet resultSet) {

        List<Order> orders = new ArrayList<Order>();
        try {
            while (resultSet.next()) {
                orders.add(createOrder(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3).replaceAll("%40", "@"), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Order createOrder(int orderId, String customerName, String email, String phoneNumber, String address, String date, String isbn, String status) {
        return new Order(orderId, customerName, email, phoneNumber, address, date, isbn, status);
    }  }
