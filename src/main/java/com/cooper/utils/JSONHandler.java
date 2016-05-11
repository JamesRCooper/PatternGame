/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.cooper.exceptions.JSONRunTimeException;

public class JSONHandler {

    private String operation;

    public JSONHandler(final String operation) {
        this.operation = operation;
    }

    public <T> T handle(final JSONFunction<String, T> jsonFunction, final String jsonString) {

        try {
            return jsonFunction.process(jsonString);
        } catch (JSONException jsonEx) {
            throw createNewException(jsonString, jsonEx);
        }

    }

    public <T> List<T> handle(JSONFunction<Integer, T> jsonFunction, JSONArray jsonArray) {
        List<T> items = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                items.add(jsonFunction.process(i));
            }
            return items;
        } catch (JSONException jsonEx) {
            throw createNewException(jsonArray.toString(), jsonEx);
        }
    }

    private JSONRunTimeException createNewException(final String json, JSONException jsonEx) {
        return new JSONRunTimeException(
                operation + " body: \"" + json + "\"", jsonEx);
    }

    @FunctionalInterface
    public interface JSONFunction<A, T> {
        T process(A argument) throws JSONException;
    }
}
