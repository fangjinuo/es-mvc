package com.jn.esmvc.service.request.cat.action;

import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.function.Consumer2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CatNodeAttrsMetrics {
    private static final Map<String, String> nameMapping = new LinkedHashMap<String, String>();
    private static final Set<String> metricNames = Collects.emptyHashSet(true);

    static {
        nameMapping.put("node", "name");
        nameMapping.put("h", "host");
        nameMapping.put("i", "ip");
        nameMapping.put("attr.name", "attr");
        nameMapping.put("attr.value", "value");
        nameMapping.put("nodeId", "id");
        nameMapping.put("p", "pid");
        nameMapping.put("po", "port");


        Collects.forEach(nameMapping, new Consumer2<String, String>() {
            @Override
            public void accept(String key, String value) {
                metricNames.add(key);
                metricNames.add(value);
            }
        });

    }

    public static String getStandardMetric(String metric) {
        String name = nameMapping.get(metric);
        if (name == null) {
            name = metric;
        }
        return name;
    }

    public static boolean isValidMetric(String metric) {
        return metricNames.contains(metric);
    }
}
