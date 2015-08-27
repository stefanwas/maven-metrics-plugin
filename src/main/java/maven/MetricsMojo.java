package maven;

import components.Settings;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import processors.Executor;

//https://maven.apache.org/plugin-developers/common-bugs.html
@Mojo(name = "processors")
public class MetricsMojo extends AbstractMojo {

    @Parameter(defaultValue="${project}", readonly=true, required=true)
    private MavenProject project;

    private Executor executor = new Executor();

    public void execute() throws MojoExecutionException {

        Settings settings = getProjectSettings();

        try {
            this.executor.init(settings);
            this.executor.execute();

//            List<File> allSourceNodes = this.directoryReader.getAllSrcFiles();
//
//            executor.getSourceNodes().addAll(allSourceNodes);
//            executor.init();
//            List<Metric> metrics = executor.execute();
//
//            OutputWriter writer = new OutputWriter();
//            writer.saveMetrics(metrics, project.getBuild().getDirectory(), null);
        }catch (Exception e) {
            throw new MojoExecutionException("Maven Metrics Plugin exception", e);
        }
    }

    private Settings getProjectSettings() {
        Settings settings = new Settings();

        settings.setSrcRootDirs(project.getCompileSourceRoots()); //src/main/java,...
        settings.setTargetDir(project.getBuild().getDirectory()); //target
        settings.setSrcEncoding(project.getProperties().getProperty("project.build.sourceEncoding"));

        return settings;
    }
}
