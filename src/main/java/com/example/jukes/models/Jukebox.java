package com.example.jukes.models;

import java.util.ArrayList;
import java.util.List;

public class Jukebox {
    private String id;
    private String model;
    private List<Component> components = new ArrayList<>();

    public Jukebox(String id, String model, List<Component> components) {
        this.id = id;
        this.model = model;
        this.components = components;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
