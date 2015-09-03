package components.file.processors;

import model.Metric;
import model.TextFile;

import java.util.List;

public interface TextFileProcessor {
    void process(TextFile textFile);
    List<Metric> getMetrics();
}
