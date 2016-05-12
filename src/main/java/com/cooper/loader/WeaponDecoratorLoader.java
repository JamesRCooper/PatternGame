/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.loader;

import org.json.JSONObject;

import com.cooper.enums.DecoratorPlacement;
import com.cooper.models.weapons.LoadedWeaponDecorator;
import com.cooper.models.weapons.WeaponDecorator;
import com.cooper.utils.JSONHandler;

public class WeaponDecoratorLoader extends Loader<WeaponDecorator> {

    public WeaponDecoratorLoader() {
        super("src/main/resources/weapon_decorator", new JSONHandler("Loading weapon decorators"));
    }

    @Override
    protected WeaponDecorator createItemFromJsonSheet(String weaponDecoratorSheet) {

        JSONObject decoratorFields = jsonHandler.handle(JSONObject::new, weaponDecoratorSheet);

        String identifier = jsonHandler.handle(decoratorFields::getString, "identifier");
        String name = jsonHandler.handle(decoratorFields::getString, "name");
        String type = jsonHandler.handle(decoratorFields::getString, "type");
        DecoratorPlacement placement = DecoratorPlacement.valueOf(
                jsonHandler.handle(decoratorFields::getString, "placement"));

        JSONObject dmgFields = jsonHandler.handle(decoratorFields::getJSONObject, "dmg");
        JSONObject hitFields = jsonHandler.handle(decoratorFields::getJSONObject, "hit");
        Integer baseDmg = jsonHandler.handle(dmgFields::getInt, "base");
        Integer baseHit = jsonHandler.handle(hitFields::getInt, "base");

        JSONObject dmgDie = jsonHandler.handle(dmgFields::getJSONObject, "die");
        JSONObject hitDie = jsonHandler.handle(hitFields::getJSONObject, "die");

        return new LoadedWeaponDecorator(
                identifier, name, buildDie(hitDie), buildDie(dmgDie), baseHit, baseDmg, placement);
    }
}
