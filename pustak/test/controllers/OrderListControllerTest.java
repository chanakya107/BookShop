package controllers;

import model.Book;
import org.junit.Before;
import org.junit.Test;
import services.OrderService;
import step.web.framework.WebContext;
import views.ViewTemplates;

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
    public void create_order_will_take_ISBN_from_the_context(){
        controller.createOrder();
        verify(context).requestBodyField("ISBN");
    }


    @Test
    public void create_order_will_get_the_book_from_the_database(){
        when(context.requestBodyField("ISBN")).thenReturn("12345");
        controller.createOrder();
        verify(service).getBook("12345");
    }

    @Test
    public void create_order_will_store_the_order_in_database(){
        when(context.requestBodyField("Name")).thenReturn("chethan");
        when(context.requestBodyField("Email")).thenReturn("chethandec22@gmail.com");
        when(context.requestBodyField("phoneNumber")).thenReturn("0987654316");
        when(context.requestBodyField("Address")).thenReturn("fasdfasf sdf asddf");
        when(context.requestBodyField("ISBN")).thenReturn("12345");
        Book book = new Book();
        when(service.getBook("12345")).thenReturn(book);

        controller.createOrder();
        verify(service).storeOrder("chethan","chethandec22@gmail.com","0987654316","fasdfasf sdf asddf", book);
    }

    @Test
    public void create_order_will_reduce_the_number_of_quantity_of_the_book_in_the_database(){
        when(context.requestBodyField("ISBN")).thenReturn("12345");
        Book book = new Book();
        when(service.getBook("12345")).thenReturn(book);
        controller.createOrder();
        verify(service).reduceCount(book);
    }

    @Test
    public void after_creating_order_the_order_successful_page_will_be_displayed(){
        controller.createOrder();
        verify(context).render(ViewTemplates.orderSuccessful);
    }
}
