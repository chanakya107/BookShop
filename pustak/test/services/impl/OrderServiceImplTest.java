package services.impl;

import model.DataBase;
import org.junit.Test;
import services.OrderService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OrderServiceImplTest {
    @Test
    public void getDailySales_invokes_database_selectQuery() {
        DataBase dataBase = mock(DataBase.class);
        OrderService orderService = new OrderServiceImpl(dataBase);
        orderService.getTodaySales();
        verify(dataBase).selectQuery("select b.isbn,b.title,temp.quantity,b.price,(b.price*temp.quantity) as GrandTotal from books b,\n" +
                "       (select isbn,count(*) as quantity from orders where date like '%2013-04-23%' group by isbn) temp where temp.isbn==b.isbn;");
    }

}
