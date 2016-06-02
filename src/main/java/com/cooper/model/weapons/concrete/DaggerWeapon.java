/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.model.weapons.concrete;

import com.cooper.model.weapons.Weapon;
import com.cooper.utils.DiceUtil;

public class DaggerWeapon extends Weapon {

    public DaggerWeapon(Integer baseHit, Integer baseDmg) {
        super("DaggerWeapon", "dagger", 4, 4);
    }

    @Override
    public Integer dmg() {
        return DiceUtil.xd2_plusy(2, baseDmg);
    }
}
