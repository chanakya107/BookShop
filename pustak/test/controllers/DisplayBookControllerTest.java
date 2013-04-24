package controllers;

import model.Book;
import model.DataBase;
import org.junit.Test;
import services.impl.BookServiceImpl;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DisplayBookControllerTest {
    @Test(expected = IllegalArgumentException.class)
    public void updateBookController_throws_IllegalArgumentException_when_webContext_is_null(){
        DataBase db = mock(DataBase.class);
        DisplayBookController.createController(null, new BookServiceImpl(db)) ;
    }

  @Test (expected =IllegalArgumentException.class)
  public void updateBookController_throws_IllegalArgumentException_when_bookService_is_null(){
     WebContext context=mock(WebContext.class);
      DisplayBookController.createController(context, null);
  }


    @Test
    public void fetchBook_gives_a_book() {
        WebContext context = mock(WebContext.class);
        BookServiceImpl bookService = mock(BookServiceImpl.class);
        Book book = mock(Book.class);
        String keyisbn = "123";

        DisplayBookController controller = DisplayBookController.createController(context, bookService);
        when(context.requestBodyField("isbn")).thenReturn(keyisbn);
        when(bookService.searchBookByIsbn(keyisbn)).thenReturn(book);

        controller.fetchBook();

        verify(context).bind("book", book);
    }
}

