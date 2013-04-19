import controllers.AssetController;
import controllers.OrderListController;
import controllers.ResultController;
import controllers.ViewTemplates;
import services.BookService;
import services.BookServiceImpl;
import services.OrderService;
import services.impl.OrderServiceImpl;
import step.web.framework.RequestHandlerResult;
import step.web.framework.RouteMap;
import step.web.framework.WebContext;
import step.web.framework.WebRequestHandler;

public class Main {
    public static void main(String[] args) {
        initializeRoutes();
    }

    private static void initializeRoutes() {
        RouteMap routeMap = RouteMap.create();
        final OrderService service = new OrderServiceImpl();

        final BookService bookService = new BookServiceImpl();
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

        routeMap.get("/Admin.html", renderTemplate(ViewTemplates.Admin));
        routeMap.get("/placeOrder.html", renderTemplate(ViewTemplates.placeOrder));
        routeMap.get("public/css/*", getAssets);
        routeMap.post("/addOrder", createOrder);
        routeMap.post("/SearchBook", searchResult);
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
