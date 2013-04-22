package services.impl;

import junit.framework.Assert;
import model.DataBase;
import org.junit.Before;
import org.junit.Test;
import services.ViewOrderService;

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
    public void one_plus_one() {
        Assert.assertEquals(1, 1);
    }
}
