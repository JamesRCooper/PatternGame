/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.entities;

import org.springframework.data.annotation.Id;

import com.cooper.enums.CarryableType;

public class ArmorBase implements CarryableEntity {

    @Id
    private String identifier;
    private CarryableType type;
    private String name;
    private Integer baseAC;

    public ArmorBase(
            String identifier,
            CarryableType type,
            String name,
            Integer baseAC) {

        this.type = type;
        this.identifier = identifier;
        this.name = name;
        this.baseAC = baseAC;
    }

    public ArmorBase() {
    }

    public CarryableType getType() {
        return type;
    }

    public void setType(CarryableType type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaseAC() {
        return baseAC;
    }

    public void setBaseAC(Integer baseAC) {
        this.baseAC = baseAC;
    }
}
