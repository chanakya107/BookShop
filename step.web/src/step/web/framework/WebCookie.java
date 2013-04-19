package step.web.framework;

public interface WebCookie{

    String getName();

    String getValue();

    void setValue(String value);

    int getVersion();

    void setVersion(int version);

    String getDomain();

    void setDomain(String domain);

    String getPath();

    void setPath(String path);
}
