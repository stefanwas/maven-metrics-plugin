package components.file;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Loads all files and sub-directories into allNodes.
 */
public class DirectoryReader {

    private List<File> roots = new LinkedList<>();
    private List<File> allNodes = new LinkedList<>();

    public void init(List<String> rootDirs) {
        rootDirs.stream().forEach(root -> this.roots.add(new File(root)));
        roots.stream().forEach(this::addAllFilesToNodes);
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

    public List<File> getAllSrcFiles() {
        return this.allNodes;
    }

}
