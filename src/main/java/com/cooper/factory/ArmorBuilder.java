/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.factory;

import org.json.JSONObject;

import com.cooper.models.armor.Armor;
import com.cooper.models.armor.ChainMailArmor;
import com.cooper.models.armor.ClothArmor;
import com.cooper.models.armor.decorators.ShimmeringPreDecorator;
import com.cooper.models.armor.decorators.TightPreDecor;

public class ArmorBuilder extends AbstractCarryableFactory<Armor> {

    private static String operation = "Building armor";

    public ArmorBuilder(JSONObject itemInfo) {
        super(operation, itemInfo);
    }

    @Override
    protected void initializeItem(String armorType) {

        switch(armorType) {
        case ("cloth"):
            item = new ClothArmor();
            break;
        case ("chain mail"):
            item = new ChainMailArmor();
            break;
        default:
            throw new RuntimeException("No armor found of type: " + armorType);
        }
    }

    @Override
    protected void wrapItemWithPostDecorator(String decorator) {

        switch (decorator) {
        default:
            throw new RuntimeException("No armor post decorator of type: " + decorator);
        }
    }

    @Override
    protected void wrapItemWithPreDecorator(String decorator) {

        switch (decorator) {
        case ("shimmering"):
            item = new ShimmeringPreDecorator(item);
            break;
        case ("tight"):
            item = new TightPreDecor(item);
            break;
        default:
            throw new RuntimeException("No armor pre decorator of type: " + decorator);
        }
    }
}
