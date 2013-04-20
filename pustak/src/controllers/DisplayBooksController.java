package controllers;


import model.Book;
import services.BookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

public class DisplayBooksController {
    private WebContext context;
    private final BookService displayDetails;

    public DisplayBooksController(WebContext context, BookService displayDetails) {
        this.context = context;
        this.displayDetails = displayDetails;
    }

    public RequestHandlerResult list(){
        Book[] books = displayDetails.getAll();
        context.bind("books", books);
        return RequestHandlerResult.ok(context.render(ViewTemplates.Index));
    }



}
