package controllers;


import org.junit.Before;
import org.junit.Test;
import services.OrderService;
import step.web.framework.WebContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

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
    public void status_internally_takes_isbn_from_service(){
        stub(context.requestBodyField("isbn")).toReturn("983740837");
         controller.status();
         verify(context).requestBodyField("isbn");
    }

    @Test
    public void status_internally_takes_isbn_and_returns_null_when_not_found() {
        stub(context.requestBodyField("status")).toReturn("dispatched");
        controller.status();
        verify(service).changeStatus(null);
    }



}
