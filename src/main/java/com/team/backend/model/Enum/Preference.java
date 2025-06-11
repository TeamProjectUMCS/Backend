package com.team.backend.model.Enum;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

@Getter
public enum Preference {
    MEN("Men"),
    WOMEN("Women"),
    BOTH("Both"),
    OTHER("Other");

    private final String displayName;

    Preference(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static Preference fromString(String value) {
        for (Preference p : Preference.values()) {
            if (p.displayName.equalsIgnoreCase(value)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Unknown preference: " + value);
    }
}



