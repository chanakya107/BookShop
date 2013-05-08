package controllers;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import services.BookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

import static org.mockito.Mockito.*;

public class AddBookControllerTest
{

  WebContext context;
  BookService service;
  AddBookController controller;

  @Before
  public void setup()
  {
    context = mock(WebContext.class);
    service = mock(BookService.class);
    controller = AddBookController.createAddBookController(context, service);
  }

  @Test
  public void createAddBookController_will_create_AddBookController()
  {
    AddBookController controller = AddBookController.createAddBookController(context, service);
    Assert.assertTrue(controller != null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void cannot_create_addBookController_of_null_context()
  {
    AddBookController.createAddBookController(null, service);
  }

  @Test(expected = IllegalArgumentException.class)
  public void cannot_create_addBookController_of_null_service()
  {
    AddBookController.createAddBookController(context, null);
  }

//  @Test
//  public void createBook_will_return_RequestHandlerResult()
//  {
//    stub(context.requestBodyField("isbn")).toReturn("isbn");
//    stub(context.requestBodyField("title")).toReturn("title");
//    stub(context.requestBodyField("author1")).toReturn("author1");
//    stub(context.requestBodyField("author2")).toReturn("author2");
//    stub(context.requestBodyField("price")).toReturn("98");
//    stub(context.requestBodyField("quantity")).toReturn("2");
//    stub(context.requestBodyField("bookstatus")).toReturn("New");
//    stub(context.render(ViewTemplates.AddBook)).toReturn("ok");
//    RequestHandlerResult result = controller.createBook();
//    verify(context).bind("message", "ISBN Already Exists.");
//    Assert.assertTrue(result != null);
//  }

}
