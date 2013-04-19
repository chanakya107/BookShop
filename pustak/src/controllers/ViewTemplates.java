package controllers;

import step.web.framework.ViewFileTemplate;

public enum ViewTemplates implements ViewFileTemplate {
    Admin("pustak/content/admin.html"),
    placeOrder("pustak/content/placeOrder.html"),
    orderSuccessful("pustak/content/orderSuccessful.html");
    private String path;

    private ViewTemplates(String path) {
        this.path = path;
    }

    @Override
    public String getTemplateFileName() {
        return path;
    }
}
