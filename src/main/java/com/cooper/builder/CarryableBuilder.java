/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.builder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cooper.models.Carryable;
import com.cooper.models.Decorator;

public class CarryableBuilder<T extends Carryable> {

    protected Map<String, T> itemMap;
    protected Map<String, Decorator<T>> decoratorMap;

    public CarryableBuilder(
            Map<String, T> itemMap,
            Map<String, Decorator<T>> decoratorMap) {
        this.itemMap = itemMap;
        this.decoratorMap = decoratorMap;
    }

    public T buildItemFromIdentifierAndDecorators(String itemIdentifier, List<String> decoratorIdentifiers) {

        if (baseItExists(itemIdentifier) && baseDecoratorsExist(decoratorIdentifiers)) {
            //TODO: fix potential issue of the same instance being passed around
            T newItem = itemMap.get(itemIdentifier);

        }
        return null;
    }

    private Boolean baseItExists(String itemIdentifier) {
        return itemMap.containsKey(itemIdentifier);
    }

    private Boolean baseDecoratorsExist(List<String> decoratorIdentifiers) {
        return decoratorIdentifiers
                .stream()
                .map(decoratorMap::containsKey)
                .reduce(true, (A, B) -> A && B);
    }

    private T decorateItem(T item, List<String> decoratorIdentifiers) {
        List<Decorator<T>> decorators = decoratorIdentifiers
                .stream()
                .map(decoratorMap::get)
                .collect(Collectors.toList());

        for (Decorator<T> decorator: decorators) {
            item = decorator.wrapItemWithNewInstance(item);
        }
        return item;
    }
}
