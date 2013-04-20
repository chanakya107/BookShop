package model;

import java.sql.*;

public class DataBase {
    private Connection connection;
    private Statement statement;

    public boolean connectTo(String dbPath) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:content/public/db/" + dbPath);
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

    public String insertQuery(String insertQuery) {
        String message;
        try {
            statement.executeUpdate(insertQuery);
            message = "Book Added To Database Successfully";
        } catch (SQLException e) {
            message = "ISBN already present : Failed To Add Book.";
        }
        closeConnection();
        return message;
    }

    public String insertBooksToDataBase(String queryString) {
        DatabaseMetaData meta = null;
        String sql = "CREATE TABLE books " +
                "(isbn VARCHAR not NULL, " +
                " title VARCHAR(255) not NULL, " +
                " author VARCHAR(255) not NULL, " +
                " price INTEGER not NULL," +
                " NewBookQuantity INTEGER, " +
                " UsedBookQuantity INTEGER, " +
                " PRIMARY KEY ( isbn ))";
        createTable(sql);
        return insertQuery(queryString);
    }

    private void closeConnection() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}