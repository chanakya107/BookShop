package controllers;

import model.Book;
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
        String isbn = context.requestBodyField("isbn");
        Book book = service.changeStatus(isbn);
        context.bind("dispatchedBook",book);
        service.disConnect();
        return RequestHandlerResult.ok(context.render(ViewTemplates.DispatchedBooks));
    }


}
