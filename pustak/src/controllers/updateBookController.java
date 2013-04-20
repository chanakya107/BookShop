package controllers;

import services.BookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;

public class updateBookController {
    private final WebContext context;
    private final BookService bookService;

    public updateBookController(WebContext context, BookService bookService) {
        this.context = context;
        this.bookService = bookService;
    }

    public RequestHandlerResult update() {
//        bookService.updateStock(context.requestBodyField("isbn"));
        return null;
    }
}
