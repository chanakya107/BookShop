package services.impl;

import emails.Invoice;
import mail.Mail;
import model.Book;
import model.DataBase;
import services.OrderService;

import javax.mail.MessagingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OrderServiceImpl implements OrderService {

    private DataBase dataBase;
    private String time;

    @Override
    public void storeOrder(String customerName, String email, String phoneNumber, String address, Book book) {
        time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
        ResultSet resultSet = dataBase.selectQuery("SELECT * from Orders");

        if (resultSet == null)
            dataBase.createTable("CREATE TABLE orders (orderId INTEGER Primary key AUTOINCREMENT, customerName text, email text, phoneNumber text,address text,date DATETIME,isbn text,status text)");
        dataBase.insertQuery("INSERT INTO Orders VALUES(null,'" + customerName + "','" + email + "','" + phoneNumber + "','" + address + "','" + time + "','" + book.getISBN() + "','Pending')");
    }

    @Override
    public void bindDB(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Book getBook(String isbn) {

        String query = "select isbn,title,author,price,newbookquantity,usedbookquantity from books where isbn like '%" + isbn + "%'";

        ResultSet resultSet = dataBase.selectQuery(query);
        try {
            return Book.createBook(resultSet.getInt(1), resultSet.getString(2).replace("+", " "), resultSet.getString(3).replace("+", " "), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void reduceCount(Book book) {

        String query = "UPDATE books SET newbookquantity=" + (book.getQuantity_New() - 1) + " where isbn like '%" + book.getISBN() + "%'";
        dataBase.updateQuery(query);
    }

    @Override
    public void sendInvoice(Book orderedBook, String customerName, String email, String address) {
        Invoice invoice = new Invoice(orderedBook, customerName, time);
        Mail mail = new Mail(invoice.getSubject(), invoice.getContent());
        try {
            email = email.replace("%40", "@");
            mail.sendMail(email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
