/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.model;

import com.cooper.enums.DecoratorPlacement;

public class ArmorDecorator implements Armor {

    private Armor baseArmor;

    private String name;
    private DecoratorPlacement placement;
    private Integer baseAC;

    public ArmorDecorator(
            Armor baseArmor,
            String name,
            DecoratorPlacement placement,
            Integer baseAC) {

        this.baseArmor = baseArmor;
        this.name = name;
        this.placement = placement;
        this.baseAC = baseAC;
    }

    @Override
    public String getName() {

        return placement.equals(DecoratorPlacement.PREFIX) ?
                name + " " + baseArmor.getName() :
                baseArmor.getName() + " " + name;
    }

    @Override
    public Integer getAC() {
        return baseArmor.getAC() + baseAC;
    }

    @Override
    public String getACString() {

        String template = placement.equals(DecoratorPlacement.PREFIX) ?
                "(+%s)" + baseArmor.getACString():
                baseArmor.getACString() + "(+%s)";
        return String.format(template, baseAC.toString());
    }
}
