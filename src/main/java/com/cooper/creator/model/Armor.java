/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.model;

import com.cooper.creator.enums.CarryableType;

public interface Armor extends Carryable {

    Integer getAC();
    String getName();

    String getACString();

    default String getIdentifier() {
        return getName() + " " + getACString();
    }

    @Override
    default CarryableType getType() {
        return CarryableType.ARMOR;
    }
}
