package model;

import java.sql.*;

public class DataBase {
    private Connection connection;
    private Statement statement;

    public boolean connectTo(String dbName) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:content/public/db/" + dbName);
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
            return null;
        }
    }

    public boolean createTable(String createTableQuery) {
        try {
            statement.executeUpdate(createTableQuery);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void insertBooksToDataBase() {
        String sql = "CREATE TABLE books " +
                "(isbn VARCHAR not NULL, " +
                " title VARCHAR(255) not NULL, " +
                " author1 VARCHAR(255) not NULL, " +
                "author2 VARCHAR(255)," +
                " price INTEGER not NULL," +
                " newbookquantity INTEGER, " +
                " usedbookquantity INTEGER, " +
                " PRIMARY KEY ( isbn ))";
        createTable(sql);
    }

    public void closeConnection() {
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

    public void dropTable(String tableName) {
        try {
            statement.executeUpdate("DROP table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateQuery(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
