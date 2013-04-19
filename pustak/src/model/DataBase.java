package model;

import java.sql.*;

public class DataBase {
    private Connection connection;
    private Statement statement;

    public boolean connectTo(String dbPath) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            statement = connection.createStatement();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet selectQuery(String selectQuery) {
        try {
            return statement.executeQuery(selectQuery);
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