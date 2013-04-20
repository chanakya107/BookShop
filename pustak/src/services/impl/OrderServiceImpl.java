package services.impl;

import model.DataBase;
import services.OrderService;

import java.sql.*;

public class OrderServiceImpl implements OrderService {

    private DataBase db;

    @Override
    public void storeOrder(String customerName, String email, String phoneNumber, String address) {
        db.connectTo("pustak.db");
        ResultSet resultSet = db.selectQuery("SELECT * from Orders");
        if (resultSet == null)
            db.createTable("CREATE TABLE Orders (OrderId INTEGER Primary key AUTOINCREMENT, customerName text, email text, phoneNumber text,address text)");
        db.insertQuery("INSERT INTO Orders VALUES(null,'" + customerName + "','" + email + "','" + phoneNumber + "','" + address + "')");
    }

    @Override
    public void bindDB(DataBase db) {

        this.db = db;
    }
}
