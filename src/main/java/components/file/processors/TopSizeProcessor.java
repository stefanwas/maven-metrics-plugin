package components.file.processors;

import model.Metric;
import model.TextFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopSizeProcessor implements TextFileProcessor {

    private static final int TOP_LIST_SIZE = 10;
    private final List<TextFile> topLineFiles = new ArrayList<>(TOP_LIST_SIZE + 1);

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
        while (index < topLineFiles.size()) {
            if ( textFile.getLines().length > topLineFiles.get(index).getLines().length) {
                break;
            }
            index++;
        }
        topLineFiles.add(index, textFile);

        // remove last file if list size exceeds the limit
        if (topLineFiles.size() > TOP_LIST_SIZE) {
            topLineFiles.remove(TOP_LIST_SIZE);
        }
    }

    private int getLastFileSize() {
        return !this.topLineFiles.isEmpty() ? topLineFiles.get(topLineFiles.size()-1).getLines().length : -1;
    }

    @Override
    public List<Metric> getMetrics() {
        Metric metric = new Metric("TOP_LINE_FILES", "Top 10 size files");
        topLineFiles.forEach(file -> metric.getElements().put(file.getFile().getName(), file.getLines().length + " lines"));
        return Arrays.asList(metric);
    }
}
