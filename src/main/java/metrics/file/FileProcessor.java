package metrics.file;

import metrics.Metric;

import java.io.File;

public interface FileProcessor {
    void init();
    void process(File file);
    Metric getMetric();
}
