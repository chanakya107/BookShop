package services.impl;

import model.DataBase;
import org.junit.Test;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.mockito.Mockito.*;


public class OrderServiceImplTest {
    @Test
    public void getDailySales_invokes_database_selectQuery() {
        String todayDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        ResultSet set = null;
        DataBase dataBase = mock(DataBase.class);
        OrderServiceImpl orderService = new OrderServiceImpl(dataBase);
        stub(dataBase.selectQuery("select b.isbn,b.title,temp.quantity,b.price,(b.price*temp.quantity) as GrandTotal from books b," +
                "(select isbn,count(*) as quantity from orders where date like '%"  + todayDate + "%' group by isbn) temp where temp.isbn==b.isbn;")).toReturn(set);
        orderService.getTodaySales();

        verify(dataBase).selectQuery("select b.isbn,b.title,temp.quantity,b.price,(b.price*temp.quantity) as GrandTotal from books b," +
                "(select isbn,count(*) as quantity from orders where date like '%"  + todayDate + "%' group by isbn) temp where temp.isbn==b.isbn;");
    }

}
