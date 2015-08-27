package components;

import java.util.List;

/**
 * A wrapper for all maven project settings important for source processing.
 */
public class Settings {
    private List<String> srcRootDirs;
    private String targetDir;
    private String srcEncoding;

    public List<String> getSrcRootDirs() {
        return srcRootDirs;
    }

    public void setSrcRootDirs(List<String> srcRootDirs) {
        this.srcRootDirs = srcRootDirs;
    }

    public String getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    public String getSrcEncoding() {
        return srcEncoding;
    }

    public void setSrcEncoding(String srcEncoding) {
        this.srcEncoding = srcEncoding;
    }
}
