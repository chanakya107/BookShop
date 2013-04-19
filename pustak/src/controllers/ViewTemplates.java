package controllers;

import step.web.framework.ViewFileTemplate;

public enum ViewTemplates implements ViewFileTemplate {
    Admin("content/admin.html"),
    placeOrder("content/placeOrder.html"),
    orderSuccessful("content/orderSuccessful.html");
    private String path;

    private ViewTemplates(String path) {
        this.path = path;
    }

    @Override
    public String getTemplateFileName() {
        return path;
    }
}
