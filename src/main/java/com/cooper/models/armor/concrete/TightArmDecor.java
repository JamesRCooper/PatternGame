/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.armor.concrete;

import com.cooper.enums.DecoratorPlacement;
import com.cooper.models.armor.Armor;
import com.cooper.models.armor.ArmorDecorator;

public class TightArmDecor extends ArmorDecorator {

    public TightArmDecor(
            String identifier,
            String name,
            Integer baseAC,
            DecoratorPlacement placement) {
        super(identifier, name, baseAC, placement);
    }

    private TightArmDecor(
            String identifier,
            String name,
            Integer baseAC,
            DecoratorPlacement placement,
            Armor armor) {
        super(identifier, name, baseAC, placement, armor);
    }

    @Override
    public Armor wrapItemWithNewInstance(Armor armor) {
        return new TightArmDecor(
                getIdentifier(), name, baseAC, placement, armor);
    }
}
