package services.impl;

import emails.Invoice;
import mail.Mail;
import model.Book;
import model.Customer;
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
    public void storeOrder(Customer customer, Book orderedBook) {
        time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
        ResultSet resultSet = dataBase.selectQuery("SELECT * from Orders");

        if (resultSet == null)
            dataBase.createTable("CREATE TABLE Orders (orderId INTEGER Primary key AUTOINCREMENT, customerName text, email text, phoneNumber text,address text,date DATETIME,isbn text,status text)");
        dataBase.insertQuery("INSERT INTO Orders VALUES(null,'" + customer.getCustomerName() + "','" + customer.getEmail() + "','" + customer.getPhoneNumber() + "','" + customer.getAddress() + "','" + time + "','" + orderedBook.getISBN() + "','Pending')");
    }

    @Override
    public void sendInvoice(Book orderedBook, Customer customer) {
        Invoice invoice = new Invoice(orderedBook, customer.getCustomerName(), time);
        Mail mail = new Mail(invoice.getSubject(), invoice.getContent());
        try {
            mail.sendMail(customer.getEmail().replace("%40", "@"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindDB(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Book getBook(String isbn) {

        String query = "select isbn,title,author,price,newbookquantity,usedbookquantity from books where isbn like '%" + isbn + "%'";

        ResultSet resultSet = dataBase.selectQuery(query);
        Book book;
        try {
            while (resultSet.next()) {
                book = Book.createBook(resultSet.getInt(1), resultSet.getString(2).replace("+", " "), resultSet.getString(3).replace("+", " "), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6));
                return book;
            }

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
}
