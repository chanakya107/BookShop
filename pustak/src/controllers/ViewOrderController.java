package controllers;

import model.Order;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewOrderService;
import views.ViewTemplates;

import java.util.List;

public class ViewOrderController {
    private final WebContext webContext;
    private final ViewOrderService service;

    private ViewOrderController(WebContext webContext, ViewOrderService service) {
        this.webContext = webContext;
        this.service = service;
    }

    public RequestHandlerResult getOrders() {
        List<Order> orders = service.getOrders();
        webContext.bind("orders", service.getOrdersWithBookDetails(orders));
        return RequestHandlerResult.ok(webContext.render(ViewTemplates.DisplayOrders));
    }

    public static ViewOrderController createViewOrderController(WebContext context, ViewOrderService service) {
        if (context == null)
            throw new IllegalArgumentException("Cannot create ViewOrderController of context : " + context);
        if (service == null)
            throw new IllegalArgumentException("Cannot create ViewOrderController of service : " + service);
        return new ViewOrderController(context, service);
    }
}
