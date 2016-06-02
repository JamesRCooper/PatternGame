/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.model;

import com.cooper.enums.DieType;

public class WeaponBase implements Weapon{

    private String name;
    private DieType hit;
    private Integer numberOFHitDice;
    private Integer baseHit;
    private DieType dmg;
    private Integer numberOfDmgDice;
    private Integer baseDmg;

    public WeaponBase(
            String name,
            DieType hit,
            Integer numberOFHitDice,
            Integer baseHit,
            DieType dmg, Integer numberOfDmgDice, Integer baseDmg) {

        this.name = name;
        this.hit = hit;
        this.numberOFHitDice = numberOFHitDice;
        this.baseHit = baseHit;
        this.dmg = dmg;
        this.numberOfDmgDice = numberOfDmgDice;
        this.baseDmg = baseDmg;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer hit() {
        return hit.roll(numberOFHitDice, baseHit);
    }

    @Override
    public Integer dmg() {
        return dmg.roll(numberOfDmgDice, baseDmg);
    }

    @Override
    public String getDmgString() {
        return numberOfDmgDice + dmg.name() + "+" + baseDmg;
    }

    @Override
    public String getHitString() {
        return numberOFHitDice + hit.name() + "+" + baseHit ;
    }
}
