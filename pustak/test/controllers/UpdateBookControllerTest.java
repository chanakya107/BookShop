package controllers;

import model.Book;
import org.junit.Assert;
import org.junit.Test;
import services.BookService;
import services.impl.BookServiceImpl;
import step.web.framework.WebContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateBookControllerTest {


    @Test(expected = IllegalArgumentException.class)
    public void createUpdateBookController_throws_IllegalArgumentException_when_context_is_null(){
        BookService bookService=mock(BookService.class);
        WebContext context=null;
        UpdateBookController.createUpdateBookController(context,bookService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUpdateBookController_throws_IllegalArgumentException_when_bookService_is_null(){
        BookService bookService=null;
        WebContext context=mock(WebContext.class);
        UpdateBookController.createUpdateBookController(context,bookService);
    }

    @Test
    public void update_calls_bind(){
        WebContext context = mock(WebContext.class);
        BookServiceImpl bookService = mock(BookServiceImpl.class);
        String keyisbn = "123";
        String status="New";
        UpdateBookController controller = UpdateBookController.createUpdateBookController(context, bookService);
        when(context.requestBodyField("isbn")).thenReturn(keyisbn);
        when(context.requestBodyField("bookstatus")).thenReturn(status);
        String additionalCopies="1";
        when(context.requestBodyField("AdditionalCopies")).thenReturn(additionalCopies);
        controller.update();
        verify(bookService).updateStock(Integer.parseInt(additionalCopies),keyisbn,status);
        verify(context).bind("isbn",keyisbn);
    }

}
