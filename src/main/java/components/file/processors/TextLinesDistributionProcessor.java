package components.file.processors;

import model.Metric;
import model.TextFile;

import java.util.Arrays;
import java.util.List;

public class TextLinesDistributionProcessor implements TextFileProcessor {
    private static final int[] THRESHOLDS = {50, 100, 200, 500, 1000, 2000, 5000, Integer.MAX_VALUE};
    private int[] linesPerFile = {0, 0, 0, 0, 0, 0, 0, 0};

    @Override
    public void process(TextFile textFile) {

        String[] lines = textFile.getLines();
        if (lines != null) {
            for (int i = 0; i<THRESHOLDS.length; i++) {
                if (lines.length <= THRESHOLDS[i]) {
                    linesPerFile[i]++;
                    break;
                }
            }
        }
    }

    @Override
    public List<Metric> getMetrics() {
        Metric fileLinesDistributionMetric = new Metric("LINES_PER_FILE_DISTRIBUTION", null);

        for (int i = 0; i<THRESHOLDS.length - 1; i++) {
            fileLinesDistributionMetric.getElements().put("files less then " + THRESHOLDS[i] + " lines", String.valueOf(linesPerFile[i]));
        }

        return Arrays.asList(fileLinesDistributionMetric);
    }


}
