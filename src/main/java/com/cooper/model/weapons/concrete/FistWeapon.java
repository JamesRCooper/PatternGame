/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.model.weapons.concrete;

import static com.cooper.utils.DiceUtil.xd2_plusy;

import com.cooper.model.weapons.Weapon;

public class FistWeapon extends Weapon {

    public FistWeapon() {
        super("Fist", "fist", 3, 2);
    }

    @Override
    public Integer dmg() {
        return xd2_plusy(2, baseDmg);
    }
}
