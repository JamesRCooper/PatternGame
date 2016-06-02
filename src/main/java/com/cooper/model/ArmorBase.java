/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.model;

public class ArmorBase implements Armor {

    private String name;
    private Integer AC;

    public ArmorBase(String name, Integer AC) {

        this.name = name;
        this.AC = AC;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getAC() {
        return AC;
    }

    @Override
    public String getACString() {
        return AC.toString();
    }
}
