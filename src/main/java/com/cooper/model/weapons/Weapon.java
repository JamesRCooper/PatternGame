/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.model.weapons;

import static com.cooper.utils.DiceUtil.xd20_plusy;

import com.cooper.model.Carryable;

public abstract class Weapon implements Carryable {

    private String identifier;

    protected String name;
    protected Integer baseHit;
    protected Integer baseDmg;

    public Weapon(String identifier, String name, Integer baseHit, Integer baseDmg) {
        this.identifier = identifier;
        this.name = name;
        this.baseHit = baseHit;
        this.baseDmg = baseDmg;
    }

    public Integer hit() {
        return xd20_plusy(1, baseHit);
    }

    public abstract Integer dmg();

    public String getName() {
        return name;
    }

    public String getIdentifier() {
        return  identifier;
    }
}
