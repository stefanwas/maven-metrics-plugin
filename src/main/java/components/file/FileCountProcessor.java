package components.file;

import model.Metric;
import java.io.File;

public class FileCountProcessor {

    public static final String METRIC_NAME = "FILE_COUNTER";

    private int numberOfFiles = 0;

    public void process(File file) {
        if (file.isFile()) {
            this.numberOfFiles++;
        }
    }

    public Metric getMetric() {
        Metric metric = new Metric(METRIC_NAME, null);
        metric.getElements().put("total files", String.valueOf(numberOfFiles));
        return metric;
    }


}
