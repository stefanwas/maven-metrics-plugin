package components.file;

import components.Component;
import components.Context;
import components.file.processors.FileCountProcessor;
import components.file.processors.TextFileProcessor;
import components.file.processors.TextLinesDistributionProcessor;
import model.TextFile;
import utils.SourceUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileComponent extends Component{

    private DirectoryReader directoryReader;
    private final List<TextFileProcessor> processors = new ArrayList<>();

    @Override
    public void init() {
        this.directoryReader = new DirectoryReader();

        this.processors.add(new FileCountProcessor());
        this.processors.add(new TextLinesDistributionProcessor());
    }

    @Override
    public void process(Context context) throws RuntimeException {

        List<TextFile> textFiles = null;
        try {
            textFiles = getTextFiles(context);
        } catch (IOException e) {
            throw new RuntimeException("Exception while src file content reading.", e);
        }

        for (TextFileProcessor processor : processors) {
            textFiles.forEach(processor::process);
        }

        this.processors.forEach(processor -> context.getMetrics().addAll(processor.getMetrics()));

    }

    private List<TextFile> getTextFiles(Context context) throws IOException {
        this.directoryReader.init(context.getSettings().getSrcRootDirs());
        List<File> srcFiles = this.directoryReader.getAllSrcFiles();

        List<TextFile> srcTextFiles = new LinkedList<>();
        Charset srcEncoding = Charset.forName(context.getSettings().getSrcEncoding());
        for (File file : srcFiles) {
            if (file.isFile()) {
                srcTextFiles.add(createTextFile(file, srcEncoding));
            }
        }

        return srcTextFiles;
    }

    private TextFile createTextFile(File file, Charset encoding) throws IOException {
        String content = SourceUtil.readFileContent(file, encoding);
        String[] lines = SourceUtil.realFileAsTextLines(content);
        return new TextFile(file, content, lines);
    }
}
