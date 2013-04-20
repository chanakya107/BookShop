import controllers.*;
import model.DataBase;
import services.*;
import services.impl.OrderServiceImpl;
import step.web.framework.RequestHandlerResult;
import step.web.framework.RouteMap;
import step.web.framework.WebContext;
import step.web.framework.WebRequestHandler;
import views.ViewTemplates;

public class Main {
    public static void main(String[] args) {
        initializeRoutes();
    }

    private static void initializeRoutes() {
        RouteMap routeMap = RouteMap.create();
        final BookService bookService = new BookServiceImpl();
        final AddBookService addBookService = new AddBookServiceImpl();
        final OrderService service = new OrderServiceImpl();
        WebRequestHandler getAssets = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {

                return AssetController.createAssetController(context).serve();
            }
        };

        WebRequestHandler searchResult = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return new ResultController(context, bookService).getResult();
            }
        };
        WebRequestHandler createOrder = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return new OrderListController(context, service).createOrder();
            }
        };

        WebRequestHandler addBook = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return AddBookController.createAddBookController(context, addBookService).createBook();
            }
        };

        WebRequestHandler display = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context){
                return new DisplayBooksController(context, bookService).list();
            }
        };
        routeMap.get("/Admin.html", renderTemplate(ViewTemplates.Admin));
        routeMap.get("/placeOrder.html", renderTemplate(ViewTemplates.placeOrder));
        routeMap.get("public/css/*", getAssets);
        routeMap.get("/addbook.html", renderTemplate(ViewTemplates.AddBook));
        routeMap.post("/addbook", addBook);
        routeMap.post("/addOrder", createOrder);
        routeMap.post("/SearchBook", searchResult);
        WebRequestHandler UpdateBook= new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
        return new updateBookController(context,bookService).update();
            }
        };
        routeMap.post("/UpdateBook", UpdateBook);
        routeMap.get("/", display);

    }

    private static WebRequestHandler renderTemplate(final ViewTemplates template) {
        return new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return RequestHandlerResult.ok(context.render(template));
            }
        };
    }
}
