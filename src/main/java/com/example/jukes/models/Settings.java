package com.example.jukes.models;

import java.util.ArrayList;
import java.util.List;

public class Settings {
    private List<Setting> settings;

    public Settings() {
        this.settings = new ArrayList<>();
    }

    public Settings(List<Setting> settings) {
        this.settings = settings;
    }

    public List<Setting> getSettings() {
        return this.settings;
    }
}
