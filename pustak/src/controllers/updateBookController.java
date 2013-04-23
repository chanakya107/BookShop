package controllers;

import services.BookService;
import services.impl.BookServiceImpl;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

public class UpdateBookController {
    private final WebContext context;
    private final BookService bookService;

    private UpdateBookController(WebContext context, BookService bookService) {
        this.context = context;
        this.bookService = bookService;
    }

    public RequestHandlerResult update() {
//        bookService.updateStock(context.requestBodyField("isbn"));
        return RequestHandlerResult.ok(context.render(ViewTemplates.UpdateBook));
    }

    public static UpdateBookController createController(WebContext context, BookService bookService) {
        if( context == null)
            throw  new IllegalArgumentException("Webcontext cannot be null" + context);
         else if(bookService==null) {
            throw new IllegalArgumentException("BookService cannot be null"+bookService);
        }
        return null;
    }
}
