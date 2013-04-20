package controllers;

import model.Book;
import services.PlaceOrderService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

public class PlaceOrderController {
    private final WebContext webContext;
    private final PlaceOrderService placeOrderService;

    public PlaceOrderController(WebContext webContext, PlaceOrderService placeOrderService) {
        this.webContext = webContext;
        this.placeOrderService = placeOrderService;
    }

    public RequestHandlerResult placeOrder() {
        String isbn = webContext.requestBodyField("isbn");
        Book book = placeOrderService.getBook(isbn);
        webContext.bind("orderedBook", book);
        return RequestHandlerResult.ok(webContext.render(ViewTemplates.placeOrder));
    }

}
