package step.web.framework.impl;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.JarResourceLoader;
import step.web.framework.PropertyBag;
import step.web.framework.ViewEngine;
import step.web.framework.ViewFileTemplate;

import java.io.StringWriter;

public class VelocityViewEngine implements ViewEngine {
    private final VelocityEngine velocityEngine;

    public VelocityViewEngine() {
        velocityEngine = new VelocityEngine();
        // todo (mujir/chanakya) : remove this harcoding. Ideally configure this from a properties (http://click.apache.org/docs/velocity/developer-guide.html#configurationexamples)
        velocityEngine.setProperty("jar.resource.loader.path", "jar/Pustak.jar");
        velocityEngine.setProperty("jar.resource.loader.class", JarResourceLoader.class.getName());
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
