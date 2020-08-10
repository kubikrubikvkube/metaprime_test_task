package com.metaprime.testtask.structure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat
public enum OrganizationType {
    /**
     * Юридическое лицо
     */
    ENTITY("Entity"),

    /**
     * Физическое лицо
     */
    INDIVIDUAL("Individual");

    private final String name;

    OrganizationType(String name) {
        this.name = name;
    }

    @JsonCreator
    public static OrganizationType fromString(String key) {
        return key == null ? null : OrganizationType.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String getKey() {
        return name;
    }
}
