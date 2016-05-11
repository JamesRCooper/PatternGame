/*
 * Copyright (C) 2016 by Amobee Inc.
 * All Rights Reserved.
 */
package com.cooper.dtos;

import com.cooper.models.character.Character;

public class CharacterDTO {

    private final String DESCRIPTION_TEMPLATE =
            "%s, wearing %s, and wielding %s";

    private String characterName;
    private String weaponName;
    private String armorName;

    public CharacterDTO(Character character) {
        this.characterName = character.getName();
        this.weaponName = character.getWeapon().getName();
        this.armorName = character.getArmor().getName();
    }

    public String getDescription() {
        return String.format(DESCRIPTION_TEMPLATE, characterName, armorName, weaponName);
    }
}
