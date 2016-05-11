/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.weapons.concrete;

import com.cooper.enums.DecoratorPlacement;
import com.cooper.models.weapons.Weapon;
import com.cooper.models.weapons.WeaponDecorator;

public class OfTheBeastWpnDecor extends WeaponDecorator {

    public OfTheBeastWpnDecor(Weapon weapon) {
        super("OfTheBeastWpnDecor", "of the beast", 0, 4, DecoratorPlacement.POSTFIX, weapon);
    }

    @Override
    public Weapon wrapItemWithNewInstance(Weapon weapon) {
        return new OfTheBeastWpnDecor(weapon);
    }
}
