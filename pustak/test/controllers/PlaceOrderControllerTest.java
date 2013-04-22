package controllers;

import model.Book;
import org.junit.Before;
import org.junit.Test;
import services.PlaceOrderService;
import step.web.framework.WebContext;
import views.ViewTemplates;

import static org.mockito.Mockito.*;

public class PlaceOrderControllerTest {
    private WebContext context;
    private PlaceOrderService service;
    private PlaceOrderController controller;

    @Before
    public void setup() {
        context = mock(WebContext.class);
        service = mock(PlaceOrderService.class);
        controller = new PlaceOrderController(context, service);
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
        verify(service).getBook("12345");
    }

    @Test
    public void placeOrder_will_bind_the_book_with_the_webpage() {
        Book book = new Book();
        when(context.requestBodyField("isbn")).thenReturn("12345");
        when(service.getBook("12345")).thenReturn(book);
        controller.placeOrder();
        verify(context).bind("orderedBook", book);
    }

    @Test
    public void after_place_order_placeOrder_page_is_rendered() {
        controller.placeOrder();
        verify(context).render(ViewTemplates.placeOrder);
    }
}
