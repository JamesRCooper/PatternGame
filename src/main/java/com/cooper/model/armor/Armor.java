/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.model.armor;

import com.cooper.model.Carryable;

public abstract class Armor implements Carryable {

    private String identifier;

    protected String name;
    protected Integer baseAC;

    public Armor(String identifier, String name, Integer baseAC) {
        this.identifier = identifier;
        this.name = name;
        this.baseAC = baseAC;
    }

    public Integer getAC() { return baseAC; }

    public String getName() { return name; }

    public String getIdentifier() { return identifier; }
}
