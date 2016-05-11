/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.weapons;

import java.util.function.Function;

import com.cooper.enums.DecoratorPlacement;

public class LoadedWeaponDecorator extends WeaponDecorator {

    private Function<Integer, Integer> hitCalculator;
    private Function<Integer, Integer> dmgCalculator;

    public LoadedWeaponDecorator(
            final String identifier,
            final String nameDecor,
            final Function<Integer, Integer> hitCalculator,
            final Function<Integer, Integer> dmgCalculator,
            final Integer baseHit,
            final Integer baseDmg,
            final DecoratorPlacement placement) {

        super(identifier, nameDecor, baseHit, baseDmg, placement);
    }

    private LoadedWeaponDecorator(
            final String identifier,
            final String nameDecor,
            final Function<Integer, Integer> hitCalculator,
            final Function<Integer, Integer> dmgCalculator,
            final Integer baseHit,
            final Integer baseDmg,
            final DecoratorPlacement placement,
            final Weapon weapon) {

        super(identifier, nameDecor, baseHit, baseDmg, placement, weapon);
    }

    @Override public Integer hit() {
        return super.hit() + hitCalculator.apply(baseHit);
    }

    @Override public Integer dmg() {
        return super.dmg() + dmgCalculator.apply(baseDmg);
    }

    @Override
    public Weapon wrapItemWithNewInstance(Weapon weapon) {
        return new LoadedWeaponDecorator(
                getIdentifier(), name, hitCalculator, dmgCalculator, baseHit, baseDmg, placement, weapon);
    }
}
