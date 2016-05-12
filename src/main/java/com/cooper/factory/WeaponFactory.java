/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.factory;

import java.util.Map;

import com.cooper.models.weapons.LoadedWeapon;
import com.cooper.models.weapons.Weapon;

public class WeaponFactory {

    private Map<String, LoadedWeapon> weapons;

    public WeaponFactory(Map<String, LoadedWeapon> weapons) {
        this.weapons = weapons;
    }

    public Weapon getWeaponByName(final String weaponName) {
        //TODO: expand to make use of hard coded weapon
        if (weapons.containsKey(weaponName)) {
            return weapons.get(weaponName).clone();
        }
        return null;
    }
}
