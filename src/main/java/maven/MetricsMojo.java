package maven;

import components.file.DirectoryReader;
import io.OutputWriter;
import processors.Executor;
import model.Metric;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;
import java.util.List;

//TODO
// 0 -read project properties
// 1 - read sources
// 2 - run code processing
// 3 - save results

@Mojo(name = "processors")
public class MetricsMojo extends AbstractMojo {

    @Parameter(defaultValue="${project}", readonly=true, required=true)
    private MavenProject project;

    private DirectoryReader directoryReader = new DirectoryReader();
    private Executor executor = new Executor();


    public void execute() throws MojoExecutionException {



        List<String> compileSourceRoots = project.getCompileSourceRoots();
        getLog().info( ">>>>Project baseDir = " + project.getBasedir());
        for (String srcRoot : compileSourceRoots) {
            getLog().info(">>>>Project srcRoot = " + srcRoot);
            this.directoryReader.init(srcRoot);
        }
        getLog().info(">>>>Project build outputDir = " + project.getBuild().getDirectory()); // target
        String encoding = project.getProperties().getProperty("project.build.sourceEncoding");

        getLog().info(">>>> Encoding = "+encoding);

        // init directory reader

        try {
            List<File> allSourceNodes = this.directoryReader.getAllSrcFiles();

            executor.getSourceNodes().addAll(allSourceNodes);
            executor.init();
            List<Metric> metrics = executor.execute();

            OutputWriter writer = new OutputWriter();
            writer.saveMetrics(metrics, project.getBuild().getDirectory(), null);
        }catch (IOException e) {
            throw new MojoExecutionException("", e);
        }

    }
}
