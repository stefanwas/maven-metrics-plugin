package components.file.processors;

import model.Metric;
import model.TextFile;

import java.util.Arrays;
import java.util.List;

public class TotalTextLinesProcessor implements TextFileProcessor {

    private int totalNumberOfCodeLines = 0; //counts all lines - code, comments, braces, even empty lines

    @Override
    public void process(TextFile textFile) {
        String[] lines = textFile.getLines();
        if (lines != null) {
            totalNumberOfCodeLines += lines.length;
        }
    }

    @Override
    public List<Metric> getMetrics() {
        Metric metric = new Metric("TOTAL_LINES_COUNTER", null);
        metric.getElements().put("Total lines", String.valueOf(totalNumberOfCodeLines));
        return Arrays.asList(metric);
    }
}
