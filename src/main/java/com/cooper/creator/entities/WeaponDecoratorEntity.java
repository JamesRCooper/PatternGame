/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.creator.entities;

import org.springframework.data.annotation.Id;

import com.cooper.creator.entities.sub.Dice;
import com.cooper.creator.enums.CarryableType;
import com.cooper.creator.enums.DecoratorPlacement;

public class WeaponDecoratorEntity implements CarryableDecoratorEntity<WeaponEntity> {

    @Id
    private String identifier;
    private CarryableType type;
    private String name;
    private DecoratorPlacement placement;
    private Dice dmg;
    private Dice hit;

    public WeaponDecoratorEntity(
            String identifier,
            CarryableType type,
            String name,
            DecoratorPlacement placement,
            Dice dmg, Dice hit) {
        this.identifier = identifier;
        this.type = type;
        this.name = name;
        this.placement = placement;
        this.dmg = dmg;
        this.hit = hit;
    }

    public WeaponDecoratorEntity() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public CarryableType getType() {
        return type;
    }

    public void setType(CarryableType type) {
        this.type = type;
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

    public Dice getDmg() {
        return dmg;
    }

    public void setDmg(Dice dmg) {
        this.dmg = dmg;
    }

    public Dice getHit() {
        return hit;
    }

    public void setHit(Dice hit) {
        this.hit = hit;
    }
}
