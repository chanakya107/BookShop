package step.web.framework;

import step.web.framework.impl.RouteMapImpl;

public abstract class RouteMap {
    public static RouteMap create(){
      return new RouteMapImpl();
    }

    public abstract void get(String url, WebRequestHandler handler);
    public abstract void post(String url, WebRequestHandler handler);

    public abstract void delete(String url, WebRequestHandler handler);

    public abstract void put(String url, WebRequestHandler handler);

    public abstract void before(String url, WebRequestFilter handler);
    public abstract void after(String url, WebRequestFilter handler);
}
