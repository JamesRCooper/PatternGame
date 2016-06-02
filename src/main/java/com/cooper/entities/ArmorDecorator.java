/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.entities;

import org.springframework.data.annotation.Id;

import com.cooper.enums.CarryableType;
import com.cooper.enums.DecoratorPlacement;

public class ArmorDecorator implements CarryableEntity {

    @Id
    private String identifier;
    private CarryableType type;
    private String name;
    private DecoratorPlacement placement;
    private Integer baseAC;

    public ArmorDecorator(
            String identifier,
            CarryableType type,
            String name,
            DecoratorPlacement placement,
            Integer baseAC) {

        this.identifier = identifier;
        this.type = type;
        this.name = name;
        this.placement = placement;
        this.baseAC = baseAC;
    }

    public ArmorDecorator() {
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

    public DecoratorPlacement getPlacement() {
        return placement;
    }

    public void setPlacement(DecoratorPlacement placement) {
        this.placement = placement;
    }

    public Integer getBaseAC() {
        return baseAC;
    }

    public void setBaseAC(Integer baseAC) {
        this.baseAC = baseAC;
    }
}
