package controllers;


import services.BookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

public class DisplayBooksController {
    private final BookService bookService;
    private WebContext context;

    public DisplayBooksController(WebContext context, BookService displayDetails) {
        this.context = context;
        this.bookService = displayDetails;
    }

    public RequestHandlerResult list() {
        String searchkey = context.requestBodyField("searchKey");
        context.bind("books", bookService.searchBookByTitle(searchkey));
        return RequestHandlerResult.ok(context.render(ViewTemplates.Index));
    }


}
