package com.sheeba.server.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Airport {
    @JsonProperty("k")
    private String key;

    @JsonProperty("v")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
