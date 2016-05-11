/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.armor.decorators;

import com.cooper.models.armor.Armor;

public abstract class ArmorPreDecorstor extends ArmorDecorator {

    public ArmorPreDecorstor(Armor armor) {
        super(armor);
        this.nameDecor = "prefix";
    }

    @Override
    public String getName() {
        return nameDecor + SPACE + armor.getName();
    }
}
