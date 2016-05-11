/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.weapons;

import com.cooper.models.Carryable;
import com.cooper.models.character.Character;

public abstract class Weapon implements Carryable {

    protected Character wielder;

    protected Integer baseHit;
    protected Integer baseDmg;

    public Weapon(Integer baseHit, Integer baseDmg) {
        this.baseHit = baseHit;
        this.baseDmg = baseDmg;
    }

    public abstract Integer hit();
    public abstract Integer dmg();

    public void setWielder(Character wielder) {
        this.wielder = wielder;
    }

    public abstract String getName();
}
