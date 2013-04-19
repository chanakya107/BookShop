package step.web.framework;


import org.apache.velocity.context.Context;

import java.util.HashMap;
import java.util.Map;

public class PropertyBag {
    Map<String, Object> map = new HashMap<String, Object>();
    public void add(String name, Object value){
        map.put(name, value);
    }

    public void applyTo(Context context) {
        for (String key : map.keySet()) {
            context.put(key, map.get(key));
        }
    }
}
