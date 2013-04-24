package services.impl;

import emails.Invoice;
import mail.Mail;
import model.Book;
import model.Customer;
import model.DataBase;
import model.Order;
import services.OrderService;

import javax.mail.MessagingException;
import java.sql.Date;
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

    public OrderServiceImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    private void storeOrder(Customer customer, String isbn) {
        time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
        ResultSet resultSet = dataBase.selectQuery("SELECT * from Orders");
        if (resultSet == null)
            dataBase.createTable("CREATE TABLE orders (orderid INTEGER Primary key AUTOINCREMENT, customername text, email text, phonenumber text,address text,pincode text,date DATETIME,isbn text,status text)");
        dataBase.insertQuery("INSERT INTO orders VALUES(null,'" + customer.getCustomerName() + "','" + customer.getEmail() + "','" + customer.getPhoneNumber() + "','" + customer.getAddress() + "','" + customer.getPinCode() + "','" + time + "','" + isbn + "','Pending')");
    }

    private void sendInvoice(String isbn, Customer customer) {
        Book orderedBook = fetchBook(isbn);
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

        List<Book> books = getBooks();
        ResultSet resultSet = dataBase.selectQuery("select * from orders where status like 'Pending'");
        List<Order> orders = new ArrayList<Order>();
        try {
            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getString(2).replace("+", " "), resultSet.getString(3).replaceAll("%40", "@"), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
                String isbn = resultSet.getString(8);
                int orderId = resultSet.getInt(1);
                Date date = resultSet.getDate(7);
                String status = resultSet.getString(9);
                for (Book book : books) {
                    if (book.getISBN().equals(isbn))
                        orders.add(createOrder(orderId, date, status, customer, book));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataBase.closeConnection();
        return orders;
    }

    private List<Book> getBooks() {
        List<Book> books = new ArrayList<Book>();
        ResultSet resultSet1 = dataBase.selectQuery("select * from books");
        try {
            while (resultSet1.next()) {
                books.add(new Book(resultSet1.getString(1), resultSet1.getString(2).replace("+", " "), resultSet1.getString(3).replace("+", " "), resultSet1.getString(4).replace("+", " "), resultSet1.getInt(5), resultSet1.getInt(6), resultSet1.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private Order createOrder(int orderId, Date date, String status, Customer customer, Book book) {
        return new Order(orderId, date, status, customer, book);
    }

    @Override
    public void connect() {
        dataBase.connectTo("pustak.db");
    }

    @Override
    public void disConnect() {
        dataBase.closeConnection();
    }

    @Override
    public void processOrder(Customer customer, String isbn, String bookType) {
        storeOrder(customer, isbn);
        reduceCount(isbn, bookType);
        sendInvoice(isbn, customer);
    }

    @Override
    public Book fetchBook(String isbn) {
        String query = "select isbn,title,author1,author2,price,newbookquantity,usedbookquantity from books where isbn like '%" + isbn + "%'";
        ResultSet resultSet = dataBase.selectQuery(query);
        try {
            return new Book(resultSet.getString(1), resultSet.getString(2).replace("+", " "), resultSet.getString(3).replace("+", " "), resultSet.getString(4).replace("+", " "), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void reduceCount(String isbn, String bookType) {
        Book book = fetchBook(isbn);
        String query = "UPDATE books SET newbookquantity=" + book.getNewBookQuantity(bookType) + ", usedbookquantity=" + book.getUsedBookQuantity(bookType) + " where isbn like '%" + isbn + "%'";
        dataBase.updateQuery(query);
    }


//    private List<Order> getOrdersInList(ResultSet resultSet) {
//
//        List<Order> orders = new ArrayList<Order>();
//        try {
//            while (resultSet.next()) {
//                orders.add(createOrder(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3).replaceAll("%40", "@"), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return orders;
//    }
//
//
//
//    @Override
//    public List<Order> getTodayOrders() {
//        dataBase.connectTo("pustak.db");
//        String time1 = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
//        List<Order> ordersInList = getOrdersInList(dataBase.selectQuery("select orderid,customername,email,phonenumber,address,date,isbn,status from orders where date like'%" + time1 + "%'"));
//        return ordersInList;
//
//    }

}
