/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.model.weapons;

import java.util.function.Function;

public class LoadedWeapon extends Weapon {

    private Function<Integer, Integer> hitCalculator;
    private Function<Integer, Integer> dmgCalculator;

    public LoadedWeapon(
            final String identifier,
            final String weaponName,
            final Function<Integer, Integer> hitCalculator,
            final Function<Integer, Integer> dmgCalculator,
            final Integer baseHit,
            final Integer baseDmg) {
        super(identifier, weaponName, baseHit, baseDmg);
        this.hitCalculator = hitCalculator;
        this.dmgCalculator = dmgCalculator;
    }

    @Override
    public Integer hit() {
        return hitCalculator.apply(baseHit);
    }

    @Override
    public Integer dmg() {
        return dmgCalculator.apply(baseDmg);
    }
}
