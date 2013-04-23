package controllers;

import model.DataBase;
import org.junit.Test;
import services.impl.BookServiceImpl;
import step.web.framework.WebContext;

import static org.mockito.Mockito.mock;

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


}
