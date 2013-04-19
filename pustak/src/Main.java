import controllers.AssetController;
import controllers.ResultController;
import services.BookService;
import services.BookServiceImpl;
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
        final BookService bookService=new BookServiceImpl();
        WebRequestHandler getAssets = new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {

                return AssetController.createAssetController(context).serve();
            }
        };
        WebRequestHandler searchResult=new WebRequestHandler() {
            @Override
            public RequestHandlerResult operation(WebContext context) {
                return  new ResultController(context, bookService).getResult();
            }
        };

        routeMap.get("/Admin.html", renderTemplate(ViewTemplates.Admin));
        routeMap.post("/SearchBook",searchResult);
        routeMap.get("public/css/*", getAssets);

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
