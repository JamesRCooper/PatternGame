/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.models.armor;

import com.cooper.models.Carryable;

public abstract class Armor implements Carryable {

    protected Integer baseAC;

    public abstract String getName();
}
