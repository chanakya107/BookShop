package controllers;

import services.BookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;


public class PurchaserPageController {
    private final WebContext webContext;
    private final BookService bookService;

    public PurchaserPageController(WebContext webContext, BookService bookService) {
        this.webContext = webContext;
        this.bookService = bookService;
    }

    public RequestHandlerResult display() {
        webContext.bind("new_books", bookService.displayAllBooks("New",""));
        webContext.bind("used_books", bookService.displayAllBooks("Used", ""));
        return RequestHandlerResult.ok(webContext.render(ViewTemplates.Index));
    }
}
