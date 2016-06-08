/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestedResponse<Q> {

    private List<Q> objects;
    private List<String> errors = new ArrayList<>();

    public RequestedResponse() {
    }

    public RequestedResponse(Q objects) {
        this.objects = Collections.singletonList(objects);
    }

    public RequestedResponse(List<Q> objects) {
        this.objects = objects;
    }

    public List<Q> getObjects() {
        return objects;
    }

    public void setObjects(List<Q> objects) {
        this.objects = objects;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
