package maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

@Mojo(name = "metrics")
public class MetricsMojo extends AbstractMojo {


    @Parameter(defaultValue="${project}", readonly=true, required=true)
    private MavenProject project;


    public void execute() throws MojoExecutionException {
        List<String> compileSourceRoots = project.getCompileSourceRoots();
        getLog().info( ">>>>Project baseDir = " + project.getBasedir());
        for (String srcRoot : compileSourceRoots) {
            getLog().info(">>>>Project srcRoot = " + srcRoot);
        }
    }
}
