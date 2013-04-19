package step.web.framework;

public interface WebRequestFilter{
    RequestHandlerResult operation(WebContext context);
}
