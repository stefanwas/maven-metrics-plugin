package components.file.processors;

import model.Metric;
import model.TextFile;

import java.util.Arrays;
import java.util.List;

public class FileCountProcessor implements TextFileProcessor {

    public static final String METRIC_NAME = "FILE_COUNTER";

    private int numberOfFiles = 0;

    @Override
    public void process(TextFile file) {
        if (file.getFile().isFile()) {
            this.numberOfFiles++;
        }
    }

    @Override
    public List<Metric> getMetrics() {
        Metric metric = new Metric(METRIC_NAME, null);
        metric.getElements().put("total files", String.valueOf(numberOfFiles));
        return Arrays.asList(metric);
    }


}
