package controllers;

import services.BookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

/**
 * Created with IntelliJ IDEA.
 * User: neharas
 * Date: 23/4/13
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminPageController {
    private final WebContext webContext;
    private final BookService bookService;

    public AdminPageController(WebContext webContext, BookService bookService) {

        this.webContext = webContext;
        this.bookService = bookService;
    }

    public RequestHandlerResult display() {
        webContext.bind("new_books", bookService.displayAllBooks("New", ""));
        webContext.bind("used_books", bookService.displayAllBooks("Used", ""));
        return RequestHandlerResult.ok(webContext.render(ViewTemplates.SearchResult));
    }
}
