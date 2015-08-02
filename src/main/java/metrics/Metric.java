package metrics;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Metric {
    private String name;
    private String description;
    private final LinkedHashMap<String, String> namedValues = new LinkedHashMap<>();

    public Metric(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LinkedHashMap<String, String> getNamedValues() {
        return namedValues;
    }

}
