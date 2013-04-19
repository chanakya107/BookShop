package step.web.framework;

public interface WebContext {
    void bind(String name, Object value);

    String render(ViewFileTemplate template);

    String requestParameter(String name);

    String requestPath();

    RequestHandlerResult sendFile(String filePath);

    String requestBodyField(String name);

    void debugRequest();

    String redirectTo(String url);

    WebSession getOrCreateSession();

    WebSession getSession();

    WebCookie getRequestCookie(String name);

    WebCookie createResponseCookie(String name, String value);

    RequestHandlerResult sendRequestedAsset(String assetRootUrl, String assetRootFolder, boolean cacheOnClient);
}
