package io.reader;

import model.Source;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class SourceDirectoryReader implements SourceReader<File> {

    @Override
    public Source<File> readSource(String location) {

        List<File> allNodes = new LinkedList<>();

        File root = new File(location);
        readAll(root, allNodes);

        Source<File> source = new Source<>();
        source.setElements(allNodes);

        return source;
    }

    private void readAll(File root, List<File> allNodes) {
        File[] subNodes = root.listFiles();
        for (File node : subNodes) {
            allNodes.add(node);
            if (node.isDirectory()) {
                readAll(node, allNodes);
            }
        }
    }
}
