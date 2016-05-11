/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.weapons.decorators;

import com.cooper.models.weapons.Weapon;

public abstract class WeaponDecorator extends Weapon {

    protected static String SPACE = " ";

    protected Weapon weapon;
    protected String nameDecor;

    public WeaponDecorator(Weapon weapon, Integer baseHit, Integer baseDmg) {
        super(baseHit, baseDmg);
        this.weapon = weapon;
        this.nameDecor = "postfix";
    }

    @Override
    public Integer hit() {
        return weapon.hit() + baseHit;
    }

    @Override
    public Integer dmg() {
        return weapon.dmg() + baseDmg;
    }

    @Override
    public String getName() {
        return weapon.getName() + SPACE + nameDecor;
    }
}
