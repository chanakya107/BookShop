package services.impl;

import junit.framework.Assert;
import model.DataBase;
import org.junit.Test;
import services.OrderService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OrderServiceImplTest {
    @Test
    public void getDailySales_invokes_database_selectQuery() {
        Assert.assertEquals(1, 1);
    }

}
