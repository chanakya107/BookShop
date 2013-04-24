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
        String bookType = context.requestBodyField("bookType");
        int pinCode = Integer.parseInt(context.requestBodyField("pinCode"));
        Customer customer = new Customer(customerName, email, phoneNumber, address, pinCode);
        service.processOrder(customer,ISBN,bookType);
        return RequestHandlerResult.ok(context.render(ViewTemplates.orderSuccessful));
    }

    public RequestHandlerResult placeOrder() {
        service.connect();
        String bookType = context.requestBodyField("bookType");
        String isbn = context.requestBodyField("isbn");
        Book book = service.fetchBook(isbn);
        context.bind("bookType",bookType);
        context.bind("orderedBook", book);
        service.disConnect();
        return RequestHandlerResult.ok(context.render(ViewTemplates.placeOrder));
    }
}
