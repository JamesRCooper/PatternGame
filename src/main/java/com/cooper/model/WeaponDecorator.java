/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.model;

import com.cooper.enums.DecoratorPlacement;
import com.cooper.enums.DieType;

public class WeaponDecorator implements Weapon {

    private Weapon baseWeapon;

    private String name;
    private DecoratorPlacement placement;
    private DieType hit;
    private Integer numberOFHitDice;
    private Integer baseHit;
    private DieType dmg;
    private Integer numberOfDmgDice;
    private Integer baseDmg;

    public WeaponDecorator(
            Weapon baseWeapon,
            String name,
            DecoratorPlacement placement,
            DieType hit,
            Integer numberOFHitDice,
            Integer baseHit,
            DieType dmg,
            Integer numberOfDmgDice,
            Integer baseDmg) {

        this.baseWeapon = baseWeapon;
        this.name = name;
        this.placement = placement;
        this.hit = hit;
        this.numberOFHitDice = numberOFHitDice;
        this.baseHit = baseHit;
        this.dmg = dmg;
        this.numberOfDmgDice = numberOfDmgDice;
        this.baseDmg = baseDmg;
    }

    @Override
    public String getName() {

        return placement.equals(DecoratorPlacement.PREFIX) ?
                name + " " + baseWeapon.getName() :
                baseWeapon.getName() + " " + name;
    }

    @Override
    public Integer hit() {
        return baseWeapon.hit() + hit.roll(numberOFHitDice, baseHit);
    }

    @Override
    public Integer dmg() {
        return baseWeapon.dmg() + dmg.roll(numberOfDmgDice, baseDmg);
    }

    @Override
    public String getDmgString() {

        String diceString = String.format("(%s)", numberOfDmgDice + dmg.name() + "+" + baseDmg);
        return placement.equals(DecoratorPlacement.PREFIX) ?
                diceString + " " + baseWeapon.getDmgString() :
                baseWeapon.getDmgString() + " " + diceString;
    }

    @Override
    public String getHitString() {

        String diceString = String.format("(%s)", numberOFHitDice + hit.name() + "+" + baseHit);
        return placement.equals(DecoratorPlacement.PREFIX) ?
                diceString + " " + baseWeapon.getHitString() :
                baseWeapon.getHitString() + " " + diceString;
    }
}
