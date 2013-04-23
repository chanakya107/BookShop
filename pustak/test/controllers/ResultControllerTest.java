package controllers;

import junit.framework.Assert;
import model.Book;
import org.junit.Test;
import services.BookService;
import step.web.framework.WebContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class ResultControllerTest {
    @Test
    public void getResult_on_successful_gives_RequestHandlerResult() {
        WebContext context = mock(WebContext.class);
        BookService service = mock(BookService.class);

        stub(context.requestBodyField("searchKey")).toReturn("Prince");
        String category = "New";
        stub(service.searchBookByTitle("Prince", category)).toReturn(new Book[0]);

        ResultController controller = new ResultController(context, service);

        Assert.assertTrue(controller.getResult() != null);

    }

}
