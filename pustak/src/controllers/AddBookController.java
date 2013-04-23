package controllers;

import model.Book;
import services.BookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

public class AddBookController {
    private final WebContext context;
    private final BookService bookService;

    private AddBookController(WebContext context, BookService bookService) {
        this.context = context;
        this.bookService = bookService;
    }

    public static AddBookController createAddBookController(WebContext context, BookService bookService) {
        if (context == null)
            throw new IllegalArgumentException("Cannot create addBookController of the context : " + context);
        if (bookService == null)
            throw new IllegalArgumentException("Cannot create addBookController of the bookService : " + bookService);
        return new AddBookController(context, bookService);
    }

    public RequestHandlerResult createBook() {
        String isbn = context.requestBodyField("isbn");
        String title = context.requestBodyField("title");
        String author1 = context.requestBodyField("author1");
        String author2 = context.requestBodyField("author2");
        int price = Integer.parseInt(context.requestBodyField("price"));
        int quantity = Integer.parseInt(context.requestBodyField("quantity"));
        String type = context.requestBodyField("bookstatus");
        Book book;
        if (type.equals("New"))
            book = new Book(isbn, title, author1, author2, price, quantity, 0);
        else
            book = new Book(isbn, title, author1, author2, price, 0, quantity);
        String message;
        if (bookService.addBook(book))
            message = "Book successfully added to Inventory.";
        else
            message = "ISBN Already Exists.";
        context.bind("message", message);
        return RequestHandlerResult.ok(context.render(ViewTemplates.AddBook));
    }
}
