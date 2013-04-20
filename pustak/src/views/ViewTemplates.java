package views;

import step.web.framework.ViewFileTemplate;

public enum ViewTemplates implements ViewFileTemplate {
    Admin("content/admin.html"),
    AddBook("content/addbook.html"),
    placeOrder("content/placeOrder.html"),
    orderSuccessful("content/orderSuccessful.html"),
    SearchResult("content/searchResult.html"),
    Index("content/Index.html"),
    DisplayOrders("content/ViewOrders.html");
    private String path;

    private ViewTemplates(String path) {
        this.path = path;
    }

    @Override
    public String getTemplateFileName() {
        return path;
    }
}
