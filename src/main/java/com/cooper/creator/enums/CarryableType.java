package com.cooper.creator.enums;

public enum CarryableType {

    WEAPON("weapon"),
    WEAPON_DECORATOR("weaponDecorator"),
    ARMOR("armor"),
    ARMOR_DECORATOR("armorDecorator"),

    FRUIT("fruit"),

    //Used for block ticks
    INTANGIBLE_TICK("intangibleTick");

    private String value;

    CarryableType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
