package metrics.java;

import metrics.Metric;

public interface JavaProcessor {
    void init();
    void process(String fileContent);
    Metric getMetric();
}
