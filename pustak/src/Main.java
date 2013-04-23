import controllers.*;
import model.DataBase;
import services.*;
import services.impl.*;
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
        DataBase dataBase = new DataBase();
//        Todo: move service
        final BookService bookService = new BookServiceImpl(dataBase);
        final OrderService orderService = new OrderServiceImpl(dataBase);

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
                return new OrderController(context, orderService).createOrder();
            }
        };

        WebRequestHandler viewOrder = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext webContext) {
                return new OrderController(webContext, orderService).getOrders();
            }
        };

        WebRequestHandler addBook = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return AddBookController.createAddBookController(context, bookService).createBook();
            }
        };

        WebRequestHandler UpdateBook = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return new updateBookController(context, bookService).update();
            }
        };

        WebRequestHandler display = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return new DisplayBooksController(context, bookService).list();
            }
        };


        WebRequestHandler placeOrder = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext webContext) {
                return new OrderController(webContext, orderService).placeOrder();
            }
        };

        routeMap.get("/", renderTemplate(ViewTemplates.Index));
        routeMap.get("/admin.html", renderTemplate(ViewTemplates.Admin));
        routeMap.get("/index.html", renderTemplate(ViewTemplates.Index));
        routeMap.post("/placeOrder", placeOrder);
        routeMap.get("public/css/*", getAssets);
        routeMap.get("/addbook.html", renderTemplate(ViewTemplates.AddBook));
        routeMap.get("/placeOrder.html", renderTemplate(ViewTemplates.placeOrder));
        routeMap.get("/ViewOrders.html", renderTemplate(ViewTemplates.DisplayOrders));
        routeMap.post("/placeOrder", placeOrder);
        routeMap.get("public/css/*", getAssets);
        routeMap.post("/addbook", addBook);
        routeMap.post("/viewOrder", viewOrder);
        routeMap.post("/addOrder", createOrder);
        routeMap.post("/searchBook", searchResult);
        routeMap.post("/display", display);
        routeMap.post("/UpdateBook", UpdateBook);
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
