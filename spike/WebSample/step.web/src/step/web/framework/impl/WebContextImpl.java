package step.web.framework.impl;

import spark.Request;
import spark.Response;
import step.web.framework.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class WebContextImpl implements WebContext {

    private final Request request;
    private final Response response;
    private final ViewEngine viewEngine;
    private final PropertyBag bag = new PropertyBag();

    public WebContextImpl(Request request, Response response, ViewEngine viewEngine) {

        this.request = request;
        this.response = response;
        this.viewEngine = viewEngine;
    }

    @Override
    public void bind(String name, Object value) {
     bag.add(name, value);
    }

    @Override
    public String render(ViewFileTemplate template) {
        return viewEngine.render(bag, template);
    }

    @Override
    public String requestParameter(String name) {
        return request.params(name);
    }
    @Override
    public String requestPath(){
        return request.pathInfo();
    }

    Dictionary<String, String> mimeTypes = createMimeTypes();

    private static Dictionary<String, String> createMimeTypes() {
        String supportedMimeTypes = "js:application/javascript,css:text/css,png:image/png,jpg:image/jpeg,gif:image/gif,txt:text/plain,html:text/html,ico:image/x-icon";
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        String[] items = supportedMimeTypes.split(",");
        for (int i = 0; i < items.length; i++) {
            String[] parts = items[i].split(":");
            hashtable.put(parts[0], parts[1]);
        }
        return hashtable;
    }
    private String getMimeType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        String mimeType = mimeTypes.get(extension);
        if (mimeType == null){
            mimeType = "unknown";
            System.out.println("dont know mimetype for "+fileName);
        }
        //System.out.println(fileName + " has  extension " + extension + " and mimeType " + mimeType);
        return mimeType;
    }

    private int writeFileToStream(File file, Writer writer) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(file));
        int total = 0;
        try {
            char[] buffer = new char[1024 * 256];
            int chars;
            while ((chars = input.read(buffer)) > 0){
                writer.write(buffer, 0, chars);
                total+= chars;
            }
            writer.flush();
            return total;

        } finally {
            input.close();
        }
    }


    private RequestHandlerResult renderFileNotFound(String filePath) {
        System.out.println("could not find file "+new File(filePath).getAbsolutePath()+" when handling "+request.pathInfo());
        return RequestHandlerResult.notFound("File not found");
    }

    @Override
    public RequestHandlerResult sendFile(String filePath) {
        File file = new File(filePath);
        return file.exists() ? renderFile(file) : renderFileNotFound(filePath);
    }

    @Override
    public String requestBodyField(String name) {
        return getBodyFields(name);
    }

    private String getBodyFields(String name) {
        String[] parts = request.body().split("&");
        for(String part:parts){
            String[] pieces = part.split("=");
            if(pieces[0].equals(name)) return pieces[1];
        }
        return null;
    }

    @Override
    public void debugRequest() {
        System.out.println("=============");
        System.out.println("path " +request.pathInfo());
        System.out.println("body " +request.body());
        System.out.println("cont len " +request.contentLength());
        System.out.println("cont type " +request.contentType());
        System.out.println("headers " +request.headers());
        System.out.println("host " +request.host());
        System.out.println("ip " +request.ip());
        System.out.println("port " + request.port());
        System.out.println("querymap " +request.queryMap());
        System.out.println("queryparam " +request.queryParams());
        System.out.println("querystring " +request.queryString());
        System.out.println("method " +request.requestMethod());
        System.out.println("scheme " +request.scheme());


        HttpServletRequest rawRequest = request.raw();
        Enumeration parameterNames = rawRequest.getParameterNames();
        System.out.println("Parameters...........");
        while(parameterNames.hasMoreElements()) {
            String name = (String) parameterNames.nextElement();
            String value = rawRequest.getParameter(name);
            System.out.println(name + ":" + value);
        }
        System.out.println("=============");
    }

    private RequestHandlerResult renderFile(File file) {
        String name = file.getName();
        final String mime = getMimeType(name);
        //System.out.println("Serving " + mime + ": " + name);
        response.raw().setContentType(mime);

        try {
            writeFileToStream(file, response.raw().getWriter());

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return RequestHandlerResult.serverError(e.toString());
        }
       return RequestHandlerResult.ok("ok");
    }
    @Override
    public String redirectTo(String url){
        System.out.println("redirecting from "+request.pathInfo()+" to "+url);
        response.redirect(url);
        return url;
    }

    @Override
    public WebSession getOrCreateSession(){
       return new WebSessionImpl(request.session(true));
    }

    @Override
    public WebSession getSession(){
        return new WebSessionImpl(request.session());
    }

    @Override
    public WebCookie getRequestCookie(String name){

        Cookie[] cookies = (Cookie[]) request.raw().getCookies();
        for(Cookie cookie: cookies){
            if(cookie.getName().equals(name))
                return new WebCookieImpl(cookie);
        }
        return null;
    }
    @Override
    public WebCookie createResponseCookie(String name,String value){
        Cookie cookie = new Cookie(name, value);
        response.raw().addCookie(cookie);
        return new WebCookieImpl(cookie);
    }
}
