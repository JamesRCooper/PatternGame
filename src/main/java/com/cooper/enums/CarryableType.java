package com.cooper.enums;

public enum CarryableType {

    WEAPON("weapon"),
    WEAPON_DECORATOR("weaponDecorator"),
    ARMOR("armor"),
    ARMOR_DECORATOR("armorDecorator");

    private String value;

    CarryableType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
