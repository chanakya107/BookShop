package services.impl;

import model.DataBase;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    @Test
    public void store_order_will_store_data_into_dataBase() {
        DataBase dataBase = mock(DataBase.class);
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.bindDB(dataBase);
        orderService.storeOrder("Neha", "neharastogi093@gmail.com", "1234567890", "haryana");
        verify(dataBase).connectTo("pustak.db");
        verify(dataBase).selectQuery("SELECT * from Orders");
        verify(dataBase).insertQuery("INSERT INTO Orders VALUES(null,'" + "Neha" + "','" + "neharastogi093@gmail.com" + "','" + "1234567890" + "','" + "haryana" + "')");
    }

    @Test
    public void store_order_will_create_the_Orders_table_if_table_is_not_present() {
        DataBase dataBase = mock(DataBase.class);
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.bindDB(dataBase);
        orderService.storeOrder("Neha", "neharastogi093@gmail.com", "1234567890", "haryana");
        when(dataBase.selectQuery("SELECT * from Orders")).thenReturn(null);
        verify(dataBase).connectTo("pustak.db");
        verify(dataBase).createTable("CREATE TABLE Orders (OrderId INTEGER Primary key AUTOINCREMENT, customerName text, email text, phoneNumber text,address text)");
        verify(dataBase).insertQuery("INSERT INTO Orders VALUES(null,'" + "Neha" + "','" + "neharastogi093@gmail.com" + "','" + "1234567890" + "','" + "haryana" + "')");
    }
}
