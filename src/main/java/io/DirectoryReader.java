package io;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Loads all files and sub-directories into allNodes.
 */
public class DirectoryReader {

    private File root = null;
    private List<File> allNodes = new LinkedList<>();

    public void init(String rootDir) {
        this.root = new File(rootDir);
        addAllFilesToNodes(this.root);
    }

    private void addAllFilesToNodes(File root) {
        File[] subNodes = root.listFiles();
        for (File node : subNodes) {
            this.allNodes.add(node);
            if (node.isDirectory()) {
                addAllFilesToNodes(node);
            }
        }
    }

    public List<File> getAllNodes() {
        return this.allNodes;
    }

}
