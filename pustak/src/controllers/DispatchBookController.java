package controllers;

import model.Order;
import services.OrderService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class DispatchBookController {
    private final WebContext context;
    private final OrderService service;

    public DispatchBookController(WebContext context, OrderService service) {
        this.context = context;
        this.service = service;
    }

    public RequestHandlerResult dispatch() {
        service.connect();
        int orderId = Integer.parseInt(context.requestBodyField("orderId"));
        String customerName = null;
        String eMail = null;
        String isbn = null;
        String address = null;
        try {
            customerName = URLDecoder.decode(context.requestBodyField("customerName"), "UTF-8");
            eMail = URLDecoder.decode(context.requestBodyField("email"), "UTF-8");
            isbn = URLDecoder.decode(context.requestBodyField("isbn"), "UTF-8");
            address = URLDecoder.decode(context.requestBodyField("address"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        service.changeStatus(orderId);
        service.sendDispatchMessage(orderId, customerName, eMail, isbn, address);
        List<Order> orders = service.getOrders();
        context.bind("orders", orders);
        service.disConnect();
        return RequestHandlerResult.ok(context.render(ViewTemplates.DispatchedBooks));
    }
}
