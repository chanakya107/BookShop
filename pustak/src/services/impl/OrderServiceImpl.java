package services.impl;

import model.DataBase;
import services.OrderService;

import java.sql.*;

public class OrderServiceImpl implements OrderService {

    @Override
    public void storeOrder(String customerName, String email, String phoneNumber, String address, DataBase dataBase) {
        dataBase.connectTo("pustak.db");
        ResultSet resultSet = dataBase.selectQuery("SELECT * from Orders");
        if (resultSet == null)
            dataBase.createTable("CREATE TABLE Orders (OrderId INTEGER Primary key AUTOINCREMENT, customerName text, email text, phoneNumber text,address text)");
        dataBase.insertQuery("INSERT INTO Orders VALUES(null,'" + customerName + "','" + email + "','" + phoneNumber + "','" + address + "')");
    }
}
