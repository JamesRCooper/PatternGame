/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.armor.decorators;

import com.cooper.models.armor.Armor;

public class TightPreDecor extends ArmorPreDecorstor {

    public TightPreDecor(Armor armor) {
        super(armor);
        nameDecor = "tight fitting";
    }
}
