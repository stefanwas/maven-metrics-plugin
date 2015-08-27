package model;

import java.util.LinkedHashMap;

//TODO change the name to something different
public class Metric {
    private String name;
    private String description;
    private final LinkedHashMap<String, String> elements = new LinkedHashMap<>();

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

    public LinkedHashMap<String, String> getElements() {
        return elements;
    }

}
