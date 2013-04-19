package controllers;

import org.junit.Before;
import org.junit.Test;
import services.OrderService;
import step.web.framework.WebContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderListControllerTest {
    private OrderListController controller;
    private WebContext context;
    private OrderService service;

    @Before
    public void setup() {
        context = mock(WebContext.class);
        service = mock(OrderService.class);
        controller = new OrderListController(context, service);
    }

    @Test
    public void createOrder_takes_name_from_the_context() {
        controller.createOrder();
        verify(context).requestBodyField("Name");
    }

    @Test
    public void create_order_takes_email_from_the_context() {
        controller.createOrder();
        verify(context).requestBodyField("Email");
    }

    @Test
    public void create_order_takes_phoneNumber_from_the_context() {
        controller.createOrder();
        verify(context).requestBodyField("phoneNumber");
    }

    @Test
    public void create_order_takes_address_from_the_context() {
        controller.createOrder();
        verify(context).requestBodyField("Address");
    }

    @Test
    public void create_order_stores_the_order_with_order_details() {
        when(context.requestBodyField("Name")).thenReturn("chethan");
        when(context.requestBodyField("Email")).thenReturn("chethandec22@gmail.com");
        when(context.requestBodyField("phoneNumber")).thenReturn("7894562132");
        when(context.requestBodyField("Address")).thenReturn("Jalahalli village");
        controller.createOrder();
        verify(service).storeOrder("chethan","chethandec22@gmail.com","7894562132","Jalahalli village");
    }
}
