import controllers.ViewTemplates;
import junit.framework.Assert;
import org.junit.Test;

public class ViewTemplatesTest {
    @Test
    public void aVoid(){
        ViewTemplates templates = ViewTemplates.Admin;
        Assert.assertEquals(templates.getTemplateFileName(),"./admin.html");
    }
}
