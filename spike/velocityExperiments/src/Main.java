import step.web.framework.PropertyBag;
import step.web.framework.ViewFileTemplate;
import step.web.framework.impl.VelocityViewEngine;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        VelocityViewEngine engine = new VelocityViewEngine();
        PropertyBag bag = new PropertyBag();
        List<String> customersList = new ArrayList<String>();
        customersList.add("Hemanth");
        customersList.add("chethan");
        Person person = new Person("chethan", 19);
        bag.add("person", person);
        bag.add("customersList",customersList);
        String result = engine.render(bag, new ViewFileTemplate() {
            @Override
            public String getTemplateFileName() {
                return "one.html";
            }
        });
        String result1 = engine.render(bag, new ViewFileTemplate() {
            @Override
            public String getTemplateFileName() {
                return "sample.html";
            }
        });
        System.out.println(result);
        System.out.println(result1);
    }
}
