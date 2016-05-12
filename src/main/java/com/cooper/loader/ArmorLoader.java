/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.loader;

import org.json.JSONObject;

import com.cooper.models.armor.Armor;
import com.cooper.models.armor.LoadedArmor;
import com.cooper.utils.JSONHandler;

public class ArmorLoader extends Loader<Armor> {

    public ArmorLoader() {
        super("src/main/resources/armor", new JSONHandler("Loading armor"));
    }

    @Override
    protected Armor createItemFromJsonSheet(String armorSheet) {

        JSONObject armorFields = jsonHandler.handle(JSONObject::new, armorSheet);

        String identifier = jsonHandler.handle(armorFields::getString, "identifier");
        String name = jsonHandler.handle(armorFields::getString, "name");
        String type = jsonHandler.handle(armorFields::getString, "type");

        Integer baseAC = jsonHandler.handle(armorFields::getInt, "baseAc");

        return new LoadedArmor(identifier, name, baseAC);
    }
}
