package step.web.framework.impl;

import step.web.framework.WebCookie;

import javax.servlet.http.Cookie;

public class WebCookieImpl implements WebCookie {

    private Cookie cookie;

    public WebCookieImpl(Cookie cookie) {
        this.cookie = cookie;
    }
    @Override
    public String getName(){
        return cookie.getName();
    }
    @Override
    public String getValue(){
        return cookie.getValue();
    }
    @Override
    public void setValue(String value){
        cookie.setValue(value);
    }
    @Override
    public int getVersion(){
        return cookie.getVersion();
    }
    @Override
    public void setVersion(int version){
        cookie.setVersion(version);
    }
    @Override
    public String getDomain(){
        return cookie.getDomain();
    }
    @Override
    public void setDomain(String domain){
        cookie.setDomain(domain);
    }
    @Override
    public String getPath(){
        return cookie.getPath();
    }
    @Override
    public void setPath(String path){
        cookie.setPath(path);
    }


}
