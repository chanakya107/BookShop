package step.web.framework;

public interface WebSession{

    <T> T getValue(String name);

    <T> void setValue(String name, T value);

    boolean isNew();

    void invalidate();
}

