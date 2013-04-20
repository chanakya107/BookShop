package model;

import java.sql.*;

public class DataBase {
    private Connection connection;
    private Statement statement;

    public boolean connectTo(String dbName) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
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
        ResultSet rs=null;
        try {
            rs = statement.executeQuery(selectQuery);
        } catch (SQLException e) {e.printStackTrace();}
        return rs;
    }

    public boolean createTable(String createTableQuery) {
        try {
            statement.executeUpdate(createTableQuery);
            closeConnection();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public String insertQuery(String insertQuery) {
        String message;
        try {
            statement.executeUpdate(insertQuery);
            message = "Book Added To Database Successfully";
        } catch (SQLException e) {
            message = "ISBN already present : Failed To Add Book.";
        }
        return message;
    }

    public String insertBooksToDataBase(String queryString) {
        DatabaseMetaData meta = null;
        String sql = "CREATE TABLE books " +
                "(isbn VARCHAR not NULL, " +
                " title VARCHAR(255) not NULL, " +
                " author VARCHAR(255) not NULL, " +
                " price INTEGER not NULL," +
                " newbookquantity INTEGER, " +
                " usedbookquantity INTEGER, " +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataBase dataBase = (DataBase) o;

        if (connection != null ? !connection.equals(dataBase.connection) : dataBase.connection != null) return false;
        if (statement != null ? !statement.equals(dataBase.statement) : dataBase.statement != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = connection != null ? connection.hashCode() : 0;
        result = 31 * result + (statement != null ? statement.hashCode() : 0);
        return result;
    }
}