package com.example.jukes.models;

import java.util.List;
import java.util.UUID;

public class Setting {
    private UUID id;
    private List<String> requires;

    public Setting(UUID id, List<String> requires) {
        this.id = id;
        this.requires = requires;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<String> getRequires() {
        return requires;
    }

    public void setRequires(List<String> requires) {
        this.requires = requires;
    }
}
