package services.impl;

import junit.framework.Assert;
import model.DataBase;
import org.junit.Before;
import org.junit.Test;
import views.ViewOrderService;
import views.ViewOrderServiceImpl;

import static org.mockito.Mockito.mock;

public class ViewOrderServiceImplTest {
    private DataBase db;
    private ViewOrderService service;

    @Before
    public void setUp() {
        db = mock(DataBase.class);
        service = new ViewOrderServiceImpl();
        service.bindDB(db);
    }

    @Test
    public void one_plus_one(){
        Assert.assertEquals(1,1);
    }

//    @Test
//    public void getOrders_will_connect_to_the_database() throws SQLException {
//        DataBase dataBase = new DataBase();
//        dataBase.connectTo("pustak.db");
//        ResultSet orderDetails = dataBase.selectQuery("select orderId,customerName,email,phoneNumber,address,date,isbn,status from orders");
//        ResultSet bookDetails = dataBase.selectQuery("select title,author,price from books where isbn like '" + orderDetails.getString(7) + "'");
//        stub(db.selectQuery("select orderId,customerName,email,phoneNumber,address,date,isbn from orders")).toReturn(orderDetails);
//        stub(db.selectQuery("select title,author,price from books where isbn like '" + orderDetails.getString(7) + "'")).toReturn(bookDetails);
//
//        service.getOrders();
//        verify(db).connectTo("pustak.db");
//        verify(db).selectQuery("select orderId,customerName,email,phoneNumber,address,date,isbn from orders");
//    }
}
