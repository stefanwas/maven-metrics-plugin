package components.file.processors;

import model.Metric;
import model.TextFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopSizeProcessor implements TextFileProcessor {

    private int topCountSize = 10;
    private final List<TextFile> topFiles = new ArrayList<>(topCountSize);

    @Override
    public void process(TextFile textFile) {
        if (textFile.getLines() != null) {
            if (topFiles.isEmpty() || textFile.getLines().length > topFiles.get(topFiles.size()-1).getLines().length) {
                addToTopFiles(textFile);
            }

        }
    }

    private void addToTopFiles(TextFile textFile) {
        for (int i=0; i<topFiles.size(); i++) {
            if (textFile.getLines().length > topFiles.get(i).getLines().length) {
                topFiles.add(i, textFile);
                return;
            }
        }
        if (topFiles.size() < topCountSize) {
            topFiles.add(textFile);
        }
    }

    @Override
    public List<Metric> getMetrics() {
        Metric metric = new Metric("TOP_LINE_FILES", null);
        topFiles.forEach(file -> metric.getElements().put(file.getFile().getName(), file.getLines().length + " lines"));
        return Arrays.asList(metric);
    }
}
