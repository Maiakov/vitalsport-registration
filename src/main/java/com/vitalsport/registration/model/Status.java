package com.vitalsport.registration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vitalsport.registration.config.StatusSerialization;
import lombok.Getter;

@JsonSerialize(using = StatusSerialization.class)
@Getter
public enum Status {
    SUCCESS, ERROR;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}
