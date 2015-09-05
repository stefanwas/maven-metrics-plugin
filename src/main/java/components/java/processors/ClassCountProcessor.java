package components.java.processors;

import model.Metric;

import java.util.HashMap;
import java.util.Map;

public class ClassCountProcessor  {

    public static final String METRIC_NAME = "JAVA CLASS COUNTER";

    private int allClasses = 0;
    private int allInterfaces = 0;
    private int allEnums = 0;

    public void process(String javaSource) {
        //TODO ...
    }


    public Metric getMetrics() {

        Metric metric = new Metric(METRIC_NAME, null);

        Map<String, String> elements = new HashMap<>();

        elements.put("total classes", String.valueOf(allClasses));
        elements.put("total interfaces", String.valueOf(allInterfaces));
        elements.put("total enums", String.valueOf(allEnums));

        metric.getElements().putAll(elements);

        return metric;
    }

}
