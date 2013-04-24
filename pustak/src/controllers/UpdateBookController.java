package controllers;

import services.BookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

import java.math.BigInteger;

public class UpdateBookController {
    private final WebContext context;
    private final BookService bookService;

    private UpdateBookController(WebContext context, BookService bookService) {
        this.context = context;
        this.bookService = bookService;
    }

    public static UpdateBookController createUpdateBookController(WebContext context, BookService bookService) {
        if (context == null)
            throw new IllegalArgumentException("Cannot create addBookController of the context : " + context);
        if (bookService == null)
            throw new IllegalArgumentException("Cannot create addBookController of the bookService : " + bookService);
        return new UpdateBookController(context, bookService);
    }

    public RequestHandlerResult update() {
        String isbn = context.requestBodyField("isbn");
        int additionalCopies = Integer.parseInt((context.requestBodyField("AdditionalCopies")));
        String type = context.requestBodyField("bookstatus");
        bookService.updateStock(additionalCopies, isbn, type);
        context.bind("isbn", isbn);
        return RequestHandlerResult.ok(context.render(ViewTemplates.UpdateAcknowledgement));
    }
}
