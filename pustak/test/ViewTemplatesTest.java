import org.junit.Assert;
import org.junit.Test;
import views.ViewTemplates;

public class ViewTemplatesTest {
    @Test
    public void aVoid() {
        ViewTemplates templates = ViewTemplates.Admin;
        Assert.assertEquals(templates.getTemplateFileName(), "content/Admin.html");
    }
}
