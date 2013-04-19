package step.web.framework.impl;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;
import step.web.framework.*;

public class RouteMapImpl extends RouteMap {
    private final VelocityViewEngine viewEngine = new VelocityViewEngine();

    @Override
    public void get(String url, final WebRequestHandler handler){
        spark.Spark.get(new Route(url) {
            @Override
            public Object handle(Request request, Response response) {
                try{
                    WebContext context = new WebContextImpl(request, response, viewEngine);
                    RequestHandlerResult result = handler.operation(context);
                    if(result.hasToHalt()){
                        halt(result.getStatus(),result.getContent());
                        return result.getContent();
                    }
                    else{
                        response.status(result.getStatus());
                        return result.getContent();
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                    e.printStackTrace();
                    return e.getMessage();
                }
            }
        });
    }

    @Override
    public void post(String url, final WebRequestHandler handler) {
        spark.Spark.post(new Route(url) {
            @Override
            public Object handle(Request request, Response response) {
                try{
                    WebContext context = new WebContextImpl(request, response, viewEngine);
                    RequestHandlerResult result = handler.operation(context);
                    if(result.hasToHalt()){
                        halt(result.getStatus(),result.getContent());
                        return result.getContent();
                    }
                    else{
                        response.status(result.getStatus());
                        return result.getContent();
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                    e.printStackTrace();
                    return e.getMessage();
                }
            }
        });
    }

    @Override
    public void delete(String url, final WebRequestHandler handler) {
        spark.Spark.delete(new Route(url) {
            @Override
            public Object handle(Request request, Response response) {
                try{
                    WebContext context = new WebContextImpl(request, response, viewEngine);
                    RequestHandlerResult result = handler.operation(context);
                    if(result.hasToHalt()){
                        halt(result.getStatus(),result.getContent());
                        return result.getContent();
                    }
                    else{
                        response.status(result.getStatus());
                        return result.getContent();
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                    e.printStackTrace();
                    return e.getMessage();
                }
            }
        });
    }

    @Override
    public void put(String url, final WebRequestHandler handler) {
        spark.Spark.put(new Route(url) {
            @Override
            public Object handle(Request request, Response response) {
                try{
                    WebContext context = new WebContextImpl(request, response, viewEngine);
                    RequestHandlerResult result = handler.operation(context);
                    if(result.hasToHalt()){
                        halt(result.getStatus(),result.getContent());
                        return result.getContent();
                    }
                    else{
                        response.status(result.getStatus());
                        return result.getContent();
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                    e.printStackTrace();
                    return e.getMessage();
                }
            }
        });
    }

    @Override
    public void before(String url, final WebRequestFilter handler) {
        spark.Spark.before(new Filter(url) {
            @Override
            public void handle(Request request, Response response) {
                try {
                    WebContext context = new WebContextImpl(request, response, viewEngine);
                    RequestHandlerResult result = handler.operation(context);
                    if(result.hasToHalt()){
                        halt(result.getStatus(),result.getContent());
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void after(String url, final WebRequestFilter handler) {
        spark.Spark.after(new Filter(url) {
            @Override
            public void handle(Request request, Response response) {
                try{
                    WebContext context = new WebContextImpl(request, response, viewEngine);
                    RequestHandlerResult result = handler.operation(context);
                    if(result.hasToHalt()){
                        halt(result.getStatus(),result.getContent());
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        });
    }
}
