/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.model;

import com.cooper.creator.enums.CarryableType;

public interface Carryable extends Model {

    CarryableType getType();
}
