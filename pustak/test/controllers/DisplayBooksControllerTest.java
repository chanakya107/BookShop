package controllers;

import model.Book;
import org.junit.Before;
import org.junit.Test;
import services.BookService;
import step.web.framework.WebContext;

import static org.mockito.Mockito.*;

public class DisplayBooksControllerTest {
    WebContext context;
    BookService bookServices;
    Book[] books;
    DisplayBooksController displayBooksController;

    @Before
    public void setUp() {
        context = mock(WebContext.class);
        bookServices = mock(BookService.class);
        displayBooksController = new DisplayBooksController(context, bookServices);
    }

//    @Test
//    public void when_displayController_is_called_list_is_invoked() {
//        books = new Book[5];
//        when(context.requestBodyField("searchKey")).thenReturn("a");
//        when(bookServices.displayAllBooks("New", "a")).thenReturn(books);
//        displayBooksController.list();
//        verify(bookServices).displayAllBooks("New", "a");
//    }
}
