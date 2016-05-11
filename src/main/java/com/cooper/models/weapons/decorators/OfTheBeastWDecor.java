/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.weapons.decorators;

import com.cooper.models.weapons.Weapon;

public class OfTheBeastWDecor extends WeaponDecorator {

    public OfTheBeastWDecor(Weapon weapon) {
        super(weapon, 0, 4);
        this.nameDecor = "of the beast";
    }
}
