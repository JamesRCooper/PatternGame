package com.cooper.creator.model;

import com.cooper.creator.enums.CarryableType;

public interface Weapon extends Carryable {

    String getName();

    Integer hit();
    Integer dmg();

    String getDmgString();
    String getHitString();

    default String getIdentifier() {

        return getName() +
                "\nhit: " + getHitString() +
                "\ndmg: " + getDmgString();
    }

    @Override
    default CarryableType getType() {
        return CarryableType.WEAPON;
    }
}
