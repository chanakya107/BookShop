package controllers;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import services.AddBookService;
import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;
import views.ViewTemplates;

import static org.mockito.Mockito.*;

public class AddBookControllerTest {

    WebContext context;
    AddBookService service;
    AddBookController controller;
    @Before
    public void setup() {
        context = mock(WebContext.class);
        service = mock(AddBookService.class);
        controller = AddBookController.createAddBookController(context, service);
    }

    @Test
    public void createAddBookController_will_create_AddBookController() {
        AddBookController controller = AddBookController.createAddBookController(context, service);
        Assert.assertTrue(controller != null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannot_create_addBookController_of_null_context() {
        AddBookController.createAddBookController(null, service);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannot_create_addBookController_of_null_service() {
        AddBookController.createAddBookController(context, null);
    }

    @Test
    public void createBook_will_return_RequestHandlerResult(){
        stub(context.requestBodyField("isbn")).toReturn("isbn");
        stub(context.requestBodyField("title")).toReturn("title");
        stub(context.requestBodyField("author")).toReturn("author");
        stub(context.requestBodyField("price")).toReturn("98");
        stub(context.requestBodyField("quantity")).toReturn("2");
        stub(context.requestBodyField("bookstatus")).toReturn("New");
        stub(context.render(ViewTemplates.AddBook)).toReturn("ok");

        RequestHandlerResult result = controller.createBook();
        verify(service).addBook("isbn","title","author",98,2,"New");
        Assert.assertTrue(result != null);
    }
}