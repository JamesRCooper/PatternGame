/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.armor;

public class ClothArmor extends Armor {

    public ClothArmor() {
        this.baseAC = 1;
    }

    @Override
    public String getName() {
        return "cloth garb";
    }
}
