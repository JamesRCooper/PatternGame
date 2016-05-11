/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.weapons.decorators;

import com.cooper.models.weapons.Weapon;

public abstract class WeaponPreDecorator extends WeaponDecorator {

    public WeaponPreDecorator(Weapon weapon, Integer baseHit, Integer baseDmg) {
        super(weapon, baseHit, baseDmg);
        this.nameDecor = "prefix";
    }

    @Override
    public String getName() {
        return nameDecor + SPACE + weapon.getName();
    }
}
