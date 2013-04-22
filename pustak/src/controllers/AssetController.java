package controllers;

import step.web.framework.RequestHandlerResult;
import step.web.framework.WebContext;

public class AssetController {
    private WebContext context;
    private String pathRoot = "/";
    private String localAssetFolderPath = "content/";

    private AssetController(WebContext context) {
        this.context = context;
    }

    public static AssetController createAssetController(WebContext context) {
        if (context == null)
            throw new IllegalArgumentException("Cannot create AssetController of Webcontext : " + context);
        return new AssetController(context);
    }

    public RequestHandlerResult serve() {
        String localPath = translatePath();
        return context.sendFile(localPath);
    }

    private String translatePath() {
        return localAssetFolderPath + context.requestPath().replaceFirst(pathRoot, "");
    }
}
