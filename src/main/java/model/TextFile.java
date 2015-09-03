package model;

import java.io.File;

public class TextFile {
    private final File file;
    private final String fullText;
    private final String[] lines;

    public TextFile(File file, String fullText, String[] lines) {
        this.file = file;
        this.fullText = fullText;
        this.lines = lines;
    }

    public File getFile() {
        return file;
    }

    public String getFullText() {
        return fullText;
    }

    public String[] getLines() {
        return lines;
    }
}
