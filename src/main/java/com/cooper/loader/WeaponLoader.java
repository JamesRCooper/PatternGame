/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.loader;

import java.util.function.Function;

import org.json.JSONObject;

import com.cooper.models.weapons.LoadedWeapon;
import com.cooper.utils.JSONHandler;

public class WeaponLoader extends Loader<LoadedWeapon> {

    private static String rootDirectory = "src/main/resources/weapons";
    private final JSONHandler jsonHandler = new JSONHandler("Loading weapon");

    public WeaponLoader() {
        super(rootDirectory);
    }

    @Override
    protected LoadedWeapon createItemFromJsonSheet(String itemSheet) {

        JSONObject itemFields = jsonHandler.handle(JSONObject::new, itemSheet);

        String name = jsonHandler.handle(itemFields::getString, "name");
        String type = jsonHandler.handle(itemFields::getString, "type");

        Integer baseHit = jsonHandler.handle(itemFields::getInt, "baseHit");
        Function<Integer, Integer> hitRoll = getDiceRoller("d20", 1);

        JSONObject dmgFields = jsonHandler.handle(itemFields::getJSONObject, "dmg");
        Integer baseDmg = jsonHandler.handle(dmgFields::getInt, "base");

        JSONObject diceFields = jsonHandler.handle(dmgFields::getJSONObject, "die");
        String diceType = jsonHandler.handle(diceFields::getString, "size");
        Integer diceNumber = jsonHandler.handle(diceFields::getInt, "number");
        Function<Integer, Integer> dmgRoll = getDiceRoller(diceType, diceNumber);

        return new LoadedWeapon(
                name,
                hitRoll,
                dmgRoll,
                baseHit,
                baseDmg);
    }
}
