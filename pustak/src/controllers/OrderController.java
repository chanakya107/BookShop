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
        int pincode = Integer.parseInt(context.requestBodyField("pinCode"));

        Customer customer = new Customer(customerName, email, phoneNumber, address, pincode);
        service.connect();
        Book orderedBook = service.fetchBook(ISBN);
        service.storeOrder(customer, orderedBook);
        service.reduceCount(orderedBook);
        service.sendInvoice(orderedBook, customer);
        service.disConnect();
        return RequestHandlerResult.ok(context.render(ViewTemplates.orderSuccessful));
    }

    public RequestHandlerResult placeOrder() {
        service.connect();
        String isbn = context.requestBodyField("isbn");
        Book book = service.fetchBook(isbn);
        context.bind("orderedBook", book);
        service.disConnect();
        return RequestHandlerResult.ok(context.render(ViewTemplates.placeOrder));
    }


}
