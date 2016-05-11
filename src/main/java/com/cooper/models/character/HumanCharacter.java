/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.character;

public class HumanCharacter extends Character {

    public HumanCharacter(String name, Integer baseDmg) {
        super(name, baseDmg);
        characterRace = "human";
    }

    @Override
    public String getIdentifier() {
        return name;
    }
}
