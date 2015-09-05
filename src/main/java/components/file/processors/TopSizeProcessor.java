package components.file.processors;

import model.Metric;
import model.TextFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopSizeProcessor implements TextFileProcessor {

    private int topCountSize = 10;
    private final List<TextFile> topFiles = new ArrayList<>(topCountSize + 1);

    @Override
    public void process(TextFile textFile) {
        if (textFile.getLines() != null) {
            if (textFile.getLines().length > getLastFileSize()) {
                addToTopFiles(textFile);
            }
        }
    }

    private void addToTopFiles(TextFile textFile) {
        int index = 0;
        while (index < topFiles.size()) {
            if ( textFile.getLines().length > topFiles.get(index).getLines().length) {
                break;
            }
            index++;
        }
        topFiles.add(index, textFile);

        // remove last file if list size exceeds the limit
        if (topFiles.size() > topCountSize) {
            topFiles.remove(topCountSize);
        }
    }

    private int getLastFileSize() {
        return !this.topFiles.isEmpty() ? topFiles.get(topFiles.size()-1).getLines().length : -1;
    }

    @Override
    public List<Metric> getMetrics() {
        Metric metric = new Metric("TOP_LINE_FILES", null);
        topFiles.forEach(file -> metric.getElements().put(file.getFile().getName(), file.getLines().length + " lines"));
        return Arrays.asList(metric);
    }
}
