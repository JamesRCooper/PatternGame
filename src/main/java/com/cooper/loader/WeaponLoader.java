/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.loader;

import org.json.JSONObject;

import com.cooper.models.weapons.LoadedWeapon;
import com.cooper.models.weapons.Weapon;
import com.cooper.utils.JSONHandler;

public class WeaponLoader extends Loader<Weapon> {

    public WeaponLoader() {
        super("src/main/resources/weapon", new JSONHandler("Loading weapon"));
    }

    @Override
    protected Weapon createItemFromJsonSheet(String weaponSheet) {

        JSONObject weaponFields = jsonHandler.handle(JSONObject::new, weaponSheet);

        String identifier = jsonHandler.handle(weaponFields::getString, "identifier");
        String name = jsonHandler.handle(weaponFields::getString, "name");
        String type = jsonHandler.handle(weaponFields::getString, "type");

        JSONObject dmgFields = jsonHandler.handle(weaponFields::getJSONObject, "dmg");
        JSONObject hitFields = jsonHandler.handle(weaponFields::getJSONObject, "hit");
        Integer baseDmg = jsonHandler.handle(dmgFields::getInt, "base");
        Integer baseHit = jsonHandler.handle(hitFields::getInt, "base");

        JSONObject dmgDie = jsonHandler.handle(dmgFields::getJSONObject, "die");
        JSONObject hitDie = jsonHandler.handle(hitFields::getJSONObject, "die");

        return new LoadedWeapon(identifier, name, buildDie(hitDie), buildDie(dmgDie), baseHit, baseDmg);
    }
}
