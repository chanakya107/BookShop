import controllers.AssetController;
import controllers.OrderListController;
import controllers.ViewTemplates;
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
        WebRequestHandler getAssets = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {

                return AssetController.createAssetController(context).serve();
            }
        };

        routeMap.get("/Admin.html", renderTemplate(ViewTemplates.Admin));
        routeMap.get("/placeOrder.html", renderTemplate(ViewTemplates.placeOrder));
        routeMap.get("public/css/*", getAssets);
        WebRequestHandler createOrder = new
                WebRequestHandler() {
                    @Override
                    public RequestHandlerResult operation(WebContext context) {
                        return new OrderListController(context, service).createOrder();
                    }
                };
        routeMap.post("/addOrder", createOrder);
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
