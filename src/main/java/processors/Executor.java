package processors;

import components.Component;
import components.Context;
import components.Settings;
import components.file.FileComponent;
import components.java.JavaComponent;
import io.OutputWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Executor {

    private Context context;
    private final List<Component> components = new ArrayList<>();

    private OutputWriter writer = new OutputWriter();

    public void init(Settings settings) {

        context = new Context();
        context.setSettings(settings);

        components.add(new FileComponent());
        components.add(new JavaComponent());
        components.stream().forEach(component -> component.init());
    }

    public void execute() throws IOException {
        this.components.stream().forEach(component -> component.process(this.context));

        System.out.printf("processed components!");
        this.writer.saveMetrics(context.getMetrics(), context.getSettings().getTargetDir(), null);
    }

}
