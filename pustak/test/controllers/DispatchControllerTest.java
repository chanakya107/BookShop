package controllers;


import model.Order;
import org.junit.Before;
import org.junit.Test;
import services.OrderService;
import step.web.framework.WebContext;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class DispatchControllerTest {
    private DispatchBookController controller;
    private WebContext context;
    private OrderService service;

    @Before
    public void setUp() {
        context = mock(WebContext.class);
        service = mock(OrderService.class);
        controller = new DispatchBookController(context, service);
    }

    @Test
    public void status_internally_takes_isbn_from_service() {
        stub(context.requestBodyField("orderId")).toReturn("1");
        controller.dispatch();
        verify(context).requestBodyField("orderId");
    }

    @Test
    public void status_internally_takes_orderId_and_returns() {
        stub(context.requestBodyField("orderId")).toReturn("1");
        ArrayList<Order> orders = new ArrayList<Order>();
        stub(service.getOrders()).toReturn(orders);
        controller.dispatch();
        verify(service).changeStatus(1);
        verify(service).getOrders();
        verify(service).disConnect();
        verify(context).bind("orders",orders);
    }
}
