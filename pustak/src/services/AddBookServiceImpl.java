package services;

import model.DataBase;

public class AddBookServiceImpl implements AddBookService {
    @Override
    public String addBook(String isbn, String title, String author, int price, int quantity, String type) {
        String dbName = "content/public/db/pustak.db";
        DataBase dataBase = new DataBase();
        String sql;
        if (type.equals("New"))
            sql = "values ('" + isbn + "','" + title + "','" + author + "'," + price + "," + quantity + "," + 0 + ")";
        else
            sql = "values ('" + isbn + "','" + title + "','" + author + "'," + price + "," + 0 + "," + quantity + ")";
        String QueryString = "INSERT INTO books " + sql;
        if (dataBase.connectTo(dbName)){
            return dataBase.insertBooksToDataBase(QueryString);
        }
        return "Database is Down";
    }
}
