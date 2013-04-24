package views;

import step.web.framework.ViewFileTemplate;

public enum ViewTemplates implements ViewFileTemplate {
    Admin("content/admin.html"),
    AddBook("content/addBook.html"),
    placeOrder("content/placeOrder.html"),
    orderSuccessful("content/orderSuccessful.html"),
    SearchResult("content/searchResult.html"),
    DisplayOrders("content/viewOrders.html"),
    Index("content/index.html"),
    DispatchedBooks("content/dispatchAcknowledgement.html"),
    FetchBook("content/updateStock.html"),
    UpdateAcknowledgement("content/updateSuccessfull.html");

    private String path;

    private ViewTemplates(String path) {
        this.path = path;
    }

    @Override
    public String getTemplateFileName() {
        return path;
    }
}
