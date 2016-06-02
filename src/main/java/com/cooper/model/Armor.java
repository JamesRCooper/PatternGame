/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.model;

public interface Armor extends Carryable {

    Integer getAC();
    String getName();

    String getACString();

    default String getIdentifier() {
        return getName() + " " + getACString();
    }
}
