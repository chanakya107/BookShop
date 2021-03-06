package controllers;

import model.Book;
import model.Customer;
import org.junit.Before;
import org.junit.Test;
import services.OrderService;
import step.web.framework.WebContext;
import views.ViewTemplates;

import static org.mockito.Mockito.*;

public class OrderControllerTest {
    private OrderController controller;
    private WebContext context;
    private OrderService service;

    @Before
    public void setUp() {
        context = mock(WebContext.class);
        service = mock(OrderService.class);
        controller = new OrderController(context, service);

        when(context.requestBodyField("Name")).thenReturn("chethan");
        when(context.requestBodyField("Email")).thenReturn("chethandec22@gmail.com");
        when(context.requestBodyField("phoneNumber")).thenReturn("0987654316");
        when(context.requestBodyField("Address")).thenReturn("fasdfasf sdf asddf");
        when(context.requestBodyField("ISBN")).thenReturn("12345");
        when(context.requestBodyField("pinCode")).thenReturn("560023");
        when(context.requestBodyField("isbn")).thenReturn("12345");
        when(context.requestBodyField("bookType")).thenReturn("New");
        when(context.requestBodyField("type")).thenReturn("New");
    }

    @Test
    public void createOrder_takes_name_from_the_context() {
        when(context.requestBodyField("Name")).thenReturn("chethan");
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
    public void create_order_will_take_ISBN_from_the_context() {
        controller.createOrder();
        verify(context).requestBodyField("ISBN");
    }

    @Test
    public void create_order_will_store_the_order_in_database() {
        controller.createOrder();
        verify(service).processOrder(new Customer("chethan", "chethandec22@gmail.com", "0987654316", "fasdfasf sdf asddf", 560023), "12345", "New");
    }

    @Test
    public void after_creating_order_the_order_successful_page_will_be_displayed() {
        controller.createOrder();
        verify(context).render(ViewTemplates.orderSuccessful);
    }

    @Test
    public void placeOrder_will_get_isbn_from_the_web_context() {
        controller.placeOrder();
        verify(context).requestBodyField("isbn");
    }

    @Test
    public void place_order_will_get_the_book_from_the_database() {
        when(context.requestBodyField("isbn")).thenReturn("12345");
        controller.placeOrder();
        verify(service).fetchBook("12345");
    }

    @Test
    public void placeOrder_will_bind_the_book_with_the_webpage() {
        Book book = mock(Book.class);
        when(context.requestBodyField("isbn")).thenReturn("12345");
        when(service.fetchBook("12345")).thenReturn(book);
        controller.placeOrder();
        verify(context).bind("orderedBook", book);
    }

    @Test
    public void after_place_order_placeOrder_page_is_rendered() {
        controller.placeOrder();
        verify(context).render(ViewTemplates.placeOrder);
    }
}
