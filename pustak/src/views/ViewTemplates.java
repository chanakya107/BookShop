package views;

import step.web.framework.ViewFileTemplate;

public enum ViewTemplates implements ViewFileTemplate {
    Admin("./admin.html"),
    SearchResult("/searchResult.html"),
    AddBook("content/addbook.html");
    private String path;

    private ViewTemplates(String path) {
        this.path = path;
    }

    @Override
    public String getTemplateFileName() {
        return path;
    }
}
