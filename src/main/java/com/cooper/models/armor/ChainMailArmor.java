/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.armor;

public class ChainMailArmor extends Armor {

    public ChainMailArmor() {
        this.baseAC = 10;
    }

    @Override
    public String getName() {
        return "chain mail";
    }
}
