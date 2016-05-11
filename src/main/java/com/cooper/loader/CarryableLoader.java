/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.loader;

import com.cooper.models.Carryable;

public abstract class CarryableLoader<T extends Carryable> extends Loader<T> {

    public CarryableLoader(String rootDirectory) {
        super(rootDirectory);
    }
}
