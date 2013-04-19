package step.web.framework.impl;

import spark.Session;
import step.web.framework.WebSession;

public class WebSessionImpl implements WebSession {
    private Session session;

    public WebSessionImpl(Session session) {

        this.session = session;
    }
    @Override
    public <T> T getValue(String name){
        return session.attribute(name);
    }
    @Override
    public <T> void setValue(String name, T value){
        session.attribute(name,value);
    }
    @Override
    public boolean isNew(){
        return session.isNew();
    }
    @Override
    public void invalidate(){
        session.invalidate();
    }

}

