package services;

import model.DataBase;

public class AddBookServiceImpl implements AddBookService {
    private DataBase db;

    @Override
    public String addBook(String isbn, String title, String author, int price, int quantity, String type) {
         db.connectTo("pustak.db");
        String sql;
        if (type.equals("New"))
            sql = "values ('" + isbn + "','" + title + "','" + author + "'," + price + "," + quantity + "," + 0 + ")";
        else
            sql = "values ('" + isbn + "','" + title + "','" + author + "'," + price + "," + 0 + "," + quantity + ")";
        String QueryString = "INSERT INTO books " + sql;
            return db.insertBooksToDataBase(QueryString);
    }

    @Override
    public void bindDB(DataBase db) {
        this.db = db;
    }
}
