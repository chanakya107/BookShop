package controllers;

import model.Book;
import services.BookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

public class DisplayBooksController {
    private final WebContext context;
    private final BookService bookService;

    public DisplayBooksController(WebContext context, BookService bookService) {
        this.context = context;
        this.bookService = bookService;
    }

    public RequestHandlerResult list() {
        String searchKey=context.requestBodyField("searchKey");
        Book[] newBooks = bookService.displayAllBooks("New", searchKey);
        Book[] usedBooks = bookService.displayAllBooks("Used", searchKey);
        context.bind("status",getStatus(newBooks));
        context.bind("new_books", newBooks);
        context.bind("used_books", usedBooks);
        return RequestHandlerResult.ok(context.render(ViewTemplates.Index));
    }

    private String getStatus(Book[] newBooks) {
        String status = "";
        if (newBooks.length == 0){
            status = "No Books Found!!!";
        }
        return status;
    }
}
