/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.entities;

import org.springframework.data.annotation.Id;

import com.cooper.creator.enums.CarryableType;

public class ArmorEntity implements CarryableEntity {

    @Id
    private String identifier;
    private CarryableType type;
    private String name;
    private Integer baseAC;

    public ArmorEntity(
            String identifier,
            CarryableType type,
            String name,
            Integer baseAC) {

        this.type = type;
        this.identifier = identifier;
        this.name = name;
        this.baseAC = baseAC;
    }

    public ArmorEntity() {
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
