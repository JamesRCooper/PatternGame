/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.entities.sub;

import java.util.List;

public class ArmorCompositeEntity {

    private String baseIdentifier;
    private List<String> decorators;

    public ArmorCompositeEntity() {
    }

    public String getBaseIdentifier() {
        return baseIdentifier;
    }

    public void setBaseIdentifier(String baseIdentifier) {
        this.baseIdentifier = baseIdentifier;
    }

    public List<String> getDecorators() {
        return decorators;
    }

    public void setDecorators(List<String> decorators) {
        this.decorators = decorators;
    }
}
