package com.team.backend.model.Enum;

public enum Sex {
    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("Other");

    private final String displayName;

    Sex(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
