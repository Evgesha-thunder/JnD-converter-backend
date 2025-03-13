package com.bulish.melnikov.converter.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum State {

    INIT("init"),
    CONVERTING("converting"),
    CONVERTED("converted"),
    IN_ERROR("in_error");

    private final String status;

    State(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
