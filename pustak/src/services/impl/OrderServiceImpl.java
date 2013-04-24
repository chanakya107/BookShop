package services.impl;

import emails.Invoice;
import mail.Mail;
import model.Book;
import model.Customer;
import model.DataBase;
import model.Order;
import services.OrderService;
import summary.Transaction;

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

    private void storeOrder(Customer customer, String bookType, String isbn) {
        time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
        ResultSet resultSet = dataBase.selectQuery("SELECT * from orders");
        if (resultSet == null)
            dataBase.createTable("CREATE TABLE orders (orderid INTEGER Primary key AUTOINCREMENT, customername text, email text, phonenumber text,address text,pincode text,date DATETIME,isbn text,status text,booktype text, FOREIGN KEY(isbn) REFERENCES books(isbn))");
        dataBase.insertQuery("INSERT INTO orders VALUES(null,'" + customer.getCustomerName() + "','" + customer.getEmail() + "','" + customer.getPhoneNumber() + "','" + customer.getAddress() + "','" + customer.getPinCode() + "','" + time + "','" + isbn + "','Pending','" + bookType + "')");
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


   /* @Override
    public List<Order> updateStatus() {
        dataBase.connectTo("pustak.db");

        List<Book> books = getBooks();
        ResultSet resultSet = dataBase.selectQuery("Update Orders SET status='Dispatched'");
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
*/
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
        storeOrder(customer, bookType, isbn);
        reduceCount(isbn, bookType);
        sendInvoice(isbn, customer);
    }

    @Override
    public void changeStatus(int orderId) {

        String query = "update orders set status='dispatched' where orderId='"+orderId+"'";
        dataBase.updateQuery(query);

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
        String field = bookType.equals("New") ? "newbookquantity" : "usedbookquantity";
        String query = "UPDATE books SET " + field + "= " + field + "-1 where isbn = " + isbn;
        dataBase.updateQuery(query);
    }

    @Override
    public List<Transaction> getTodaySales() {
        dataBase.connectTo("pustak.db");
        String todayDate = new SimpleDateFormat("yyyy-MM-dd ").format(Calendar.getInstance().getTime());
        ResultSet resultSet = dataBase.selectQuery("select b.isbn,b.title,temp.quantity,b.price,(b.price*temp.quantity) as GrandTotal from books b," +
                "(select isbn,count(*) as quantity from orders where date like '%" + todayDate + "%' group by isbn) temp where temp.isbn==b.isbn;");
        return createTransaction(resultSet);

    }

    private List<Transaction> createTransaction(ResultSet resultSet) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        try {
            while (resultSet.next()) {
                transactions.add(new Transaction(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
