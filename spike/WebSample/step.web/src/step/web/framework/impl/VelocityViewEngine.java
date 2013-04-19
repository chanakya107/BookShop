package step.web.framework.impl;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import step.web.framework.PropertyBag;
import step.web.framework.ViewEngine;
import step.web.framework.ViewFileTemplate;

import java.io.StringWriter;

public class VelocityViewEngine implements ViewEngine {
    private final VelocityEngine velocityEngine;

    public VelocityViewEngine() {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
    }

    @Override
    public String render(PropertyBag bag, ViewFileTemplate viewFileTemplate) {
        VelocityContext velocityContext = new VelocityContext();
        Template template = velocityEngine.getTemplate(viewFileTemplate.getTemplateFileName());
        bag.applyTo(velocityContext);
        StringWriter writer = new StringWriter();
        template.merge(velocityContext, writer);
        return writer.toString();
    }
}
