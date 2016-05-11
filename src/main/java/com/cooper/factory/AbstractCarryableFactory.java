/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.factory;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cooper.models.Carryable;
import com.cooper.utils.JSONHandler;

public abstract class AbstractCarryableFactory<T extends Carryable> {

    protected JSONHandler jsonHandler;

    protected Map<String, T> itemMap;
    protected Map<String, ? extends T> postDecoratorMap;
    protected Map<String, ? extends T> preDecoratorMap;

    protected JSONObject itemInfo;
    protected List<String> postDecoratorTypes;
    protected List<String> preDecoratorTypes;

    protected T item;

    public AbstractCarryableFactory(final String operation, final JSONObject itemInfo) {
        jsonHandler = new JSONHandler(operation);
        this.itemInfo = itemInfo;
    }

    public T build() {

        String itemType = getItemType();
        initializeItem(itemType);
        retrieveDecoratorTypes();
        wrapDecorators();
        return item;
    }

    private String getItemType() {
        return jsonHandler.handle(
                itemInfo::getString, "type");
    }

    protected abstract void initializeItem(final String itemType);

    private void retrieveDecoratorTypes() {
        JSONArray postDecoratorJSON = jsonHandler.handle(
                itemInfo::getJSONArray, "decorators");
        JSONArray preDecoratorJSON = jsonHandler.handle(
                itemInfo::getJSONArray, "predecorators");

        postDecoratorTypes = jsonHandler.handle(
                postDecoratorJSON::getString, postDecoratorJSON);
        preDecoratorTypes = jsonHandler.handle(
                preDecoratorJSON::getString, preDecoratorJSON);
    }

    private void wrapDecorators() {
        postDecoratorTypes.forEach(this::wrapItemWithPostDecorator);
        preDecoratorTypes.forEach(this::wrapItemWithPreDecorator);
    }

    protected abstract void wrapItemWithPostDecorator(final String decorator);

    protected abstract void wrapItemWithPreDecorator(final String decorator);
}
