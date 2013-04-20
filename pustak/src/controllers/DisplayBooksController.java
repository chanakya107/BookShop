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
        String searchKey = context.requestBodyField("searchKey");
        context.bind("books", bookService.searchBookByTitle(searchKey));
        return RequestHandlerResult.ok(context.render(ViewTemplates.TitleSearchResult));
    }

}
