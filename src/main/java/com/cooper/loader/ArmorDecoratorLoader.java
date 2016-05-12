/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.loader;

import org.json.JSONObject;

import com.cooper.enums.DecoratorPlacement;
import com.cooper.models.armor.ArmorDecorator;
import com.cooper.models.armor.LoadedArmorDecor;
import com.cooper.utils.JSONHandler;

public class ArmorDecoratorLoader extends Loader<ArmorDecorator> {

    public ArmorDecoratorLoader() {
        super("src/main/resources/armor_decorator", new JSONHandler("Loading armor decorators"));
    }

    @Override
    protected ArmorDecorator createItemFromJsonSheet(String armorDecoratorSheet) {

        JSONObject decoratorFields = jsonHandler.handle(JSONObject::new, armorDecoratorSheet);

        String identifier = jsonHandler.handle(decoratorFields::getString, "identifier");
        String name = jsonHandler.handle(decoratorFields::getString, "name");
        String type = jsonHandler.handle(decoratorFields::getString, "type");
        DecoratorPlacement placement = DecoratorPlacement.valueOf(
                jsonHandler.handle(decoratorFields::getString, "placement"));
        Integer baseAC = jsonHandler.handle(decoratorFields::getInt, "baseAC");

        return new LoadedArmorDecor(identifier, name, baseAC, placement);
    }
}
