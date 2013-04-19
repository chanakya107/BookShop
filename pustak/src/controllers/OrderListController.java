package controllers;

import services.OrderService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;

public class OrderListController {
    private final WebContext context;
    private final OrderService service;

    public OrderListController(WebContext context, OrderService service) {
        this.context = context;
        this.service = service;
    }

    public RequestHandlerResult createOrder() {
        String customerName = context.requestBodyField("Name");
        String email = context.requestBodyField("Email");
        String phoneNumber = context.requestBodyField("phoneNumber");
        String address = context.requestBodyField("Address");

        service.storeOrder(customerName, email, phoneNumber, address);

        return RequestHandlerResult.ok(context.render(ViewTemplates.orderSuccessful));
    }

}
