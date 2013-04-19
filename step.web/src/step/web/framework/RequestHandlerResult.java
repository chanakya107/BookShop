package step.web.framework;

public class RequestHandlerResult {

    private boolean shouldHalt;
    private int status;
    private String content;

    private RequestHandlerResult(boolean shouldHalt, int status, String content) {

        this.shouldHalt = shouldHalt;
        this.status = status;
        this.content = content;
    }

    public boolean hasToHalt() {
        return shouldHalt;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public static RequestHandlerResult ok(String content) {
        return new RequestHandlerResult(false,200,content);
    }

    public static RequestHandlerResult proceed() {
        return new RequestHandlerResult(false,200,"");
    }

    public static RequestHandlerResult unAuthorized(String content) {
        return new RequestHandlerResult(true,401,content);
    }

    public static RequestHandlerResult notFound(String content) {
        return new RequestHandlerResult(true,404,content);
    }

    public static RequestHandlerResult serverError(String content) {
        return new RequestHandlerResult(true,500,content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestHandlerResult that = (RequestHandlerResult) o;

        if (shouldHalt != that.shouldHalt) return false;
        if (status != that.status) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (shouldHalt ? 1 : 0);
        result = 31 * result + status;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
