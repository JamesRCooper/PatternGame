package com.cooper.model;

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
}
