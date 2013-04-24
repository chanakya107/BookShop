package controllers;

import org.junit.Assert;
import org.junit.Test;
import services.BookService;
import step.web.framework.WebContext;

import static org.mockito.Mockito.mock;

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

}
