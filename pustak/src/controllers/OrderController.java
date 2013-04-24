package controllers;

import model.Book;
import model.Customer;
import services.OrderService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class OrderController {
    private final WebContext context;
    private final OrderService service;

    public OrderController(WebContext context, OrderService service) {
        this.context = context;
        this.service = service;
    }

    public RequestHandlerResult createOrder() {
        String customerName = null;
        String address = null;
        String ISBN = null;
        String bookType = null;
        String phoneNumber = null;
        String email = null;
        try {
            customerName = URLDecoder.decode(context.requestBodyField("Name"), "UTF-8");
            email = URLDecoder.decode(context.requestBodyField("Email"), "UTF-8");
            phoneNumber = URLDecoder.decode(context.requestBodyField("phoneNumber"), "UTF-8");
            address = URLDecoder.decode(context.requestBodyField("Address"), "UTF-8");
            ISBN = URLDecoder.decode(context.requestBodyField("ISBN"), "UTF-8");
            bookType = URLDecoder.decode(context.requestBodyField("bookType"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int pinCode = Integer.parseInt(context.requestBodyField("pinCode"));
        Customer customer = new Customer(customerName, email, phoneNumber, address, pinCode);
        service.connect();
        service.processOrder(customer, ISBN, bookType);
        service.disConnect();
        return RequestHandlerResult.ok(context.render(ViewTemplates.orderSuccessful));
    }

    public RequestHandlerResult placeOrder() {
        service.connect();
        String bookType = null;
        String isbn = null;

        try {
            bookType = URLDecoder.decode(context.requestBodyField("type"), "UTF-8");
            isbn = URLDecoder.decode(context.requestBodyField("isbn"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Book book = service.fetchBook(isbn);
        context.bind("bookType", bookType);
        context.bind("orderedBook", book);
        service.disConnect();
        return RequestHandlerResult.ok(context.render(ViewTemplates.placeOrder));
    }
}
