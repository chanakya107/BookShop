package controllers;

import services.OrderService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

public class DispatchBookController {
    private final WebContext context;
    private final OrderService service;

    public DispatchBookController(WebContext context, OrderService service) {
        this.context = context;
        this.service = service;
    }

    public RequestHandlerResult status() {
        service.connect();
        context.bind("dispatchedBook", service.getOrders());
        service.disConnect();
        return RequestHandlerResult.ok(context.render(ViewTemplates.DispatchedBooks));
    }
}
