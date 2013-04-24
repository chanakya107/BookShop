package controllers;

import services.BookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

public class DisplayBookController {
    private final WebContext context;
    private final BookService bookService;

    private DisplayBookController(WebContext context, BookService bookService) {
        this.context = context;
        this.bookService = bookService;
    }

    public RequestHandlerResult fetchBook() {
        String isbn = context.requestBodyField("isbn");
        context.bind("book", bookService.searchBookByIsbn(isbn));
        return RequestHandlerResult.ok(context.render(ViewTemplates.FetchBook));

    }

    public static DisplayBookController createController(WebContext context, BookService bookService) {
        if( context == null)
            throw  new IllegalArgumentException("Webcontext cannot be null" + context);
        else if(bookService==null) {
          throw new IllegalArgumentException("BookService cannot be null"+bookService);
        }
        return new DisplayBookController(context,bookService);
    }
}
