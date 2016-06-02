/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.entities.sub;

import com.cooper.enums.DieType;

public class Dice {

    private Integer baseValue;
    private Integer numberOfDice;
    private DieType size;

    public Dice(Integer baseValue, Integer numberOfDice, DieType size) {
        this.baseValue = baseValue;
        this.numberOfDice = numberOfDice;
        this.size = size;
    }

    public Dice() {
    }

    public Integer roll() {
        return size.roll(numberOfDice, baseValue);
    }

    public Integer getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(Integer baseValue) {
        this.baseValue = baseValue;
    }

    public Integer getNumberOfDice() {
        return numberOfDice;
    }

    public void setNumberOfDice(Integer numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    public DieType getSize() {
        return size;
    }

    public void setSize(DieType size) {
        this.size = size;
    }
}
