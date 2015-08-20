package metrics;

import metrics.file.FileCountProcessor;
import metrics.file.FileProcessor;
import metrics.java.ClassCountProcessor;
import metrics.java.JavaProcessor;
import utils.SourceFileReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


//TODO remember about file encoding
//https://maven.apache.org/plugin-developers/common-bugs.html
public class Executor {

    private String srcMainDir;
    private String targetDir;

    private final List<FileProcessor> fileProcessors = new ArrayList<>();
    private final List<Processor> javaProcessors = new ArrayList<>();

    private final List<File> sourceNodes = new LinkedList<>();

    public void init() {

        initFileProcessors();
        initJavaProcessors();

    }

    private void initJavaProcessors() {
        ClassCountProcessor classCountProcessor = new ClassCountProcessor();
        classCountProcessor.init();
        this.javaProcessors.add(classCountProcessor);
    }

    private void initFileProcessors() {
        FileCountProcessor fileCountProcessor = new FileCountProcessor();
        fileCountProcessor.init();
        this.fileProcessors.add(fileCountProcessor);
    }


    public List<Metric> start() throws IOException {

        List<Metric> metrics = new ArrayList<>();

        for (FileProcessor processor : this.fileProcessors) {
            for (File node : sourceNodes) {
                processor.process(node);
            }
            Metric metric = processor.getMetric();
            metrics.add(metric);
        }

        for (Processor processor : this.javaProcessors) {
            for (File node : sourceNodes) {
                if (node.isFile()) {
                    String source = SourceFileReader.readFile(node, Charset.defaultCharset());
                    processor.process(source);
                }
            }
            Metric metric = processor.getMetric();
            metrics.add(metric);
        }

        return metrics;
    }

    public List<File> getSourceNodes() {
        return this.sourceNodes;
    }
}
