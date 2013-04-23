package controllers;

import model.Book;
import services.BookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
        String isbn = null;
        String title = null;
        String author1 = null;
        String author2 = null;
        try {
            isbn = URLDecoder.decode(context.requestBodyField("isbn"), "UTF-8");
            title = URLDecoder.decode(context.requestBodyField("title"), "UTF-8");
            author1 = URLDecoder.decode(context.requestBodyField("author1"), "UTF-8");
            author2 = URLDecoder.decode(context.requestBodyField("author2"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
