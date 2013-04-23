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
        String searchkey = context.requestBodyField("searchKey");

        String category=context.requestBodyField("category");
//        context.bind("books", bookService.searchBookByTitle(searchkey, category));
        return RequestHandlerResult.ok(context.render(ViewTemplates.SearchResult));
    }
}
