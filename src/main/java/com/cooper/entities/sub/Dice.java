/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.entities.sub;

import com.cooper.enums.DieType;

public class Dice {

    private Integer base;
    private Integer number;
    private DieType size;

    public Dice(Integer base, Integer number, DieType size) {
        this.base = base;
        this.number = number;
        this.size = size;
    }

    public Dice() {
    }

    public Integer roll() {
        return size.roll(number, base);
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public DieType getSize() {
        return size;
    }

    public void setSize(DieType size) {
        this.size = size;
    }
}
