package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class SourceMetrics<T> {

    protected String name;
    protected String description;
    protected final Map<String, String> details = new LinkedHashMap<>();

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

    public Map<String, String> getDetails() {
        return this.details;
    }
}
