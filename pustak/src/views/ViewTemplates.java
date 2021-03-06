package views;

import step.web.framework.ViewFileTemplate;

public enum ViewTemplates implements ViewFileTemplate {
    AddBook("content/addBook.html"),
    placeOrder("content/placeOrder.html"),
    orderSuccessful("content/orderSuccessful.html"),
    Admin("content/Admin.html"),
    DisplayOrders("content/viewOrders.html"),
    Index("content/Index.html"),
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
