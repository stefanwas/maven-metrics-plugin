package components.file;

import components.Component;
import components.Context;
import model.Metric;

import java.io.File;
import java.util.List;

public class FileComponent extends Component{

    private DirectoryReader directoryReader;
    private FileCountProcessor fileCountProcessor;

    @Override
    public void init() {
        this.directoryReader = new DirectoryReader();
        this.fileCountProcessor = new FileCountProcessor();
    }

    @Override
    public void process(Context context) {

        this.directoryReader.init(context.getSettings().getSrcRoot());
        List<File> srcFiles = this.directoryReader.getAllSrcFiles();
        srcFiles.forEach(this.fileCountProcessor::process);

        Metric metric = this.fileCountProcessor.getMetric();

        context.getMetrics().add(metric);
    }
}
