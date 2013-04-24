package controllers;

import model.Order;
import services.OrderService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

import java.util.List;

public class DispatchBookController {
    private final WebContext context;
    private final OrderService service;

    public DispatchBookController(WebContext context, OrderService service) {
        this.context = context;
        this.service = service;
    }

    public RequestHandlerResult status() {
        service.connect();
        String isbn = context.requestBodyField("isbn");
        service.changeStatus(isbn);
        List<Order> orders = service.getOrders();
        service.disConnect();
        context.bind("orders",orders);
        return RequestHandlerResult.ok(context.render(ViewTemplates.DispatchedBooks));
    }


}
