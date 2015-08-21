package processors.file;

import model.Source;
import model.SourceMetrics;
import processors.SourceProcessor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileCountProcessor extends SourceProcessor<File> {

    @Override
    public SourceMetrics<File> process(Source<File> source) {

        FileCountMetrics fileCountMetric = new FileCountMetrics();
        fileCountMetric.setNumberOfFiles(source.getElements().size());
        
        return fileCountMetric;
    }

    public static class FileCountMetrics extends SourceMetrics<File> {

        protected int numberOfFiles = 0;
        
        public FileCountMetrics() {
            this.name = "FILE_COUNT_METRIC";
            this.description = "Number of all source files in the project.";
        }

        public int getNumberOfFiles() {
            return numberOfFiles;
        }

        public void setNumberOfFiles(int numberOfFiles) {
            this.numberOfFiles = numberOfFiles;
        }

        @Override
        public Map<String, String> getMetricsMap() {
            Map<String, String> metrics = new HashMap<>();
            metrics.put("total files", String.valueOf(numberOfFiles));
            return metrics;
        }
    }


}
