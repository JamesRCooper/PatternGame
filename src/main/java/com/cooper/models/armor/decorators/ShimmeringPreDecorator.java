/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.armor.decorators;

import com.cooper.models.armor.Armor;

public class ShimmeringPreDecorator extends ArmorPreDecorstor {

    public ShimmeringPreDecorator(Armor armor) {
        super(armor);
        nameDecor = "shimmering";
    }
}
