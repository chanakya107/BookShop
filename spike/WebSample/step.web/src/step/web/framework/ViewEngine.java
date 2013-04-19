package step.web.framework;

public interface ViewEngine{
    String render(PropertyBag bag, ViewFileTemplate viewFileTemplate);
}
