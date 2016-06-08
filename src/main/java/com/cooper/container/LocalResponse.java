/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.container;

import java.util.ArrayList;
import java.util.List;

import com.cooper.creator.enums.LocalErrorType;

public class LocalResponse {

    private String message = "";
    private List<LocalErrorType> errors = new ArrayList<>();

    public LocalResponse() {
    }

    public LocalResponse(String message) {
        this.message = message;
    }

    public LocalResponse(LocalErrorType error) {
        this.errors.add(error);
    }

    public LocalResponse(List<LocalErrorType> errors) {
        this.errors = errors;
    }

    public void addError(LocalErrorType error) {
        this.errors.add(error);
    }

    public List<LocalErrorType> getErrors() {
        return errors;
    }

    public Boolean isSuccessful() {
        return errors.size() == 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
