/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.weapons.decorators;

import com.cooper.models.weapons.Weapon;

public class SwiftWPreDecor extends WeaponPreDecorator {

    public SwiftWPreDecor(Weapon weapon) {
        super(weapon, 5, 0);
        this.nameDecor = "swift";
    }
}
