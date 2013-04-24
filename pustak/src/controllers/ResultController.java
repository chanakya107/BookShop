package controllers;

import services.BookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

public class ResultController {
    private final WebContext context;
    private BookService bookService;

    public ResultController(WebContext context, BookService bookService) {

        this.context = context;
        this.bookService = bookService;
    }

    public RequestHandlerResult getResult() {
        String searchKey = context.requestBodyField("searchKey");
        context.bind("new_books", bookService.displayAllBooks("New", searchKey));
        context.bind("used_books", bookService.displayAllBooks("Used", searchKey));


        return RequestHandlerResult.ok(context.render(ViewTemplates.SearchResult));
    }
}
