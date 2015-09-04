package components.file;

import components.Component;
import components.Context;
import components.file.processors.*;
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
        this.processors.add(new TotalTextLinesProcessor());
        this.processors.add(new TextLinesDistributionProcessor());
        this.processors.add(new TopSizeProcessor());
    }

    @Override
    public void process(Context context) {

        List<TextFile> textFiles = getTextFiles(context);
        for (TextFileProcessor processor : processors) {
            textFiles.forEach(processor::process);
        }

        this.processors.forEach(processor -> context.getMetrics().addAll(processor.getMetrics()));
    }

    private List<TextFile> getTextFiles(Context context) {
        this.directoryReader.init(context.getSettings().getSrcRootDirs());
        List<File> srcFiles = this.directoryReader.getAllSrcFiles();

        List<TextFile> srcTextFiles = new LinkedList<>();
        Charset srcEncoding = Charset.forName(context.getSettings().getSrcEncoding());
        try {
            for (File file : srcFiles) {
                if (file.isFile()) {
                    srcTextFiles.add(createTextFile(file, srcEncoding));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception while src file content reading.", e);
        }

        return srcTextFiles;
    }

    private TextFile createTextFile(File file, Charset encoding) throws IOException {
        String content = SourceUtil.readFileContent(file, encoding);
        String[] lines = SourceUtil.realFileAsTextLines(content);
        return new TextFile(file, content, lines);
    }
}
