package controllers;

import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;

public class AssetController {
    private WebContext context;

    public AssetController(WebContext context) {
        this.context = context;
    }

    public RequestHandlerResult serve() {
        String path = "out/production/WebSample/public/" + context.requestPath().replaceFirst("/", "");
        return RequestHandlerResult.ok(context.sendFile(path).getContent());
    }
}
