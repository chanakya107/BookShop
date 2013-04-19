package controllers;

import services.AddBookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

public class AddBookController {
    private final WebContext context;
    private final AddBookService addBookService;

    public AddBookController(WebContext context, AddBookService addBookService) {
        this.context = context;
        this.addBookService = addBookService;
    }

    public RequestHandlerResult createBook() {
        String isbn=context.requestBodyField("isbn");
        String title=context.requestBodyField("title");
        String author=context.requestBodyField("author");
        int price= Integer.parseInt(context.requestBodyField("price"));
        int quantity= Integer.parseInt(context.requestBodyField("quantity"));
        String type=context.requestBodyField("bookstatus");
        addBookService.addBook(isbn,title,author,price,quantity,type);
        return RequestHandlerResult.ok(context.render(ViewTemplates.AddBook));
    }
}
