/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.model;

public interface Decorator<T extends Carryable> {
    T wrapItemWithNewInstance(T item);
}
