/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.weapons;

import com.cooper.enums.DecoratorPlacement;
import com.cooper.models.Decorator;
import com.cooper.models.weapons.Weapon;

public abstract class WeaponDecorator extends Weapon implements Decorator<Weapon> {

    private static String SPACE = " ";

    private Weapon weapon;
    protected DecoratorPlacement placement;

    public WeaponDecorator(
            String identifier,
            String nameDecor,
            Integer baseHit,
            Integer baseDmg,
            DecoratorPlacement placement) {

        super(identifier, nameDecor, baseHit, baseDmg);
        this.placement = placement;
    }

    protected WeaponDecorator(
            String identifier,
            String nameDecor,
            Integer baseHit,
            Integer baseDmg,
            DecoratorPlacement placement,
            Weapon weapon) {

        super(identifier, nameDecor, baseHit, baseDmg);
        this.placement = placement;
        this.weapon = weapon;
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
        return placement.equals(DecoratorPlacement.PREFIX) ?
                name + SPACE + weapon.getName() :
                weapon.getName() + SPACE + name;
    }
}
