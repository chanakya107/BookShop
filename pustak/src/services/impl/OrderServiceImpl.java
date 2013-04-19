package services.impl;

import services.OrderService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderServiceImpl implements OrderService {
    @Override
    public void storeOrder(String customerName, String email, String phoneNumber, String address){
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String dbName = "bookshop.db";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:pustak/content/public/db/" + dbName);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (statement != null) {
                statement.executeQuery("SELECT * from Orders");
            }
        } catch (SQLException e) {
            try {
                statement.executeUpdate("CREATE TABLE Orders (OrderId INTEGER Primary key AUTOINCREMENT, customerName text, email text, phoneNumber text,address text)");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        try {
            if (statement != null) {
                statement.executeUpdate("INSERT INTO Orders VALUES(null,'" + customerName + "','" + email + "','" + phoneNumber + "','" + address + "')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
