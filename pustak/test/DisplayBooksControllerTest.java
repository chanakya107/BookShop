import controllers.DisplayBooksController;
import model.Book;
import org.junit.Before;
import services.BookService;
import step.web.framework.WebContext;

import static org.mockito.Mockito.mock;

public class DisplayBooksControllerTest {
    WebContext context;
    BookService bookServices;
    Book book;
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
//        displayBooksController.list();
//        verify(context).bind("books", book);
//    }
}
