package io.reader;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class SourceDirectoryReader {

    public List<File> readSource(String location) {

        List<File> allNodes = new LinkedList<>();

        File root = new File(location);
        readAll(root, allNodes);


        return allNodes;
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
