/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.entities;

import org.springframework.data.annotation.Id;

import com.cooper.entities.sub.Dice;
import com.cooper.enums.CarryableType;

public class WeaponEntity implements CarryableEntity {

    @Id
    private String identifier;
    private CarryableType type;
    private String name;
    private Dice dmg;
    private Dice hit;

    public WeaponEntity(
            String identifier,
            CarryableType type,
            String name,
            Dice dmg,
            Dice hit) {

        this.type = type;
        this.identifier = identifier;
        this.name = name;
        this.dmg = dmg;
        this.hit = hit;
    }

    public WeaponEntity() {
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
