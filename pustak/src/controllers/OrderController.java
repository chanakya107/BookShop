package controllers;

import model.Book;
import model.Customer;
import model.Order;
import services.OrderService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

import java.util.List;

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
        String pinCode = context.requestBodyField("pinCode");
        String bookType = context.requestBodyField("bookType");

        Customer customer = new Customer(customerName, email, phoneNumber, address,pinCode);
        service.connect();
        Book orderedBook = service.fetchBook(ISBN);
        service.storeOrder(customer, orderedBook);
        service.reduceCount(orderedBook,bookType);
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

    public RequestHandlerResult getOrders() {
        service.connect();
        List<Order> orders = service.getOrders();
        context.bind("orders", service.getOrdersWithBookDetails(orders));
        service.disConnect();
        return RequestHandlerResult.ok(context.render(ViewTemplates.DisplayOrders));
    }
}
