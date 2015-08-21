package model;

import java.util.Map;

public abstract class SourceMetrics<T> {

    protected String name;
    protected String description;
    protected Source<T> source;

    public abstract Map<String, String> getMetricsMap();

    public Source<T> getSource() {
        return source;
    }

    public void setSource(Source<T> source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
