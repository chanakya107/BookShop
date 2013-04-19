import controllers.AssetController;
import step.web.framework.RequestHandlerResult;
import step.web.framework.RouteMap;
import step.web.framework.WebContext;
import step.web.framework.WebRequestHandler;
import views.ViewTemplate;

public class Main {
    public static void main(String[] args) {
        initializeRoutes();
    }

    private static void initializeRoutes() {
        RouteMap routeMap = RouteMap.create();

        WebRequestHandler getAssets = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return new AssetController(context).serve();
            }
        };
        routeMap.get("/", renderTemplate(ViewTemplate.Index));
        routeMap.get("/css/*", getAssets);
    }

    private static WebRequestHandler renderTemplate(final ViewTemplate template) {
        return new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return RequestHandlerResult.ok(context.render(template));
            }
        };
    }
}
