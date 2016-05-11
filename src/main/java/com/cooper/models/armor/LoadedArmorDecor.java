/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.armor;

import com.cooper.enums.DecoratorPlacement;

public class LoadedArmorDecor extends ArmorDecorator {

    public LoadedArmorDecor(
            String identifier,
            String name,
            Integer baseAC,
            DecoratorPlacement placement) {

        super(identifier, name, baseAC, placement);
    }

    private LoadedArmorDecor(
            String identifier,
            String name,
            Integer baseAC,
            DecoratorPlacement placement,
            Armor armor) {

        super(identifier, name, baseAC, placement, armor);
    }

    @Override
    public Armor wrapItemWithNewInstance(Armor armor) {
        return new LoadedArmorDecor(
                getIdentifier(), name, baseAC, placement, armor);
    }
}
