package metrics.file;

import metrics.Metric;
import metrics.Processor;

import java.io.File;

public class FileCountProcessor implements Processor<File> {

    public static final String METRIC_NAME = "FILE_COUNTER";
    private int numberOfFiles = 0;

    @Override
    public void init() {
        this.numberOfFiles = 0;
    }

    @Override
    public void process(File file) {
        if (file.isFile()) {
            this.numberOfFiles++;
        }
    }

    @Override
    public Metric getMetric() {
        Metric metric = new Metric(METRIC_NAME, null);
        metric.getNamedValues().put("total files", String.valueOf(numberOfFiles));
        return metric;
    }
}
