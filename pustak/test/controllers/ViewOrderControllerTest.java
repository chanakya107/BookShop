package controllers;

import org.junit.Before;
import org.junit.Test;
import step.web.framework.WebContext;
import views.ViewOrderService;

import static org.mockito.Mockito.mock;

public class ViewOrderControllerTest {
    private WebContext context;
    private ViewOrderService service;

    @Before
    public void setUp() {
        context = mock(WebContext.class);
        service = mock(ViewOrderService.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannot_create_ViewOrderController_of_null_context() {
        ViewOrderController.createViewOrderController(null, service);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannot_create_ViewOrderController_of_null_service() {
        ViewOrderController.createViewOrderController(context, null);
    }
}
