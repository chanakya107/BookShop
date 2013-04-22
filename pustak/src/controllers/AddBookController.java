package controllers;

import services.AddBookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

public class AddBookController {
    private final WebContext context;
    private final AddBookService addBookService;

    private AddBookController(WebContext context, AddBookService addBookService) {
        this.context = context;
        this.addBookService = addBookService;
    }

    public static AddBookController createAddBookController(WebContext context, AddBookService addBookService) {
        if (context == null)
            throw new IllegalArgumentException("Cannot create addBookController of the context : " + context);
        if (addBookService == null)
            throw new IllegalArgumentException("Cannot create addBookController of the addBookService : " + addBookService);
        context.bind("message", "Enter");
        return new AddBookController(context, addBookService);
    }

    public RequestHandlerResult createBook() {
        String isbn = context.requestBodyField("isbn");
        String title = context.requestBodyField("title");
        String author = context.requestBodyField("author");
        int price = Integer.parseInt(context.requestBodyField("price"));
        int quantity = Integer.parseInt(context.requestBodyField("quantity"));
        String type = context.requestBodyField("bookstatus");

        String message = addBookService.addBook(isbn, title, author, price, quantity, type);
        bind(message);
        return RequestHandlerResult.ok(context.render(ViewTemplates.AddBook));
    }

    private void bind(String message) {
        context.bind("message", message);
    }
}
