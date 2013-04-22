package controllers;

import model.Book;
import services.OrderService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

public class OrderListController {
    private final WebContext context;
    private final OrderService service;
    private Book orderedBook;

    public OrderListController(WebContext context, OrderService service) {
        this.context = context;
        this.service = service;
    }

    public RequestHandlerResult createOrder() {
        String customerName = context.requestBodyField("Name");
        String email = context.requestBodyField("Email");
        String phoneNumber = context.requestBodyField("phoneNumber");
        String address = context.requestBodyField("Address");
        String ISBN = context.requestBodyField("ISBN");


        orderedBook = service.getBook(ISBN);

        service.storeOrder(customerName, email, phoneNumber, address, orderedBook);

        service.reduceCount(orderedBook);

        service.sendInvoice(orderedBook, customerName, email, address);
        return RequestHandlerResult.ok(context.render(ViewTemplates.orderSuccessful));
    }
}
