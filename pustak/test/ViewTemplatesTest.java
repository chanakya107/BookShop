import views.ViewTemplates;
import org.junit.Assert;
import org.junit.Test;

public class ViewTemplatesTest {
    @Test
    public void aVoid(){
        ViewTemplates templates = ViewTemplates.Admin;
        Assert.assertEquals(templates.getTemplateFileName(), "content/admin.html");
    }
}
