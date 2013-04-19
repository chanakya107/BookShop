package views;

import step.web.framework.ViewFileTemplate;

public enum ViewTemplate implements ViewFileTemplate {
    Index("public/index.html");
    private String fileName;

    ViewTemplate(String fileName) {

        this.fileName = fileName;
    }

    @Override
    public String getTemplateFileName() {
        return fileName;
    }
}
