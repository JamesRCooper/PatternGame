/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.weapons;

import com.cooper.utils.Dice;

public class DaggerWeapon extends Weapon{

    public DaggerWeapon(Integer baseHit, Integer baseDmg) {
        super(baseHit, baseDmg);
    }

    @Override
    public Integer hit() {
        return Dice.xd20_plusy(1, 0);
    }

    @Override
    public Integer dmg() {
        return Dice.xd2_plusy(2, baseDmg);
    }

    @Override
    public String getName() {
        return "a small dagger";
    }
}
