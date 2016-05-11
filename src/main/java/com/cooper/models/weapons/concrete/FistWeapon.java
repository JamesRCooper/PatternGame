/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.weapons.concrete;

import static com.cooper.utils.Dice.xd2_plusy;

import com.cooper.models.weapons.Weapon;

public class FistWeapon extends Weapon {

    public FistWeapon() {
        super("Fist", "fist", 3, 2);
    }

    @Override
    public Integer dmg() {
        return xd2_plusy(2, baseDmg);
    }
}
