package summary;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import services.OrderService;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;


public class Report {

    private final OrderService orderService;

    public Report(OrderService orderService) {
        this.orderService = orderService;
    }

    public String getReport() {
        Velocity.init(getProperties());
        Template template = Velocity.getTemplate("dailyReport.html");
        VelocityContext context = new VelocityContext();
        context.put("transactions", orderService.getTodaySales());
        Writer writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

    private Properties getProperties() {
        Properties p = new Properties();
        p.setProperty("resource.loader", "file");
        p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        p.setProperty("file.resource.loader.path", "src/summary/templates/");
        p.setProperty("file.resource.loader.cache", "false");
        p.setProperty("file.resource.loader.modificationCheckInterval", "0");
        return p;
    }

}
