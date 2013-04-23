package controllers;

import model.Order;
import org.junit.Before;
import org.junit.Test;
import services.ViewOrderService;
import step.web.framework.WebContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ViewOrderControllerTest {
    private WebContext context;
    private ViewOrderService service;
    private ViewOrderController controller;

    @Before
    public void setUp() {
        context = mock(WebContext.class);
        service = mock(ViewOrderService.class);
        controller = ViewOrderController.createViewOrderController(context, service);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannot_create_ViewOrderController_of_null_context() {
        ViewOrderController.createViewOrderController(null, service);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannot_create_ViewOrderController_of_null_service() {
        ViewOrderController.createViewOrderController(context, null);
    }

    @Test
    public void getOrders_binds_the_list_of_orders_to_the_velocity_variable_orders() {
        List<Order> orders = new ArrayList<Order>();
        stub(service.getOrders()).toReturn(orders);
        controller.getOrders();
        verify(context).bind("orders", orders);
    }
}
