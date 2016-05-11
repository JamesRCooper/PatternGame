/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.weapons.concrete;

import com.cooper.models.weapons.Weapon;
import com.cooper.utils.Dice;

public class DaggerWeapon extends Weapon {

    public DaggerWeapon(Integer baseHit, Integer baseDmg) {
        super("DaggerWeapon", "dagger", 4, 4);
    }

    @Override
    public Integer dmg() {
        return Dice.xd2_plusy(2, baseDmg);
    }
}
