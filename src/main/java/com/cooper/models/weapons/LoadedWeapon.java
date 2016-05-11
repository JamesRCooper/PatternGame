/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.weapons;

import java.util.function.Function;

public class LoadedWeapon extends Weapon {

    private String weaponName;

    private Function<Integer, Integer> hitCalculator;
    private Function<Integer, Integer> dmgCalculator;

    public LoadedWeapon(
            final String weaponName,
            final Function<Integer, Integer> hitCalculator,
            final Function<Integer, Integer> dmgCalculator,
            final Integer baseHit,
            final Integer baseDmg) {
        super(baseHit, baseDmg);
        this.weaponName = weaponName;
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

    @Override
    public String getName() {
        return weaponName;
    }

    @Override
    public String getIdentifier() {
        return weaponName;
    }

    public Weapon clone() {
        return new LoadedWeapon(
                weaponName,
                hitCalculator,
                dmgCalculator,
                baseHit,
                baseDmg);
    }
}
