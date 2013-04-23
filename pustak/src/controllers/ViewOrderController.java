package controllers;

import model.Order;
import services.OrderService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

import java.util.List;

public class ViewOrderController {
    private final WebContext context;
    private final OrderService service;

    public ViewOrderController(WebContext context, OrderService service) {
        this.context = context;
        this.service = service;
    }
    public RequestHandlerResult getOrders() {
        List<Order> orders = service.getOrders();
        context.bind("orders", orders);
        return RequestHandlerResult.ok(context.render(ViewTemplates.DisplayOrders));
    }
}
