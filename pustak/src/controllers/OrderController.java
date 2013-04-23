package controllers;

import model.Book;
import model.Customer;
import services.OrderService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

public class OrderController {
    private final WebContext context;
    private final OrderService service;

    public OrderController(WebContext context, OrderService service) {
        this.context = context;
        this.service = service;
    }

    public RequestHandlerResult createOrder() {

        String customerName = context.requestBodyField("Name");
        String email = context.requestBodyField("Email");
        String phoneNumber = context.requestBodyField("phoneNumber");
        String address = context.requestBodyField("Address");
        String ISBN = context.requestBodyField("ISBN");

        Customer customer = new Customer(customerName, email, phoneNumber, address);
        Book orderedBook = service.getBook(ISBN);
        service.storeOrder(customer, orderedBook);
        service.reduceCount(orderedBook);
        service.sendInvoice(orderedBook, customer);
        return RequestHandlerResult.ok(context.render(ViewTemplates.orderSuccessful));
    }
}
