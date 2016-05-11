/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.armor.decorators;

import com.cooper.models.armor.Armor;

public abstract class ArmorDecorator extends Armor {

    protected static String SPACE = " ";

    protected Armor armor;
    protected String nameDecor;

    public ArmorDecorator(Armor armor) {
        this.armor = armor;
        this.nameDecor = "postfix";
    }

    @Override
    public String getName() {
        return armor.getName() + SPACE + nameDecor;
    }
}
