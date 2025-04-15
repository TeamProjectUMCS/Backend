package com.team.backend.model.Enum;


public enum Preference {
    MEN("Men"),
    WOMEN("Women"),
    BOTH("Both");

    private final String displayName;

    Preference(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
