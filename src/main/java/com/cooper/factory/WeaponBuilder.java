/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.factory;

import org.json.JSONObject;

import com.cooper.models.weapons.Weapon;
import com.cooper.models.weapons.decorators.OfTheBeastWDecor;
import com.cooper.models.weapons.decorators.SwiftWPreDecor;

public class WeaponBuilder extends AbstractCarryableFactory<Weapon> {

    private static String operation = "Building weapon";

    public WeaponBuilder(JSONObject itemInfo) {
        super(operation, itemInfo);
    }

    @Override
    protected void initializeItem(String weaponType) {

        switch(weaponType) {
        case ("fist"):
            item = new FistWeapon();
            break;
        case ("great sword"):
            item = new GreatSwordWeapon();
            break;
        default:
            throw new RuntimeException("No weapon found of type: " + weaponType);
        }
    }

    @Override
    protected void wrapItemWithPostDecorator(final String decorator) {

        switch(decorator) {
        case ("of the beast"):
            item = new OfTheBeastWDecor(item);
            break;
        default:
            throw new RuntimeException("No weapon post decorator found of type: " + decorator);
        }
    }

    @Override
    protected void wrapItemWithPreDecorator(final String decorator) {

        switch(decorator) {
        case ("swift"):
            item = new SwiftWPreDecor(item);
            break;
        default:
            throw new RuntimeException("No weapon pre decorator found of type: " + decorator);
        }
    }
}
