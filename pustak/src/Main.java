import controllers.*;
import model.DataBase;
import services.BookService;
import services.OrderService;
import services.impl.BookServiceImpl;
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
        DataBase dataBase = new DataBase();

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
                return new ViewOrderController(webContext, orderService).getOrders();
            }
        };

        WebRequestHandler addBook = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return AddBookController.createAddBookController(context, bookService).createBook();
            }
        };

        WebRequestHandler fetchbook = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return DisplayBookController.createController(context, bookService).fetchBook();
            }
        };

        WebRequestHandler display = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return new DisplayBooksController(context, bookService).list();
            }
        };

        WebRequestHandler updatebook = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return UpdateBookController.createUpdateBookController(context,bookService).update();
            }
        };

        WebRequestHandler placeOrder = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext webContext) {
                return new OrderController(webContext, orderService).placeOrder();
            }
        };

        WebRequestHandler displayPurchaserPage=new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext webContext) {
                return new PurchaserPageController(webContext,bookService).display();
            }
        };
        WebRequestHandler displayAdminPage=new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext webContext) {
                return new AdminPageController(webContext,bookService).display();
            }
        };
        routeMap.get("/", displayPurchaserPage);
        routeMap.get("/admin.html",displayAdminPage);
        routeMap.get("/index.html",displayPurchaserPage);
        routeMap.get("/addbook.html", renderTemplate(ViewTemplates.AddBook));
        routeMap.get("/placeOrder.html", renderTemplate(ViewTemplates.placeOrder));
        routeMap.post("/placeOrder", placeOrder);
        routeMap.post("/addbook", addBook);
        routeMap.get("/viewOrders", viewOrder);
        routeMap.post("/addOrder", createOrder);
        routeMap.post("/searchBook", searchResult);
        routeMap.get("/", renderTemplate(ViewTemplates.Index));
        routeMap.post("/dispatchBook", renderTemplate(ViewTemplates.DispatchedBooks));
        routeMap.post("/display", display);
        routeMap.post("/fetchbook", fetchbook);
        routeMap.post("/updatebook", updatebook);

        routeMap.get("public/css/*", getAssets);
        routeMap.get("public/images/*", getAssets);

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
