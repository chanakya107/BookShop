package controllers;

import services.BookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

/**
 * Created with IntelliJ IDEA.
 * User: neharas
 * Date: 23/4/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplayBooksController {
    private final WebContext context;
    private final BookService bookService;

    public DisplayBooksController(WebContext context, BookService bookService) {
        this.context = context;
        this.bookService = bookService;
    }

    public RequestHandlerResult list() {

        String searchKey=context.requestBodyField("searchKey");
        context.bind("new_books", bookService.displayAllBooks("New",searchKey));
        context.bind("used_books", bookService.displayAllBooks("Used", searchKey));
        return RequestHandlerResult.ok(context.render(ViewTemplates.Index));
    }
}
