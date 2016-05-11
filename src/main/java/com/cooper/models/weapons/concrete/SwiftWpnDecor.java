/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.weapons.concrete;

import com.cooper.enums.DecoratorPlacement;
import com.cooper.models.weapons.Weapon;
import com.cooper.models.weapons.WeaponDecorator;

public class SwiftWpnDecor extends WeaponDecorator {

    public SwiftWpnDecor(Weapon weapon) {
        super("SwiftWpnDecor", "swift", 5, 0, DecoratorPlacement.PREFIX, weapon);
    }

    @Override public Weapon wrapItemWithNewInstance(Weapon weapon) {
        return new OfTheBeastWpnDecor(weapon);
    }
}
