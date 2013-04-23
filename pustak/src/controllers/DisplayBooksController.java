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
        if(searchKey.trim().equals("+"))
            searchKey="" ;
        context.bind("new_books", bookService.searchBookByTitle(searchKey, "New"));
        context.bind("used_books", bookService.searchBookByTitle(searchKey, "Used"));
        return RequestHandlerResult.ok(context.render(ViewTemplates.TitleSearchResult));
    }
}
