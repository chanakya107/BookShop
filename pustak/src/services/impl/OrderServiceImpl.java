package services.impl;

import emails.Invoice;
import model.Book;
import model.Customer;
import model.DataBase;
import model.Order;
import services.OrderService;
import mail.*;

import javax.mail.MessagingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderServiceImpl implements OrderService {
//TODO: database connection is not closed properly.
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
    public List<Order> getOrders() {
        dataBase.connectTo("pustak.db");
        List<Order> ordersInList = getOrdersInList(dataBase.selectQuery("select orderId,customerName,email,phoneNumber,address,date,isbn,status from orders"));
        dataBase.closeConnection();
        return ordersInList;
    }

    @Override
    public List<Order> getOrdersWithBookDetails(List<Order> orders) {
        ResultSet resultSet1;
        dataBase.connectTo("pustak.db");
        for (Order order : orders) {
            resultSet1 = dataBase.selectQuery("select title,author,price from books where isbn like '%" + order.getIsbn() + "%'");
            try {
                order.setTitle(resultSet1.getString(1).replace("+", " "));
                order.setAuthor(resultSet1.getString(2).replace("+", " "));
                order.setPrice(resultSet1.getInt(3));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        dataBase.closeConnection();
        return orders;
    }

    @Override
    public void bindDB(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Book fetchBook(String isbn) {

        String query = "select isbn,title,author,price,newbookquantity,usedbookquantity from books where isbn like '%" + isbn + "%'";

        ResultSet resultSet = dataBase.selectQuery(query);
        Book book;
        try {
            while (resultSet.next()) {
//                book = Book.createBook(resultSet.getInt(1), resultSet.getString(2).replace("+", " "), resultSet.getString(3).replace("+", " "), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6));
//                return book;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void reduceCount(Book book) {
        String query = "UPDATE books SET newbookquantity=" + (book.getNewQuantity() - 1) + " where isbn like '%" + book.getISBN() + "%'";
        dataBase.updateQuery(query);
    }

    private List<Order> getOrdersInList(ResultSet resultSet) {

        List<Order> orders = new ArrayList<Order>();
        try {
            while (resultSet.next()) {
                orders.add(createOrder(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3).replaceAll("%40", "@"), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Order createOrder(int orderId, String customerName, String email, String phoneNumber, String address, String date, String isbn, String status) {
        return new Order(orderId, customerName, email, phoneNumber, address, date, isbn, status);
    }
}
