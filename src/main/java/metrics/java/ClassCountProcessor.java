package metrics.java;

import metrics.Metric;

public class ClassCountProcessor implements JavaProcessor {

    public static final String METRIC_NAME = "JAVA CLASS COUNTER";

    private int allClasses = 0;
    private int allInterfaces = 0;
    private int allEnums = 0;

    @Override
    public void init() {
    }

    @Override
    public void process(String fileContent) {
        //TODO
    }

    @Override
    public Metric getMetric() {
        Metric metric = new Metric(METRIC_NAME, null);

        metric.getNamedValues().put("total classes", String.valueOf(allClasses));
        metric.getNamedValues().put("total interfaces", String.valueOf(allInterfaces));
        metric.getNamedValues().put("total enums", String.valueOf(allEnums));

        return metric;
    }
}
