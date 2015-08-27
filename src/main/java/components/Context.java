package components;

import model.Metric;

import java.util.ArrayList;
import java.util.List;

public class Context {
    private Settings settings;
    private final List<Metric> metrics = new ArrayList<>();

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

}
