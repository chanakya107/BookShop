package models;

import java.sql.*;

public class DataBase {
    private Connection connection;
    private Statement statement;

    public boolean createDataBase(String dbPath) {
        try {
            Class.forName("org.sqlite.JDBC");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean connectTo(String dbPath) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            statement = connection.createStatement();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet selectQuery(String selectQuery) {
        try {
            return  statement.executeQuery(selectQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createTable(String createTableQuery) {
        try {
            statement.executeUpdate(createTableQuery);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean insertQuery(String insertQuery) {
        try {
            statement.executeUpdate(insertQuery);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}