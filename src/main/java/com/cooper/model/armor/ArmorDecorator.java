/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.model.armor;

import com.cooper.enums.DecoratorPlacement;
import com.cooper.model.Decorator;

public abstract class ArmorDecorator extends Armor implements Decorator<Armor> {

    protected static String SPACE = " ";

    private Armor armor;
    protected DecoratorPlacement placement;

    public ArmorDecorator(
            String identifier,
            String name,
            Integer baseAC,
            DecoratorPlacement placement) {
        super(identifier, name, baseAC);
        this.placement = placement;
    }

    protected ArmorDecorator(
            String identifier,
            String name,
            Integer baseAC,
            DecoratorPlacement placement,
            Armor armor) {
        super(identifier, name, baseAC);
        this.placement = placement;
        this.armor = armor;
    }

    @Override
    public Integer getAC() {
        return super.getAC() + baseAC;
    }

    @Override
    public String getName() {
        return placement.equals(DecoratorPlacement.PREFIX) ?
                name + SPACE + armor.getName() :
                armor.getName() + SPACE + name;
    }
}
